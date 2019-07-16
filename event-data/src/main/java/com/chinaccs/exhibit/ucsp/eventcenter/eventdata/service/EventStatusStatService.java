package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.BaseService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.CrudService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusStatEntity;

import java.util.Date;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
public interface EventStatusStatService extends IService<EventStatusStatEntity> {
    void statResolvePerformance(Date statTime, Integer interval);
    void statResolvePerformance(Date startTime, Date endTime, Integer interval);
}