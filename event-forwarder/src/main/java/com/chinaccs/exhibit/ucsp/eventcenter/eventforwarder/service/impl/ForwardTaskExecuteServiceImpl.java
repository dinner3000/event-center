package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.impl;

import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.constant.EventForwardStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.EventForwardLogRecordService;
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
    private EventForwardLogRecordService eventForwardLogRecordService;

    @Override
    public void forward(EventForwardLogEntity eventForwardLogEntity) {

        eventForwardLogEntity.setFwTime(new Date());
        try {
            logger.debug("forward to external api => url: {}, targets: {}",
                    eventForwardLogEntity.getFwUrl(), eventForwardLogEntity.getFwTargets());
            logger.debug("add real logic here");

            if (eventForwardLogEntity.getId() % 2 != 0) {
                throw new RuntimeException("Simulate forward failure");
            }

            eventForwardLogEntity.setStatus(EventForwardStatus.SUCCESS.getValue());
            eventForwardLogEntity.setFwResult(EventForwardStatus.SUCCESS.toString());
        } catch (Exception e){
            logger.error("Event forward failure: {}", e.getMessage());

            eventForwardLogEntity.setStatus(EventForwardStatus.FAILURE.getValue());
            eventForwardLogEntity.setRetry(eventForwardLogEntity.getRetry() + 1);
            eventForwardLogEntity.setFwResult(e.getMessage());
        }

        logger.debug("update forward task to db");
        eventForwardLogRecordService.updateById(eventForwardLogEntity);

    }
}
