package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.EventEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Mapper
public interface EventDao extends BaseMapper<EventEntity> {
	
}