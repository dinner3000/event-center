package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.BaseService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.CrudService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
public interface EventStatusLogService extends BaseService<EventStatusLogEntity> {
}