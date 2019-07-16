package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.Constant;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventLevel;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.BaseServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventStatusLogDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventStatusLogServiceImpl extends BaseServiceImpl<EventStatusLogDao, EventStatusLogEntity> implements EventStatusLogService {

}