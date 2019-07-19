package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Mapper
public interface EventDao extends BaseMapper<EventEntity> {
    List<Map<String, Object>> statGroupByLevel();
    List<Map<String, Object>> statGroupByType();
    List<Map<String, Object>> statGroupByAppCode();
    List<Map<String, Object>> statGroupByStatus();}