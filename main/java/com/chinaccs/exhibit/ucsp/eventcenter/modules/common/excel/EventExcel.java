package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
public class EventExcel {
    @Excel(name = "ID 使用snowflake算法产生64位ID")
    private Long id;
    @Excel(name = "类型")
    private String typeId;
    @Excel(name = "内容")
    private String message;
    @Excel(name = "源应用系统代码")
    private String appCode;
    @Excel(name = "状态")
    private String status;

}