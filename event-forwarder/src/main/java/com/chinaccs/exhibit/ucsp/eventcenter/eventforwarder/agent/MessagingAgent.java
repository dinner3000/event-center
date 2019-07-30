package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;

import java.io.UnsupportedEncodingException;

public interface MessagingAgent {
    void invoke(EventForwardLogEntity forwardLogEntity) throws UnsupportedEncodingException;
}
