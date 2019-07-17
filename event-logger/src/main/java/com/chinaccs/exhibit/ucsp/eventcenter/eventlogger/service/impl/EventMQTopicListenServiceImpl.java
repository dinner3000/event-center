package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.IncomingEventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.ForwardNoticeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardConfigEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardConfigService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusLogService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.utils.ConvertUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventMQTopicListenService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.ForwardTaskMQEnqueueService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventMQTopicListenServiceImpl implements EventMQTopicListenService {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventStatusLogService statusLogService;

    @Autowired
    private EventForwardConfigService eventForwardConfigService;

    @Autowired
    private ForwardTaskMQEnqueueService forwardTaskMQEnqueueService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = "${global-params.event-topic}", errorHandler = "eventListenErrorHandler")
    @Override
    @Transactional
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack) throws Exception {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            logger.debug("receive => event: {}", message);

            IncomingEventDTO incomingEventDTO = JSON.parseObject(message.toString(), IncomingEventDTO.class);
            if (incomingEventDTO.getId() == null){
                throw new RuntimeException("incoming event do not have valid id");
            }
            if (incomingEventDTO.getAppCode() == null){
                throw new RuntimeException("incoming event do not have valid app code");
            }

            EventEntity eventEntity = eventService.getByTraceId(incomingEventDTO.getId(), incomingEventDTO.getAppCode());

            if(eventEntity == null){
                eventEntity = this.saveNewEvent(incomingEventDTO);
                this.performForwardStage(incomingEventDTO, eventEntity);
            } else {
                this.updateEventStatus(incomingEventDTO, eventEntity);
            }

            ack.acknowledge();
        }
    }

    private EventEntity saveNewEvent(IncomingEventDTO incomingEventDTO){
        EventEntity eventEntity = ConvertUtils.sourceToTarget(incomingEventDTO, EventEntity.class);

        eventEntity.setTraceId(incomingEventDTO.getId());
        eventEntity.setId(null);
        eventEntity.setLogTime(new Date());
        eventEntity.setStatus(0);

        logger.debug("save event to db");
        eventService.insert(eventEntity);

        return eventEntity;
    }

    private void updateEventStatus(IncomingEventDTO incomingEventDTO, EventEntity eventEntity){
        if (incomingEventDTO.getStatus() == null){
            throw new RuntimeException("incoming event status is empty");
        }
        if (!EventStatus.isValidCode(incomingEventDTO.getStatus())){
            throw new RuntimeException(String.format(
                    "incoming event do not have valid status: %s", incomingEventDTO.getStatus()));
        }
        if (eventEntity.getStatus() == EventStatus.REJECTED.getCode()){
            throw new RuntimeException(String.format("Change %s status is not allowed", EventStatus.REJECTED.getName()));
        }
        if (eventEntity.getStatus() == EventStatus.RESOLVED.getCode()){
            throw new RuntimeException(String.format("Change %s status is not allowed", EventStatus.RESOLVED.getName()));
        }
        if (StringUtils.isEmpty(incomingEventDTO.getOwner())){
            throw new RuntimeException("incoming event do not have valid owner");
        }

        eventEntity.setStatus(incomingEventDTO.getStatus());
        if(incomingEventDTO.getStatus() == EventStatus.RESOLVED.getCode()){
            eventEntity.setResolveTime(new Date());
        }
        eventService.updateById(eventEntity);

        EventStatusLogEntity statusLogEntity = new EventStatusLogEntity();
        statusLogEntity.setId(null);
        statusLogEntity.setLogTime(new Date());
        statusLogEntity.setCurrStatus(incomingEventDTO.getStatus());
        statusLogEntity.setPrevStatus(eventEntity.getStatus());
        statusLogEntity.setOwner(incomingEventDTO.getOwner());
        statusLogEntity.setOvertime(incomingEventDTO.getOvertime() == null ? 0 : incomingEventDTO.getOvertime());
        statusLogService.insert(statusLogEntity);
    }

    private void performForwardStage(IncomingEventDTO incomingEventDTO, EventEntity eventEntity) {
        EventForwardConfigEntity configEntity;
        do {
            try {
                if (incomingEventDTO.getTypeId() == null || incomingEventDTO.getTypeId() <= 0) {
                    logger.debug("empty type id, skip");
                    break;
                }

                logger.debug("try get event type info");
                configEntity = eventForwardConfigService.tryGetOneConfig(
                        incomingEventDTO.getAppCode(), incomingEventDTO.getTypeId(), incomingEventDTO.getLevel());
                if (configEntity == null) {
                    logger.debug("event type id not exist: {}, skip", incomingEventDTO.getTypeId());
                    logger.debug("possibly: 1. event type not created, 2. wrong type id");
                    break;
                }

                if (configEntity.getFwEnabled() <= 0) {
                    logger.debug("forward disabled, ignore");
                    break;
                }

                logger.debug("forward enabled, send forward signal");
                ForwardNoticeDTO forwardNoticeDTO = new ForwardNoticeDTO(eventEntity, configEntity);
                forwardTaskMQEnqueueService.notify(forwardNoticeDTO);

                logger.debug("====================================================================");

            } catch (Exception e){
                logger.error("unexpected error in performForwardStage(): {}", e.getMessage());
            }
        } while (false);

    }

}