package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventTypeDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventTypeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventTypeEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventTypeService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventTypeServiceImpl extends CrudServiceImpl<EventTypeDao, EventTypeEntity, EventTypeDTO> implements EventTypeService {

    @Override
    public QueryWrapper<EventTypeEntity> getWrapper(Map<String, Object> params) {
        return null;
    }
}