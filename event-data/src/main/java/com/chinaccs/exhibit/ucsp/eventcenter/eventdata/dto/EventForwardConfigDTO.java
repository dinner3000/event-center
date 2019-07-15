package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 事件推送配置 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
@ApiModel(value = "事件推送配置")
public class EventForwardConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "说明")
	private String desc;

	@ApiModelProperty(value = "是否启用")
	private String appCode;

	@ApiModelProperty(value = "事件等级")
	private Integer level;

	@ApiModelProperty(value = "是否启用")
	private Integer fwEnabled;

	@ApiModelProperty(value = "推送类型")
	private String fwType;

	@ApiModelProperty(value = "推送目标")
	private String fwTargets;

	@ApiModelProperty(value = "推送内容模版")
	private String fwTplText;

	@ApiModelProperty(value = "推送内容模版id（外部）")
	private String fwTplId;

	@ApiModelProperty(value = "状态")
	private Integer status;
}