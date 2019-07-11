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
public class IncomingEventDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	private String id;

	private String appCode;

	private Long typeId;

	private Integer level;

	private String title;

	private String message;

	private Date occurTime;

	private Integer status;
}