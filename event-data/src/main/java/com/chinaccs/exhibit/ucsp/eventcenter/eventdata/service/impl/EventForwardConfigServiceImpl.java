package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.Constant;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventForwardConfigDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventForwardConfigDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardConfigEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventForwardConfigServiceImpl extends CrudServiceImpl<EventForwardConfigDao, EventForwardConfigEntity, EventForwardConfigDTO> implements EventForwardConfigService {

    @Override
    public QueryWrapper<EventForwardConfigEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<EventForwardConfigEntity> queryWrapper = new QueryWrapper<>();

        Integer level = Convert.toInt(params.get(Constant.LEVEL), null);
        queryWrapper.eq(level != null, Constant.LEVEL, level);

        Integer type = Convert.toInt(params.get(Constant.TYPE_ID), null);
        queryWrapper.eq(type != null, "type_id", type);

        String appCode = Convert.toStr(params.get(Constant.APP_CODE), null);
        queryWrapper.eq(!StringUtils.isEmpty(appCode), "app_code", appCode);

        return queryWrapper;
    }

    @Override
    public EventForwardConfigEntity tryGetOneConfig(String appCode, Long typeId, Integer level){
        QueryWrapper<EventForwardConfigEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_code", appCode);
        queryWrapper.eq("type_id", typeId);
        queryWrapper.eq("level", level);
        EventForwardConfigEntity configEntity = this.baseDao.selectOne(queryWrapper);
        return configEntity;
    }
}