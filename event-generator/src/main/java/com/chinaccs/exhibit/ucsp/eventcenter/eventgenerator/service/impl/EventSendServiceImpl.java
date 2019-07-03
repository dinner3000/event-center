package com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.config.GlobalParams;
import com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.service.EventSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventSendServiceImpl implements EventSendService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${global-params.mq-topic}")
    private String mqTopic;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void send() {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(System.currentTimeMillis());
        eventDTO.setAppCode("app-1");
        eventDTO.setTypeId(1L);
        eventDTO.setMessage("app-1,type-1,message");

        logger.info("topic: {}, message = {}", mqTopic, JSON.toJSONString(eventDTO));
        kafkaTemplate.send(mqTopic, JSON.toJSONString(eventDTO));
    }
}