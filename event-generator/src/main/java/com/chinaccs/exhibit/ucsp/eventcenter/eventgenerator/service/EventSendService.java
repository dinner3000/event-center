package com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.service;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
public interface EventSendService {

    void send(EventDTO eventDTO);

}