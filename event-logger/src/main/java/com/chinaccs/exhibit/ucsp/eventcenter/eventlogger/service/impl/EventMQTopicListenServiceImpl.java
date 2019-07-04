package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dto.ForwardNoticeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.EventTypeEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventMQTopicListenService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventRecordService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventTypeRecordService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.ForwardTaskMQEnqueueService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.utils.ConvertUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private EventRecordService eventRecordService;

    @Autowired
    private EventTypeRecordService eventTypeRecordService;

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

            EventDTO eventDTO = JSON.parseObject(message.toString(), EventDTO.class);
            EventEntity eventEntity = ConvertUtils.sourceToTarget(eventDTO, EventEntity.class);
            eventEntity.setId(null);

            logger.debug("save event to db");
            eventRecordService.save(eventEntity);

            ack.acknowledge();

            EventTypeEntity typeEntity = null;
            do {
                if(eventDTO.getTypeId() == null || eventDTO.getTypeId() <= 0){
                    logger.debug("empty type id, skip");
                    break;
                }

                logger.debug("try get event type info");
                typeEntity = eventTypeRecordService.getById(eventEntity.getTypeId());
                if (typeEntity == null) {
                    logger.debug("event type id not exist: {}, skip", eventDTO.getTypeId());
                    logger.debug("possibly: 1. event type not created, 2. wrong type id");
                    break;
                }

                logger.debug("fill event type info to event entity");
                eventEntity.setLevel(typeEntity.getLevel());
                eventEntity.setTypeName(typeEntity.getName());
                eventEntity.setLogTime(new Date());
                eventEntity.setStatus(0);

                logger.debug("update event to db");
                eventRecordService.updateById(eventEntity);

                logger.debug("send forward message");
                ForwardNoticeDTO forwardNoticeDTO = new ForwardNoticeDTO(eventEntity, typeEntity);
                forwardTaskMQEnqueueService.notify(forwardNoticeDTO);

                logger.debug("====================================================================");
            } while (false);

        }
    }

}