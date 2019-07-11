package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.ForwardNoticeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardConfigEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardLogService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.constant.EventForwardStatus;
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
    private EventForwardLogService eventForwardLogService;

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
                EventForwardConfigEntity eventForwardConfigEntity = forwardNoticeDTO.getTypeEntity();

                if(eventEntity.getId() == null || eventEntity.getId() <= 0){
                    logger.debug("empty event id, skip");
                    break;
                }

                if(eventEntity.getTypeId() == null || eventEntity.getTypeId() <= 0){
                    logger.debug("empty type id, skip");
                    break;
                }

                if(eventForwardConfigEntity.getFwTargets() == null || StringUtils.isEmpty(eventForwardConfigEntity.getFwTargets())){
                    logger.debug("empty targets, skip");
                    break;
                }

                EventForwardLogEntity eventForwardLogEntity = new EventForwardLogEntity();
                eventForwardLogEntity.setId(eventEntity.getId());
                eventForwardLogEntity.setConfigId(eventEntity.getTypeId());
                eventForwardLogEntity.setTargets(eventForwardConfigEntity.getFwTargets());
                eventForwardLogEntity.setText(eventEntity.getMessage());
                eventForwardLogEntity.setRetries(0);
                eventForwardLogEntity.setStatus(EventForwardStatus.INITIAL.getValue());

                logger.debug("save forward task to db");
                eventForwardLogService.save(eventForwardLogEntity);

                ack.acknowledge();

                logger.debug("try call forward api");
                forwardTaskExecuteService.forward(eventForwardLogEntity);

                logger.debug("====================================================================");
            } while (false);

        }
    }

}