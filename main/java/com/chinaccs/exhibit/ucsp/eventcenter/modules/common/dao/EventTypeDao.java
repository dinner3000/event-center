package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dao;

import com.chinaccs.exhibit.ucsp.eventcenter.common.dao.BaseDao;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.entity.EventTypeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Mapper
public interface EventTypeDao extends BaseDao<EventTypeEntity> {
	
}