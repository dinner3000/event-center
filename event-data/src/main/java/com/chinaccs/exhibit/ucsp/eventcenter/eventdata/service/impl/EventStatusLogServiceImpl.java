package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.BaseServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventStatusLogDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusLogService;
import org.springframework.stereotype.Service;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventStatusLogServiceImpl extends BaseServiceImpl<EventStatusLogDao, EventStatusLogEntity> implements EventStatusLogService {

}