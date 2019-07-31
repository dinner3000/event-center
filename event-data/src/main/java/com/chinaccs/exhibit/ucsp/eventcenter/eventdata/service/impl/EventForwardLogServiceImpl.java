package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventForwardLogDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventForwardLogDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardLogService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 事件推送配置 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventForwardLogServiceImpl extends CrudServiceImpl<EventForwardLogDao, EventForwardLogEntity, EventForwardLogDTO> implements EventForwardLogService {

    @Override
    public QueryWrapper<EventForwardLogEntity> getWrapper(Map<String, Object> params) {
        return null;
    }
}