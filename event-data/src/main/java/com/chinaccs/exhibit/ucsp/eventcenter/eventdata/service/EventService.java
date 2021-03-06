package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.CrudService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;

import java.util.List;
import java.util.Map;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
public interface EventService extends CrudService<EventEntity, EventDTO> {
    List<Map<String, Object>> statGroupByLevel(Integer precision);
    List<Map<String, Object>> statGroupByType(Integer precision);
    List<Map<String, Object>> statGroupByAppCode(Integer precision);
    List<Map<String, Object>> statGroupByStatus(Integer precision);

    EventEntity getByTraceId(String traceId, String appCode);
//    Map<String, Object> statGroupByFWStatus();
}