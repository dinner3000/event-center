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

    @GetMapping("/byLevel")
    @ApiOperation("按级别统计数量")
    public Result<Map<String, Object>> byLevel() {
        Map<String, Object> data = eventService.statGroupByLevel();
        return new Result<Map<String, Object>>().ok(data);
    }

    @GetMapping("/byType")
    @ApiOperation("按类型统计数量")
    public Result<Map<String, Object>> byType() {
        Map<String, Object> data = eventService.statGroupByLevel();
        return new Result<Map<String, Object>>().ok(data);
    }

    @GetMapping("/byAppCode")
    @ApiOperation("按系统来源统计数量")
    public Result<Map<String, Object>> byAppCode() {
        Map<String, Object> data = eventService.statGroupByLevel();
        return new Result<Map<String, Object>>().ok(data);
    }

//    @GetMapping("/byFWStatus")
//    @ApiOperation("按推送结果数量")
//    public Result<PageData<EventDTO>> byFWStatus() {
//        PageData<EventDTO> pageData = eventService.page();
//        return new Result<PageData<EventDTO>>().ok(pageData);
//    }

}