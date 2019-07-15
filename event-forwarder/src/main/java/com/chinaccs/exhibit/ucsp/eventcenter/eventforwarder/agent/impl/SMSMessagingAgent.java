package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent.impl;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent.MessagingAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class SMSMessagingAgent implements MessagingAgent {

    @Value("${external-api.sms.app-id}")
    private String appId;

    @Value("${external-api.sms.app-key}")
    private String addKey;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void invoke(EventForwardLogEntity forwardLogEntity) {
        logger.debug("SMSMessagingAgent.invoke()");
    }
}
