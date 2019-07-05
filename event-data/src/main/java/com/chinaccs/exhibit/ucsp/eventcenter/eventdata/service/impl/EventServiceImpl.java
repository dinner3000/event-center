package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.BaseServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventServiceImpl extends CrudServiceImpl<EventDao, EventEntity, EventDTO> implements EventService {

    @Override
    public QueryWrapper<EventEntity> getWrapper(Map<String, Object> params) {
        return null;
    }
}