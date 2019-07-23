/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.CollectionUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.Result;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventForwardType;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventLevel;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventAppCodeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventTypeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventAppCodeService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试接口
 *
 * @author Zhu Jiawei zhujiawei@sunseaaiot.com
 */
@RestController
@RequestMapping("/enum")
@Api(tags = "事件常量定义接口")
public class EventEnumApiController {

    @Autowired
    private EventTypeService eventTypeService;

    @Autowired
    private EventAppCodeService appCodeService;

    @GetMapping("/app_code_list")
    @ApiOperation("应用系统列表")
    public Result<List<EventAppCodeDTO>> getAppCodeList() {

        List<EventAppCodeDTO> list = appCodeService.list(new HashMap<>());

        return new Result<List<EventAppCodeDTO>>().ok(list);
    }

    @GetMapping("/forward_type_list")
    @ApiOperation("通知渠道列表")
    public Result<List<Map<String, Object>>> getForwardTypeList() {

        return new Result<List<Map<String, Object>>>().ok(EventForwardType.toPropertyMapList());
    }

    @GetMapping("/event_type_list")
    @ApiOperation("事件类型列表")
    public Result<List<EventTypeDTO>> getEventTypeList() {

        List<EventTypeDTO> list = eventTypeService.list(new HashMap<>());

        return new Result<List<EventTypeDTO>>().ok(list);
    }

    @GetMapping("/event_level_list")
    @ApiOperation("事件级别列表")
    public Result<List<Map<String, Object>>> getEventLevelList() {

        return new Result<List<Map<String, Object>>>().ok(EventLevel.toPropertyMapList());
    }

    @GetMapping("/event_status_list")
    @ApiOperation("事件状态列表")
    public Result<List<Map<String, Object>>> getEventStatusList() {

        return new Result<List<Map<String, Object>>>().ok(EventStatus.toPropertyMapList());
    }

}
