/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import com.chinaccs.exhibit.ucsp.eventcenter.common.constant.Constant;
import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.Result;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.IncomingEventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.utils.ConvertUtils;
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
@RequestMapping("/query")
@Api(tags="测试接口")
public class EventQueryApiController {

    @Autowired
    private EventService eventService;

    @GetMapping("/page")
    @ApiOperation("分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<List<IncomingEventDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        return new Result<List<IncomingEventDTO>>().ok(null);
    }

    @GetMapping("/list")
    @ApiOperation("列表")
    public Result<List<IncomingEventDTO>> list(){
        return new Result<List<IncomingEventDTO>>().ok(null);
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "事件id", paramType = "query", required = true, dataType="long") ,
            @ApiImplicitParam(name = "detailLevel", value = "数据详细程度", paramType = "query", dataType="int")
    })
    public Result<EventDTO> getById(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") Integer detailLevel){

        EventEntity entity = eventService.selectById(id);
        EventDTO dto = ConvertUtils.sourceToTarget(entity, EventDTO.class);

        return new Result<EventDTO>().ok(dto);
    }

}
