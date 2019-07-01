package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dao;

import com.chinaccs.exhibit.ucsp.eventcenter.common.dao.BaseDao;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.entity.EventEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Mapper
public interface EventDao extends BaseDao<EventEntity> {
	
}