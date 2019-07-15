package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent.impl;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventForwardLogDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent.MessagingAgent;
import org.springframework.beans.factory.annotation.Value;

public class SMSMessagingAgent implements MessagingAgent {

    @Value("${external-api.sms.app-id}")
    private String appId;

    @Value("${external-api.sms.app-key}")
    private String addKey;

    @Override
    public void invoke(EventForwardLogDTO forwardLogDTO) {

    }
}
