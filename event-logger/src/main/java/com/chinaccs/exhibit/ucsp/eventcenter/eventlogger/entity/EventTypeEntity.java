package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 事件类型 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("event_type")
public class EventTypeEntity implements Serializable{
	private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
	private String name;
    /**
     * 说明
     */
	@TableField("`desc`")
	private String desc;
	/**
	 * 源应用系统代码
	 */
	private String appCode;
    /**
     * 级别
     */
	private Integer level;
    /**
     * 是否需要推送 Y/N
     */
	private Integer fwEnabled;

	private String fwUrl;

	private String fwType;

	private String fwTargets;

	private String fwTextTpl;

	/**
     * 状态
     */
	private Integer status;
}