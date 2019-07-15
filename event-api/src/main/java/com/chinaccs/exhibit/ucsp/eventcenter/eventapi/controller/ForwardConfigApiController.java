/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import com.chinaccs.exhibit.ucsp.eventcenter.common.validator.AssertUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.common.validator.ValidatorUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.common.validator.group.AddGroup;
import com.chinaccs.exhibit.ucsp.eventcenter.common.validator.group.DefaultGroup;
import com.chinaccs.exhibit.ucsp.eventcenter.common.validator.group.UpdateGroup;
import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.Result;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.Constant;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventForwardConfigDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.page.PageData;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardConfigService;
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
@RequestMapping("/config")
@Api(tags = "事件推送设置接口")
public class ForwardConfigApiController {

    @Autowired
    private EventForwardConfigService forwardConfigService;

    @GetMapping("/page")
    @ApiOperation("事件推送设置分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.LEVEL, value = "事件级别", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = Constant.TYPE_ID, value = "事件类型", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = Constant.APP_CODE, value = "来源", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<EventForwardConfigDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<EventForwardConfigDTO> pageData = forwardConfigService.page(params);
        return new Result<PageData<EventForwardConfigDTO>>().ok(pageData);
    }

    @GetMapping("/list")
    @ApiOperation("事件推送设置列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.LEVEL, value = "事件级别", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = Constant.TYPE_ID, value = "事件类型", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = Constant.APP_CODE, value = "来源", paramType = "query", dataType = "String"),
    })
    public Result<List<EventForwardConfigDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<EventForwardConfigDTO> data = forwardConfigService.list(params);
        return new Result<List<EventForwardConfigDTO>>().ok(data);
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("事件推送设置单个查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "事件id", paramType = "path", required = true, dataType = "long")
    })
    public Result<EventForwardConfigDTO> getById(@PathVariable("id") Long id) {

        EventForwardConfigDTO dto = forwardConfigService.get(id);

        return new Result<EventForwardConfigDTO>().ok(dto);
    }

    @PostMapping
    @ApiOperation("保存单个事件推送设置")
    public Result save(@RequestBody EventForwardConfigDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        forwardConfigService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改单个事件推送设置")
    public Result update(@RequestBody EventForwardConfigDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        forwardConfigService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除单个事件推送设置")
    public Result delete(@RequestBody Long id){

        forwardConfigService.deleteById(id);

        return new Result();
    }
}
