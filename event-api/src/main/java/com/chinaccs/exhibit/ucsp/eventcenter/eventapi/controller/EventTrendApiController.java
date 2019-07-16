/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.CollectionUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.Result;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusStatEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 测试接口
 *
 * @author Zhu Jiawei zhujiawei@sunseaaiot.com
 */
@RestController
@RequestMapping("/trend")
@Api(tags = "事件统计趋势接口")
public class EventTrendApiController {
    @Autowired
    private EventStatusStatService eventStatusStatService;

    @GetMapping("/resolve/performance")
    @ApiOperation("统计事件处理时效")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始时间", paramType = "query", required = true, dataType = "date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", required = true, dataType = "date"),
            @ApiImplicitParam(name = "interval", value = "时间间隔", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "stages", value = "统计环节（数组）", paramType = "query", dataType = "int", allowMultiple = true),
            @ApiImplicitParam(name = "mock", value = "使用mock", paramType = "query", defaultValue = "0", dataType = "int")
    })
    public Result<Map<String, Object>> resolvePerformance(
            @RequestParam Date startTime,
            @RequestParam Date endTime,
            @RequestParam Integer interval,
            @RequestParam(required = false) String stages,
            @RequestParam(required = false, defaultValue = "true") boolean mock
    ) {

        Map<String, Object> data = new HashMap<>();
        if (mock) {
            data.put("正常处理", generateValueList(startTime, endTime, interval));
            data.put("超时处理", generateValueList(startTime, endTime, interval));
        } else {
            List<Integer> statusList = null;
            if (!StringUtils.isEmpty(stages)){
                List<String> buffer = Arrays.asList(stages.split(","));
                statusList = buffer.stream().map(i -> Convert.toInt(i)).collect(Collectors.toList());
            }
            data.put("正常处理", getValueList(startTime, endTime, interval, statusList, 0));
            data.put("超时处理", getValueList(startTime, endTime, interval, statusList, 1));
        }

        return new Result<Map<String, Object>>().ok(data);
    }

    private List<Map<String, Object>> generateValueList(Date startTime, Date endTime, Integer interval) {
        List<Map<String, Object>> list = new ArrayList<>();

        Random random = new Random(System.currentTimeMillis());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        do {
            list.add(CollectionUtils.buildLabelValueItem(random.nextInt(100), sdf.format(calendar.getTime())));
            calendar.add(Calendar.MINUTE, interval);
        } while (calendar.getTime().before(endTime));

        return list;
    }

    private List<Map<String, Object>> getValueList(Date startTime, Date endTime, Integer interval, List<Integer> statusList, Integer overtime) {
        List<Map<String, Object>> list = new ArrayList<>();

        QueryWrapper<EventStatusStatEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("stat_time, SUM(count) as count");
        queryWrapper.ge("stat_time", startTime);
        queryWrapper.le("stat_time", endTime);
        queryWrapper.eq("time_span", interval);
        queryWrapper.eq("overtime", overtime);
        queryWrapper.groupBy("stat_time");

        if (statusList != null && statusList.size() > 0){
            queryWrapper.in("status", statusList);
        }

        List<EventStatusStatEntity> statusStatEntityList = eventStatusStatService.list(queryWrapper);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (EventStatusStatEntity statusStatEntity : statusStatEntityList){
            list.add(CollectionUtils.buildLabelValueItem(
                    statusStatEntity.getCount(), sdf.format(statusStatEntity.getStatTime())));
        }
        return list;
    }

}