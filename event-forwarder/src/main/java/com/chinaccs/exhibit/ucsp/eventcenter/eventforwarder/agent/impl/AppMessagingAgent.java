package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent.impl;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent.MessagingAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppMessagingAgent implements MessagingAgent {

    @Value("${external-api.app-msg.url}")
    private String url;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void invoke(EventForwardLogEntity forwardLogEntity) {
        logger.debug("AppMessagingAgent.invoke()");
    }
}
