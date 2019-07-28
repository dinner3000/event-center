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
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

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

    private void populateTotalNPercent(List<Map<String, Object>> list, Integer precision){
        Double totalCount = list.stream().mapToDouble(x -> Convert.toDouble(x.get(Constant.COUNT_NAME))).sum();
        for (Map<String, Object> item : list){
            item.put(Constant.TOTAL_NAME, Convert.toLong(totalCount));
            if (totalCount > 0) {
                item.put(Constant.PERCENT_NAME, StringUtil.getPercentString(
                        Convert.toFloat(Convert.toDouble(item.get(Constant.COUNT_NAME)) / totalCount), precision));
            } else {
                item.put(Constant.PERCENT_NAME, "N/A");
            }
        }
    }

    @Override
    public List<Map<String, Object>> statGroupByLevel(Integer precision) {
        List<Map<String, Object>> list = this.baseDao.statGroupByLevel();
        for (Map<String, Object> item : list){
            item.put(Constant.LABEL_NAME, EventLevel.parse(Convert.toInt(item.get(Constant.CODE_NAME))).getName());
        }
        this.populateTotalNPercent(list, precision);
        return list;
    }

    @Override
    public List<Map<String, Object>> statGroupByType(Integer precision) {
        List<Map<String, Object>> list = this.baseDao.statGroupByType();
        this.populateTotalNPercent(list, precision);
        return list;
    }

    @Override
    public List<Map<String, Object>> statGroupByAppCode(Integer precision) {
        List<Map<String, Object>> list = this.baseDao.statGroupByAppCode();
        this.populateTotalNPercent(list, precision);
        return list;
    }

    @Override
    public List<Map<String, Object>> statGroupByStatus(Integer precision) {
        List<Map<String, Object>> list = this.baseDao.statGroupByStatus();
        for (Map<String, Object> item : list){
            item.put(Constant.LABEL_NAME, EventStatus.parse(Convert.toInt(item.get(Constant.CODE_NAME))).getName());
        }
        this.populateTotalNPercent(list, precision);
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