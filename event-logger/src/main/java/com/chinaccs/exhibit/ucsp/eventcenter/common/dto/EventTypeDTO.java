package com.chinaccs.exhibit.ucsp.eventcenter.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
@ApiModel(value = "事件类型 ")
public class EventTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "说明")
	private String desc;

	@ApiModelProperty(value = "级别")
	private Integer level;

	@ApiModelProperty(value = "源应用系统代码")
	private String appCode;

	@ApiModelProperty(value = "是否需要推送 Y/N")
	private String fwNeeded;

	@ApiModelProperty(value = "推送配置 JSON数组")
	private String fwConfigIds;

	@ApiModelProperty(value = "状态")
	private Integer status;


}