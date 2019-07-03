package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.EventListenErrorHandler;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.config.GlobalParams;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dto.ForwardNoticeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.EventTypeEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventListenService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventTypeService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.ForwardNoticeService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.utils.ConvertUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventListenServiceImpl implements EventListenService {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventTypeService eventTypeService;

    @Autowired
    private ForwardNoticeService forwardNoticeService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = "${global-params.event-topic}", errorHandler = "eventListenErrorHandler")
    @Override
    public void onMessage(ConsumerRecord<?, ?> record) throws Exception {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            logger.info("message = {}", message);

            EventDTO eventDTO = JSON.parseObject(message.toString(), EventDTO.class);
            EventEntity eventEntity = ConvertUtils.sourceToTarget(eventDTO, EventEntity.class);

            EventTypeEntity typeEntity = null;
            do {
                if(eventDTO.getTypeId() == null || eventDTO.getTypeId() <= 0){
                    break;
                }

                typeEntity = eventTypeService.getById(eventEntity.getTypeId());
                if (typeEntity == null) {
                    // TypeId not exist means: 1. forward config not created, 2. wrong type id;
                    // no way to distinguish these 2 cases
                    logger.debug("event type id not exist: {}", eventDTO.getTypeId());
                    eventService.save(eventEntity);
                    break;
                }

                eventEntity.setLevel(typeEntity.getLevel());
                eventEntity.setTypeName(typeEntity.getName());
                eventEntity.setLogTime(new Date());
                eventEntity.setStatus(0);
                eventService.save(eventEntity);

                ForwardNoticeDTO forwardNoticeDTO = new ForwardNoticeDTO(eventEntity, typeEntity);
                forwardNoticeService.notify(forwardNoticeDTO);
            } while (false);

        }
    }

}