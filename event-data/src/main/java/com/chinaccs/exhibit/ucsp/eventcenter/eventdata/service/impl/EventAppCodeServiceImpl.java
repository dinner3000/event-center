package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventAppCodeDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventAppCodeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventAppCodeEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventAppCodeService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventAppCodeServiceImpl extends CrudServiceImpl<EventAppCodeDao, EventAppCodeEntity, EventAppCodeDTO> implements EventAppCodeService {

    @Override
    public QueryWrapper<EventAppCodeEntity> getWrapper(Map<String, Object> params) {
        return null;
    }
}