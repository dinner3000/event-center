package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.dao.EventDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.EventRecordService;
import org.springframework.stereotype.Service;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventRecordServiceImpl extends ServiceImpl<EventDao, EventEntity> implements EventRecordService {

}