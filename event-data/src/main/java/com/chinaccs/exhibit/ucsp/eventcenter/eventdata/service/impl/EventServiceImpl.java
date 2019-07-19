package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.Constant;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventLevel;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.page.PageData;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventService;
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
    public PageData<EventDTO> page(Map<String, Object> params) {
        return super.page(params);
    }

    @Override
    public List<Map<String, Object>> statGroupByLevel() {
        List<Map<String, Object>> list = this.baseDao.statGroupByLevel();
        for (Map<String, Object> item : list){
            item.put("label", EventLevel.parse(Convert.toInt(item.get("code"))).getName());
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> statGroupByType() {
        List<Map<String, Object>> list = this.baseDao.statGroupByType();
//        for (Map<String, Object> item : list){
//            item.put("label", String.format("事件类型%s", item.get("code")));
//        }
        return list;
    }

    @Override
    public List<Map<String, Object>> statGroupByAppCode() {
        List<Map<String, Object>> list = this.baseDao.statGroupByAppCode();
//        for (Map<String, Object> item : list){
//            item.put("label", item.get("code"));
//        }
        return list;
    }

    @Override
    public List<Map<String, Object>> statGroupByStatus() {
        List<Map<String, Object>> list = this.baseDao.statGroupByStatus();
        for (Map<String, Object> item : list){
            item.put("label", EventStatus.parse(Convert.toInt(item.get("code"))).getName());
        }
        return list;
    }

    @Override
    public EventEntity getByTraceId(String traceId, String appCode){

        QueryWrapper<EventEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("trace_id", traceId);
        queryWrapper.eq("app_code", appCode);

        EventEntity entity = this.baseDao.selectOne(queryWrapper);

        return entity;
    }

}