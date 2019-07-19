/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.Result;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.Constant;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.page.PageData;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardLogService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 测试接口
 *
 * @author Zhu Jiawei zhujiawei@sunseaaiot.com
 */
@RestController
@RequestMapping("/stat")
@Api(tags = "事件统计接口")
public class EventStatApiController {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventForwardLogService forwardLogService;

    @GetMapping("/by_level")
    @ApiOperation("按级别统计数量")
    public Result<List<Map<String, Object>>> byLevel() {
        List<Map<String, Object>> data = eventService.statGroupByLevel();
        return new Result<List<Map<String, Object>>>().ok(data);
    }

    @GetMapping("/by_type")
    @ApiOperation("按类型统计数量")
    public Result<List<Map<String, Object>>> byType() {
        List<Map<String, Object>> data = eventService.statGroupByType();
        return new Result<List<Map<String, Object>>>().ok(data);
    }

    @GetMapping("/by_app_code")
    @ApiOperation("按系统来源统计数量")
    public Result<List<Map<String, Object>>> byAppCode() {
        List<Map<String, Object>> data = eventService.statGroupByAppCode();
        return new Result<List<Map<String, Object>>>().ok(data);
    }

    @GetMapping("/by_status")
    @ApiOperation("按事件状态统计数量")
    public Result<List<Map<String, Object>>> byStatus() {
        List<Map<String, Object>> pageData = eventService.statGroupByStatus();
        return new Result<List<Map<String, Object>>>().ok(pageData);
    }

}