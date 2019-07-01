package com.chinaccs.exhibit.ucsp.eventcenter.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaccs.exhibit.ucsp.eventcenter.common.entity.ForwardConfigEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件推送配置 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Mapper
public interface ForwardConfigDao extends BaseMapper<ForwardConfigEntity> {
	
}