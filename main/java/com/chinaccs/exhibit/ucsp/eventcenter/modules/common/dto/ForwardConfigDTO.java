package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 事件推送配置 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
@ApiModel(value = "事件推送配置 ")
public class ForwardConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "说明")
	private String desc;

	@ApiModelProperty(value = "推送接口地址")
	private String apiUrl;

	@ApiModelProperty(value = "推送类型 如：SMS，VOICE")
	private String type;

	@ApiModelProperty(value = "推送目标列表 JSON数组")
	private String targetList;

	@ApiModelProperty(value = "状态")
	private Integer status;


}