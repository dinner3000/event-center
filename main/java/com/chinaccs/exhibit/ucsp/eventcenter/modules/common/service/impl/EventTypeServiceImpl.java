package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.common.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dao.EventTypeDao;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dto.EventTypeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.entity.EventTypeEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.service.EventTypeService;
import org.apache.commons.lang3.StringUtils;
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
    public QueryWrapper<EventTypeEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<EventTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}