package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.IncomingEventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.ForwardNoticeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardConfigEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardConfigService;
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
            EventEntity eventEntity = ConvertUtils.sourceToTarget(incomingEventDTO, EventEntity.class);

            eventEntity.setId(null);
            eventEntity.setLogTime(new Date());
            if (incomingEventDTO.getStatus() == null) {
                eventEntity.setStatus(0);
            }

            // eventEntity.setConfigId(0L);

            logger.debug("save event to db");
            eventService.insert(eventEntity);

            ack.acknowledge();

            EventForwardConfigEntity typeEntity = null;
            do {
                if(incomingEventDTO.getTypeId() == null || incomingEventDTO.getTypeId() <= 0){
                    logger.debug("empty type id, skip");
                    break;
                }

                logger.debug("try get event type info");
                typeEntity = eventForwardConfigService.selectById(incomingEventDTO.getTypeId());
                if (typeEntity == null) {
                    logger.debug("event type id not exist: {}, skip", incomingEventDTO.getTypeId());
                    logger.debug("possibly: 1. event type not created, 2. wrong type id");
                    break;
                }

                logger.debug("send forward signal");
                ForwardNoticeDTO forwardNoticeDTO = new ForwardNoticeDTO(eventEntity, typeEntity);
                forwardTaskMQEnqueueService.notify(forwardNoticeDTO);

                logger.debug("====================================================================");
            } while (false);

        }
    }

}