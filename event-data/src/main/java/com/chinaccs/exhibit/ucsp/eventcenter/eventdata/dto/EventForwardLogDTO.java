package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
@ApiModel(value = "事件推送记录")
public class EventForwardLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "事件id")
	private Long id;

	@ApiModelProperty(value = "推送配置id")
	private Long configId;

	@ApiModelProperty(value = "推送目标列表")
	private String targets;

	@ApiModelProperty(value = "推送内容模板")
	private String tplText;

	@ApiModelProperty(value = "模版变量列表")
	private String tplParams;

	@ApiModelProperty(value = "推送内容")
	private String text;

	@ApiModelProperty(value = "推送状态")
	private Integer status;

	@ApiModelProperty(value = "重试次数")
	private Integer retries;

	@ApiModelProperty(value = "推送时间")
//	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date fwTime;

	@ApiModelProperty(value = "推送返回结果")
	private String fwResult;

}