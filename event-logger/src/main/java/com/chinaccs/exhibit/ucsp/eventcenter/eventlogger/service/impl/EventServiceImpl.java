package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dao.EventDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.EventTypeEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventTypeService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventDao, EventEntity> implements EventService {

}