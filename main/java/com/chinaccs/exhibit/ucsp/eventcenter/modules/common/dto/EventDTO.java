package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
@ApiModel(value = "事件 存储事件信息，每个事件作为一条记录")
public class EventDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID 使用snowflake算法产生64位ID")
	private Long id;

	@ApiModelProperty(value = "类型")
	private String typeId;

	@ApiModelProperty(value = "内容")
	private String message;

	@ApiModelProperty(value = "源应用系统代码")
	private String appCode;

	@ApiModelProperty(value = "状态")
	private String status;


}