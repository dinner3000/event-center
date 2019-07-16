/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.CollectionUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.Result;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventLevel;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/app_code_list")
    @ApiOperation("应用系统列表")
    public Result<List<Map<String, Object>>> getAppCodeList() {

        List<Map<String, Object>> list = new ArrayList<>();
        for(int i=1; i<=10; i++){
            list.add(CollectionUtils.buildLabelCodeItem(
                    String.format("appCode%d", i),
                    String.format("应用系统%d", i)
                    ));
        }

        return new Result<List<Map<String, Object>>>().ok(list);
    }

    @GetMapping("/forward_type_list")
    @ApiOperation("通知渠道列表")
    public Result<List<Map<String, Object>>> getForwardTypeList() {

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(CollectionUtils.buildLabelCodeItem(1, "短信"));
        list.add(CollectionUtils.buildLabelCodeItem(2, "语音"));

        return new Result<List<Map<String, Object>>>().ok(list);
    }

    @GetMapping("/event_type_list/{appCode}")
    @ApiOperation("事件类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appCode", value = "源应用系统代码", paramType = "path", required = true, dataType = "String")
    })
    public Result<List<Map<String, Object>>> getEventTypeList(@PathVariable("appCode") String appCode) {

        List<Map<String, Object>> list = new ArrayList<>();
        for(int i=1; i<=10; i++){
            list.add(CollectionUtils.buildLabelCodeItem(
                    String.format("事件类型%d", i),
                    String.format("系统%s-类型%d", appCode, i)
            ));
        }

        return new Result<List<Map<String, Object>>>().ok(list);
    }

    @GetMapping("/event_level_list")
    @ApiOperation("应用系统列表")
    public Result<List<Map<String, Object>>> getEventLevelList() {

        return new Result<List<Map<String, Object>>>().ok(EventLevel.toPropertyMapList());
    }

    @GetMapping("/event_status_list")
    @ApiOperation("应用系统列表")
    public Result<List<Map<String, Object>>> getEventStatusList() {

        return new Result<List<Map<String, Object>>>().ok(EventStatus.toPropertyMapList());
    }

}
