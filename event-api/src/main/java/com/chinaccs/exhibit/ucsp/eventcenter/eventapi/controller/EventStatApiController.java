/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author Zhu Jiawei zhujiawei@sunseaaiot.com
 */
@RestController
@RequestMapping("/stat")
@Api(tags="测试接口")
public class EventStatApiController {

    @GetMapping("/")
    @ApiOperation("测试")
    public Result<String> test(){
        return new Result<String>().ok("success");
    }

}
