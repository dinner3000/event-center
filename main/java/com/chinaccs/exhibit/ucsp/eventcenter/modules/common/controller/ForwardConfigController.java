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
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dto.ForwardConfigDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.excel.ForwardConfigExcel;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.service.ForwardConfigService;
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
 * 事件推送配置 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@RestController
@RequestMapping("common/forwardconfig")
@Api(tags="事件推送配置 ")
public class ForwardConfigController {
    @Autowired
    private ForwardConfigService forwardConfigService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("common:forwardconfig:page")
    public Result<PageData<ForwardConfigDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<ForwardConfigDTO> page = forwardConfigService.page(params);

        return new Result<PageData<ForwardConfigDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("common:forwardconfig:info")
    public Result<ForwardConfigDTO> get(@PathVariable("id") Long id){
        ForwardConfigDTO data = forwardConfigService.get(id);

        return new Result<ForwardConfigDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("common:forwardconfig:save")
    public Result save(@RequestBody ForwardConfigDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        forwardConfigService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("common:forwardconfig:update")
    public Result update(@RequestBody ForwardConfigDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        forwardConfigService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("common:forwardconfig:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        forwardConfigService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("common:forwardconfig:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<ForwardConfigDTO> list = forwardConfigService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, ForwardConfigExcel.class);
    }

}