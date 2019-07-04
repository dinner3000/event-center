package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto;

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
public class EventDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	private Long id;

	private Long typeId;

	private String message;

	private String appCode;

	private Date occurTime;

	private String geoInfo;
}