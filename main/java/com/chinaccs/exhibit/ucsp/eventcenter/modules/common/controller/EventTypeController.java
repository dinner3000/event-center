package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.controller;

import com.chinaccs.exhibit.ucsp.eventcenter.common.annotation.LogOperation;
import com.chinaccs.exhibit.ucsp.eventcenter.common.constant.Constant;
import com.chinaccs.exhibit.ucsp.eventcenter.common.page.PageData;
import com.chinaccs.exhibit.ucsp.eventcenter.common.utils.ExcelUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.common.utils.Result;
import com.chinaccs.exhibit.ucsp.eventcenter.common.validator.AssertUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.common.validator.ValidatorUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.common.validator.group.AddGroup;
import com.chinaccs.exhibit.ucsp.eventcenter.common.validator.group.DefaultGroup;
import com.chinaccs.exhibit.ucsp.eventcenter.common.validator.group.UpdateGroup;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dto.EventTypeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.excel.EventTypeExcel;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.service.EventTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@RestController
@RequestMapping("common/eventtype")
@Api(tags="事件类型 ")
public class EventTypeController {
    @Autowired
    private EventTypeService eventTypeService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("common:eventtype:page")
    public Result<PageData<EventTypeDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<EventTypeDTO> page = eventTypeService.page(params);

        return new Result<PageData<EventTypeDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("common:eventtype:info")
    public Result<EventTypeDTO> get(@PathVariable("id") Long id){
        EventTypeDTO data = eventTypeService.get(id);

        return new Result<EventTypeDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("common:eventtype:save")
    public Result save(@RequestBody EventTypeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        eventTypeService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("common:eventtype:update")
    public Result update(@RequestBody EventTypeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        eventTypeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("common:eventtype:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        eventTypeService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("common:eventtype:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<EventTypeDTO> list = eventTypeService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, EventTypeExcel.class);
    }

}