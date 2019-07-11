package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventForwardConfigDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventForwardLogDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardConfigEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardConfigService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventForwardConfigServiceImpl extends CrudServiceImpl<EventForwardConfigDao, EventForwardConfigEntity, EventForwardLogDTO> implements EventForwardConfigService {

    @Override
    public QueryWrapper<EventForwardConfigEntity> getWrapper(Map<String, Object> params) {
        return null;
    }
}