package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;

public interface MessagingAgent {
    void invoke(EventForwardLogEntity forwardLogEntity);
}
