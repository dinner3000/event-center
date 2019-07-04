package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("event_forward_log")
public class EventForwardLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 名称
     */
	private Long typeId;


	private String fwUrl;

	private String fwTargets;

	private String text;

	private Integer status;

	private Integer retry;

	private Date fwTime;

	private String fwResult;


}