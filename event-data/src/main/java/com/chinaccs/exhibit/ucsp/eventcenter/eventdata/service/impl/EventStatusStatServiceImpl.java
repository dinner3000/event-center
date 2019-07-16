package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.BaseServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventStatusStatDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusStatEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusStatService;
import org.springframework.stereotype.Service;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventStatusStatServiceImpl extends BaseServiceImpl<EventStatusStatDao, EventStatusStatEntity> implements EventStatusStatService {

}