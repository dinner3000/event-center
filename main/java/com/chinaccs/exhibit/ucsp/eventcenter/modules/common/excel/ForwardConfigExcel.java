package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 事件推送配置 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
public class ForwardConfigExcel {
    @Excel(name = "ID")
    private Long id;
    @Excel(name = "名称")
    private String name;
    @Excel(name = "说明")
    private String desc;
    @Excel(name = "推送接口地址")
    private String apiUrl;
    @Excel(name = "推送类型 如：SMS，VOICE")
    private String type;
    @Excel(name = "推送目标列表 JSON数组")
    private String targetList;
    @Excel(name = "状态")
    private Integer status;

}