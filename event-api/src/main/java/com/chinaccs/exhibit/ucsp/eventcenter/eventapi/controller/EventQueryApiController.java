/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.Result;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.Constant;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.IncomingEventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.page.PageData;
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
@Api(tags = "测试接口")
public class EventQueryApiController {

    @Autowired
    private EventService eventService;

    @GetMapping("/page")
    @ApiOperation("分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.LEVEL, value = "事件级别", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = Constant.TYPE, value = "事件类型", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = Constant.APP_CODE, value = "来源", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.START_TIME, value = "起始时间", paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = Constant.END_TIME, value = "结束时间", paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<EventDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<EventDTO> pageData = eventService.page(params);
        return new Result<PageData<EventDTO>>().ok(pageData);
    }

    @GetMapping("/list")
    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.LEVEL, value = "事件级别", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = Constant.TYPE, value = "事件类型", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = Constant.APP_CODE, value = "来源", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.START_TIME, value = "起始时间", paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = Constant.END_TIME, value = "结束时间", paramType = "query", dataType = "Date")
    })
    public Result<List<EventDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<EventDTO> data = eventService.list(params);
        return new Result<List<EventDTO>>().ok(data);
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "事件id", paramType = "query", required = true, dataType = "long"),
            @ApiImplicitParam(name = "detailLevel", value = "数据详细程度", paramType = "query", dataType = "int")
    })
    public Result<EventDTO> getById(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") Integer detailLevel) {

        EventDTO dto = eventService.get(id);

        return new Result<EventDTO>().ok(dto);
    }

}
