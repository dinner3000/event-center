package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusStatEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Mapper
public interface EventStatusStatDao extends BaseMapper<EventStatusStatEntity> {
    void statResolvePerformance(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                               @Param("status") Integer status, @Param("overtime") Integer overTime,
                               @Param("statTime") Date statTime, @Param("timeSpan") Integer timeSpan);
}