package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventLevel;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.ForwardNoticeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.IncomingEventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardConfigEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardConfigService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusLogService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.utils.ConvertUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.exception.IncomingEventDataValidationException;
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

            try {
                IncomingEventDTO incomingEventDTO = JSON.parseObject(message.toString(), IncomingEventDTO.class);
                if (incomingEventDTO.getId() == null) {
                    throw new IncomingEventDataValidationException("incoming event do not have valid id");
                }
                if (incomingEventDTO.getAppCode() == null) {
                    throw new IncomingEventDataValidationException("incoming event do not have valid app code");
                }

                if (!StringUtils.isEmpty(incomingEventDTO.getTitle()) || !StringUtils.isEmpty(incomingEventDTO.getMessage())) {
                    // treat as new event
                    EventEntity eventEntity = this.saveNewEvent(incomingEventDTO);

                    this.performForwardStage(incomingEventDTO, eventEntity);

                } else {
                    // treat as event status update
                    EventEntity eventEntity = eventService.getByTraceId(incomingEventDTO.getId(), incomingEventDTO.getAppCode());

                    if (eventEntity == null) {
                        throw new IncomingEventDataValidationException("incoming event do not exist for status update");
                    }

                    this.updateEventStatus(incomingEventDTO, eventEntity);
                }

                ack.acknowledge();

            } catch (IncomingEventDataValidationException e) {
                logger.error(e.getMessage());
                ack.acknowledge();
            } catch (Exception e) {
                throw e;
            } finally {
            }

        }
    }

    private EventEntity saveNewEvent(IncomingEventDTO incomingEventDTO) {
        EventEntity eventEntity = ConvertUtils.sourceToTarget(incomingEventDTO, EventEntity.class);

        if (eventEntity.getTypeId() == null) {
            throw new IncomingEventDataValidationException("event do not have valid type");
        }
        if (eventEntity.getLevel() == null || EventLevel.parse(eventEntity.getLevel()) == null) {
            throw new IncomingEventDataValidationException("event do not have valid level");
        }
        if (StringUtils.isEmpty(eventEntity.getTitle())) {
            throw new IncomingEventDataValidationException("event do not have valid title");
        }
        if (StringUtils.isEmpty(eventEntity.getMessage())) {
            throw new IncomingEventDataValidationException("event do not have valid message");
        }
        if (eventEntity.getOccurTime() == null) {
            throw new IncomingEventDataValidationException("event do not have valid occur time");
        }

        eventEntity.setTraceId(incomingEventDTO.getId());
        eventEntity.setId(null);
        eventEntity.setLogTime(new Date());
        eventEntity.setStatus(0);

        logger.debug("save event to db");
        eventService.insert(eventEntity);

        return eventEntity;
    }

    private void updateEventStatus(IncomingEventDTO incomingEventDTO, EventEntity eventEntity) {
        if (incomingEventDTO.getStatus() == null) {
            throw new RuntimeException("incoming event status is empty");
        }
        if (!EventStatus.isValidCode(incomingEventDTO.getStatus())) {
            throw new IncomingEventDataValidationException(String.format(
                    "incoming event do not have valid status: %s", incomingEventDTO.getStatus()));
        }
        if (eventEntity.getStatus() == EventStatus.REJECTED.getCode()) {
            throw new IncomingEventDataValidationException(String.format("Change %s status is not allowed", EventStatus.REJECTED.getName()));
        }
        if (eventEntity.getStatus() == EventStatus.RESOLVED.getCode()) {
            throw new IncomingEventDataValidationException(String.format("Change %s status is not allowed", EventStatus.RESOLVED.getName()));
        }
        if (StringUtils.isEmpty(incomingEventDTO.getOwner())) {
            throw new IncomingEventDataValidationException("incoming event do not have valid owner");
        }

        EventStatusLogEntity statusLogEntity = new EventStatusLogEntity();
        statusLogEntity.setId(null);
        statusLogEntity.setEventId(eventEntity.getId());
        statusLogEntity.setLogTime(new Date());
        statusLogEntity.setCurrStatus(incomingEventDTO.getStatus());
        statusLogEntity.setPrevStatus(eventEntity.getStatus());
        statusLogEntity.setOwner(incomingEventDTO.getOwner());
        statusLogEntity.setOvertime(incomingEventDTO.getOvertime() == null ? 0 : incomingEventDTO.getOvertime());
        statusLogService.insert(statusLogEntity);

        eventEntity.setStatus(incomingEventDTO.getStatus());
        if (incomingEventDTO.getStatus() == EventStatus.RESOLVED.getCode()) {
            eventEntity.setResolveTime(new Date());
        }
        EventEntity eventEntity4Update = new EventEntity();
        eventEntity4Update.setId(eventEntity.getId());
        eventEntity4Update.setStatus(eventEntity.getStatus());
        eventService.updateById(eventEntity4Update);

    }

    private void performForwardStage(IncomingEventDTO incomingEventDTO, EventEntity eventEntity) {
        EventForwardConfigEntity configEntity;
        do {
            try {
                logger.debug("try get event forward config");
                configEntity = eventForwardConfigService.tryGetOneConfig(
                        incomingEventDTO.getAppCode(), incomingEventDTO.getTypeId(), incomingEventDTO.getLevel());
                if (configEntity == null) {
                    logger.debug("event forward config not exist: {}/{}/{}, skip",
                            incomingEventDTO.getAppCode(),
                            incomingEventDTO.getTypeId(),
                            incomingEventDTO.getLevel()
                    );
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

            } catch (Exception e) {
                logger.error("unexpected error in performForwardStage(): {}", e.getMessage());
            }
        } while (false);

    }

}