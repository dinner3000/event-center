package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.common.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dao.EventDao;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.service.EventService;
import org.apache.commons.lang3.StringUtils;
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
    public QueryWrapper<EventEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<EventEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}