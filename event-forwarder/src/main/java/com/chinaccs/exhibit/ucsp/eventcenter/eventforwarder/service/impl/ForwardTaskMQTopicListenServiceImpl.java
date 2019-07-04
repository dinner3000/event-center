package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.constant.EventForwardStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.dto.ForwardNoticeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.entity.EventTypeEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.EventForwardLogRecordService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.ForwardTaskExecuteService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.ForwardTaskMQTopicListenService;
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
public class ForwardTaskMQTopicListenServiceImpl implements ForwardTaskMQTopicListenService {

    @Autowired
    private EventForwardLogRecordService eventForwardLogRecordService;

    @Autowired
    private ForwardTaskExecuteService forwardTaskExecuteService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = "${global-params.forward-topic}", errorHandler = "eventListenErrorHandler")
    @Override
    @Transactional
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            logger.debug("receive => forward task: {}", message);

            do {
                ForwardNoticeDTO forwardNoticeDTO = JSON.parseObject(message.toString(), ForwardNoticeDTO.class);
                EventEntity eventEntity = forwardNoticeDTO.getEventEntity();
                EventTypeEntity eventTypeEntity = forwardNoticeDTO.getTypeEntity();

                if(eventEntity.getId() == null || eventEntity.getId() <= 0){
                    logger.debug("empty event id, skip");
                    break;
                }

                if(eventEntity.getTypeId() == null || eventEntity.getTypeId() <= 0){
                    logger.debug("empty type id, skip");
                    break;
                }

                if(eventTypeEntity.getFwTargets() == null || StringUtils.isEmpty(eventTypeEntity.getFwTargets())){
                    logger.debug("empty fwTargets, skip");
                    break;
                }

                if(eventTypeEntity.getFwUrl() == null || StringUtils.isEmpty(eventTypeEntity.getFwUrl())){
                    logger.debug("empty fwUrl, skip");
                    break;
                }

                EventForwardLogEntity eventForwardLogEntity = new EventForwardLogEntity();
                eventForwardLogEntity.setId(eventEntity.getId());
                eventForwardLogEntity.setTypeId(eventEntity.getTypeId());
                eventForwardLogEntity.setFwUrl(eventTypeEntity.getFwUrl());
                eventForwardLogEntity.setFwTargets(eventTypeEntity.getFwTargets());
                eventForwardLogEntity.setText(eventEntity.getMessage());
//            eventForwardLogEntity.setFwTime(null);
//            eventForwardLogEntity.setFwResult(null);
                eventForwardLogEntity.setRetry(0);
                eventForwardLogEntity.setStatus(EventForwardStatus.INITIAL.getValue());

                logger.debug("save forward task to db");
                eventForwardLogRecordService.save(eventForwardLogEntity);

                ack.acknowledge();

                logger.debug("try call forward api");
                forwardTaskExecuteService.forward(eventForwardLogEntity);

                logger.debug("====================================================================");
            } while (false);

        }
    }

}