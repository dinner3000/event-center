package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.service;

import com.chinaccs.exhibit.ucsp.eventcenter.common.service.CrudService;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.entity.EventEntity;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
public interface EventService extends CrudService<EventEntity, EventDTO> {

}