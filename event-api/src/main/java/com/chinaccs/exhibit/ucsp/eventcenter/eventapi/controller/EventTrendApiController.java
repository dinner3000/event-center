/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.common.exception.ErrorCode;
import com.chinaccs.exhibit.ucsp.eventcenter.common.exception.EventCenterException;
import com.chinaccs.exhibit.ucsp.eventcenter.common.utils.DateUtils;
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
            @ApiImplicitParam(name = "startTime", value = "起始时间，格式：2019-07-17 12:00:00", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间，格式：2019-07-17 13:00:00", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "interval", value = "时间间隔，可选值：5，120，1440", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "stages", value = "统计环节（数组），可选值，1，2，3，4", paramType = "query", dataType = "int", allowMultiple = true),
            @ApiImplicitParam(name = "mock", value = "使用mock", paramType = "query", defaultValue = "0", dataType = "int")
    })
    public Result<Map<String, Object>> resolvePerformance(
            @RequestParam("startTime") String startTimeStr,
            @RequestParam("endTime") String endTimeStr,
            @RequestParam Integer interval,
            @RequestParam(required = false) String stages,
            @RequestParam(required = false, defaultValue = "false") boolean mock
    ) {
        // parse and validate stages
        List<Integer> statusList = null;
        if (!StringUtils.isEmpty(stages)){
            List<String> buffer = Arrays.asList(stages.split(","));
            statusList = buffer.stream().map(i -> Convert.toInt(i)).collect(Collectors.toList());
            statusList.stream().forEach(i -> {
                if (EventStatus.parse(i) == null){
                    throw new EventCenterException(ErrorCode.PARAMS_GET_ERROR, String.format("Invalid stage code: %d", i));
                }
            });
        }

        // validate interval
        List<Integer> validIntervals = Arrays.asList(5, 120, 1440);
        if(!validIntervals.contains(interval)){
            throw new EventCenterException(ErrorCode.PARAMS_GET_ERROR, String.format("Invalid interval: %d", interval));
        }

        Map<String, Object> data = new HashMap<>();
        Date startTime = DateUtils.parse(startTimeStr, DateUtils.DATE_TIME_PATTERN);
        Date endTime = DateUtils.parse(endTimeStr, DateUtils.DATE_TIME_PATTERN);

        if (startTime.after(endTime)){
            throw new EventCenterException(ErrorCode.PARAMS_GET_ERROR, "start time cannot be later than end time");
        }

        if (mock) {
            data.put("正常处理", generateValueList(startTime, endTime, interval));
            data.put("超时处理", generateValueList(startTime, endTime, interval));
        } else {
            data.put("正常处理", getValueList(startTime, endTime, interval, statusList, 0));
            data.put("超时处理", getValueList(startTime, endTime, interval, statusList, 1));
        }

        return new Result<Map<String, Object>>().ok(data);
    }

    private List<Map<String, Object>> generateValueList(Date startTime, Date endTime, Integer interval) {
        List<Map<String, Object>> list = new ArrayList<>();

        Random random = new Random(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        do {
            list.add(CollectionUtils.buildLabelValueItem(random.nextInt(100), DateUtils.format(calendar.getTime(), DateUtils.DATE_TIME_PATTERN)));
            calendar.add(Calendar.MINUTE, interval);
        } while (calendar.getTime().before(endTime));

        return list;
    }

    private List<Map<String, Object>> getValueList(Date startTime, Date endTime, Integer interval, List<Integer> statusList, Integer overtime) {
        List<Map<String, Object>> list = new ArrayList<>();

        QueryWrapper<EventStatusStatEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("stat_time, SUM(count) as count");
        queryWrapper.ge(startTime != null, "stat_time", startTime);
        queryWrapper.le(endTime != null, "stat_time", endTime);
        queryWrapper.eq(interval != null, "time_span", interval);
        queryWrapper.eq(overtime != null, "overtime", overtime);
        queryWrapper.groupBy("stat_time");

        queryWrapper.in(statusList != null && statusList.size() > 0, "status", statusList);

        List<EventStatusStatEntity> statusStatEntityList = eventStatusStatService.list(queryWrapper);

        for (EventStatusStatEntity statusStatEntity : statusStatEntityList){
            list.add(CollectionUtils.buildLabelValueItem(
                    statusStatEntity.getCount(), DateUtils.format(statusStatEntity.getStatTime(), DateUtils.DATE_TIME_PATTERN)));
        }
        return list;
    }

}