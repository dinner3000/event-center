package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value = "事件信息")
public class EventDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "事件id")
	private Long id;

    @ApiModelProperty(value = "事件追踪id")
	private String traceId;

    @ApiModelProperty(value = "业务系统id")
    private String appCode;
    @ApiModelProperty(value = "业务系统名称")
    private String appName;

    @ApiModelProperty(value = "事件类型id")
    private Long typeId;
    @ApiModelProperty(value = "事件类型名称")
    private String typeName;

    @ApiModelProperty(value = "事件等级")
    private Integer level;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String message;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "发生时间")
//    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date occurTime;

    @ApiModelProperty(value = "解决时间")
//    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date resolveTime;

    @ApiModelProperty(value = "记录时间")
//    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date logTime;

    @ApiModelProperty(value = "推送记录")
    private EventForwardLogDTO forwardLog;

    @ApiModelProperty(value = "推送配置")
    private EventForwardConfigDTO forwardConfig;

}