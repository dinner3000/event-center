package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.impl;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardLogService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent.MessagingAgent;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.constant.EventForwardStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.ForwardTaskExecuteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ForwardTaskExecuteServiceImpl implements ForwardTaskExecuteService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EventForwardLogService eventForwardLogService;

    @Autowired
    private MessagingAgent smsMessagingAgent;

    @Override
    public void forward(EventForwardLogEntity eventForwardLogEntity) {

        eventForwardLogEntity.setFwTime(new Date());
        try {
            logger.debug("forward to external api => url: {}, targets: {}",
                eventForwardLogEntity.getTplText(), eventForwardLogEntity.getTargets());
            smsMessagingAgent.invoke(eventForwardLogEntity);

            if (eventForwardLogEntity.getId() % 2 != 0) {
                throw new RuntimeException("Simulate forward failure");
            }

            eventForwardLogEntity.setStatus(EventForwardStatus.SUCCESS.getValue());
            eventForwardLogEntity.setFwResult(EventForwardStatus.SUCCESS.toString());
        } catch (Exception e){
            logger.error("Event forward failure: {}", e.getMessage());

            eventForwardLogEntity.setStatus(EventForwardStatus.FAILURE.getValue());
            eventForwardLogEntity.setRetries(eventForwardLogEntity.getRetries() + 1);
            eventForwardLogEntity.setFwResult(e.getMessage());
        }

        logger.debug("update forward task to db");
        eventForwardLogService.updateById(eventForwardLogEntity);

    }
}
