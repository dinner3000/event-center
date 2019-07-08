package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.Constant;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
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

        QueryWrapper<EventEntity> queryWrapper = new QueryWrapper<>();

        Integer level = Convert.toInt(params.get(Constant.LEVEL), null);
        queryWrapper.eq(level != null, Constant.LEVEL, level);

        Integer type = Convert.toInt(params.get(Constant.TYPE_ID), null);
        queryWrapper.eq(type != null, "type_id", type);

        String appCode = Convert.toStr(params.get(Constant.APP_CODE), null);
        queryWrapper.eq(!StringUtils.isEmpty(appCode), "app_code", appCode);

        Date startTime = Convert.toDate(params.get(Constant.START_TIME), null);
        queryWrapper.ge(startTime != null, "occur_time", startTime);

        Date endTime = Convert.toDate(params.get(Constant.END_TIME), null);
        queryWrapper.le(endTime != null, "occur_time", endTime);

        return queryWrapper;
    }

    @Override
    public Map<String, Object> statGroupByLevel() {
        return this.baseDao.statGroupByLevel();
    }

    @Override
    public Map<String, Object> statGroupByType() {
        return this.baseDao.statGroupByType();
    }

    @Override
    public Map<String, Object> statGroupByAppCode() {
        return this.baseDao.statGroupByAppCode();
    }

//    @Override
//    public Map<String, Object> statGroupByFWStatus() {
//        return null;
//    }
}