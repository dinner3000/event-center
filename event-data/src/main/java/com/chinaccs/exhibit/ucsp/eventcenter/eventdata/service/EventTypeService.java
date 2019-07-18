package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.CrudService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventAppCodeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventTypeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventAppCodeEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventTypeEntity;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
public interface EventTypeService extends CrudService<EventTypeEntity, EventTypeDTO> {
}