package com.chinaccs.exhibit.ucsp.eventcenter.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.common.dao.EventTypeDao;
import com.chinaccs.exhibit.ucsp.eventcenter.common.entity.EventTypeEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.common.service.EventTypeService;
import org.springframework.stereotype.Service;

/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventTypeServiceImpl extends ServiceImpl<EventTypeDao, EventTypeEntity> implements EventTypeService {

}