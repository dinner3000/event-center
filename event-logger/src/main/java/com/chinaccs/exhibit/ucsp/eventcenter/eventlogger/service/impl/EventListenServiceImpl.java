package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.EventListenErrorHandler;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.config.GlobalParams;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventListenService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.utils.ConvertUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = "${global-params.mq-topic}", errorHandler = "eventListenErrorHandler")
    @Override
    public void onMessage(ConsumerRecord<?, ?> record) throws Exception {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
//            logger.info("----------------- record =" + record);
            logger.info("message = {}", message);

            EventDTO dto = JSON.parseObject(message.toString(), EventDTO.class);
            EventEntity entity = ConvertUtils.sourceToTarget(dto, EventEntity.class);

            eventService.save(entity);

//            throw new Exception("unexpect error");

        }
    }

}