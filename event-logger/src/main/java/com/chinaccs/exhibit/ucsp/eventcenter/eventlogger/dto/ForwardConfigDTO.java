package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * 事件推送配置 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
public class ForwardConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String desc;

	private String apiUrl;

	private String type;

	private String targetList;

	private Integer status;


}