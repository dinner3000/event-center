package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventForwardLogDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardLogService;
import org.springframework.stereotype.Service;

/**
 * 事件推送配置 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventForwardLogServiceImpl extends ServiceImpl<EventForwardLogDao, EventForwardLogEntity> implements EventForwardLogService {

}