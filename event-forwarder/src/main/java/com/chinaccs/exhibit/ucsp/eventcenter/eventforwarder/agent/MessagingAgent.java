package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventForwardLogDTO;

public interface MessagingAgent {
    void invoke(EventForwardLogDTO forwardLogDTO);
}
