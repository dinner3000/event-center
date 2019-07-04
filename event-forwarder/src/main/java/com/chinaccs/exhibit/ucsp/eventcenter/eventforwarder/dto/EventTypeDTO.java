package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
public class EventTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String desc;

	private Integer level;

	private String appCode;

	private String fwNeeded;

	private String fwConfigIds;

	private Integer status;


}