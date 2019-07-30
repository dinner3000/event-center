package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventForwardType;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventForwardLogDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.ForwardNoticeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardConfigEntity;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class ForwardTaskMQTopicListenServiceImpl implements ForwardTaskMQTopicListenService {

    @Autowired
    private EventForwardLogDao eventForwardLogDao;

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

                if(eventForwardConfigEntity.getFwTargets() == null || StringUtils.isEmpty(eventForwardConfigEntity.getFwTargets())){
                    logger.debug("empty targets, skip");
                    break;
                }

                List<Integer> typeIdList = null;
                if (StringUtils.isEmpty(eventForwardConfigEntity.getFwType())) {
                    logger.debug("empty forward types, skip");
                    break;
                }

                List<String> buffer = Arrays.asList(eventForwardConfigEntity.getFwType().split(","));
                typeIdList = buffer.stream().map(i -> Convert.toInt(i)).collect(Collectors.toList());
                typeIdList.stream().forEach(i -> {
                    if (EventForwardType.parse(i) == null){
                        throw new RuntimeException(String.format("Invalid event forward type code: %d", i));
                    }
                });

                for(Integer typeId : typeIdList) {
                    EventForwardLogEntity eventForwardLogEntity = new EventForwardLogEntity();
                    eventForwardLogEntity.setId(eventEntity.getId());
                    eventForwardLogEntity.setTraceId(eventEntity.getTraceId());
                    eventForwardLogEntity.setAppCode(eventEntity.getAppCode());
                    eventForwardLogEntity.setTypeId(eventEntity.getTypeId());
                    eventForwardLogEntity.setLevel(eventEntity.getLevel());
                    eventForwardLogEntity.setConfigId(eventForwardConfigEntity.getId());
                    eventForwardLogEntity.setFwType(typeId);
                    eventForwardLogEntity.setTargets(eventForwardConfigEntity.getFwTargets());
                    eventForwardLogEntity.setTitle(eventEntity.getTitle());
                    eventForwardLogEntity.setMessage(eventEntity.getMessage());
                    eventForwardLogEntity.setRetries(0);
                    eventForwardLogEntity.setStatus(EventForwardStatus.INITIAL.getValue());

                    logger.debug("save forward task to db");
                    eventForwardLogDao.insert(eventForwardLogEntity);

                    ack.acknowledge();

                    logger.debug("try call forward api");
                    forwardTaskExecuteService.forward(eventForwardLogEntity);
                }

                logger.debug("====================================================================");
            } while (false);

        }
    }

}