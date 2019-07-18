package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import javax.swing.event.HyperlinkEvent;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Mapper
public interface EventTypeDao extends BaseMapper<EventTypeEntity> {
}