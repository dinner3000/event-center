package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
public class EventTypeExcel {
    @Excel(name = "ID")
    private Long id;
    @Excel(name = "名称")
    private String name;
    @Excel(name = "说明")
    private String desc;
    @Excel(name = "级别")
    private Integer level;
    @Excel(name = "源应用系统代码")
    private String appCode;
    @Excel(name = "是否需要推送 Y/N")
    private String fwNeeded;
    @Excel(name = "推送配置 JSON数组")
    private String fwConfigIds;
    @Excel(name = "状态")
    private Integer status;

}