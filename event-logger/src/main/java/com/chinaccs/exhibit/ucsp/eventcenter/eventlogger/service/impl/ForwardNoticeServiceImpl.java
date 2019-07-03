package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dto.ForwardNoticeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.ForwardNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ForwardNoticeServiceImpl implements ForwardNoticeService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${global-params.forward-topic}")
    private String mqTopic;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void notify(ForwardNoticeDTO forwardNoticeDTO) throws Exception {

        logger.info("topic: {}, message = {}", mqTopic, JSON.toJSONString(forwardNoticeDTO));
        kafkaTemplate.send(mqTopic, JSON.toJSONString(forwardNoticeDTO));
    }
}
