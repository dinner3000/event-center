package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
@ApiModel(value = "事件类型")
public class EventTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
	private Long id;

    @ApiModelProperty(value = "名称")
    private String name;
    
}