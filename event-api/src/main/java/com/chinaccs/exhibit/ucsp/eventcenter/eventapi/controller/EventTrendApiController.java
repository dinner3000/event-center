/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.CollectionUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.Result;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    private EventService eventService;

    @GetMapping("/resolve/performance")
    @ApiOperation("统计事件处理时效")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始时间", paramType = "path", required = true, dataType = "date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "path", required = true, dataType = "date"),
            @ApiImplicitParam(name = "interval", value = "时间间隔", paramType = "path", required = true, dataType = "int"),
            @ApiImplicitParam(name = "stages", value = "统计环节（数组）", paramType = "query", dataType = "int", allowMultiple = true)
    })
    public Result<Map<String, Object>> resolvePerformance(
            @RequestParam Date startTime,
            @RequestParam Date endTime,
            @RequestParam Integer interval,
            @RequestParam Integer[] stages
    ) {

        Map<String, Object> data = new HashMap<>();
        data.put("正常处理", getValueList(startTime, endTime, interval));
        data.put("超时处理", getValueList(startTime, endTime, interval));

        return new Result<Map<String, Object>>().ok(data);
    }

    private List<Map<String, Object>> getValueList(Date startTime, Date endTime, Integer interval){
        List<Map<String, Object>> list = new ArrayList<>();

        Random random = new Random(System.currentTimeMillis());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<String> timeList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        do {
            list.add(CollectionUtils.buildLabelValueItem(random.nextInt(100), sdf.format(calendar.getTime())));
            calendar.add(Calendar.MINUTE, interval);
        }while (calendar.before(endTime));

        return list;
    }

}