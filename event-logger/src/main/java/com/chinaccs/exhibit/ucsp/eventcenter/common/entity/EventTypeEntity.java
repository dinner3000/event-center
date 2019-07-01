package com.chinaccs.exhibit.ucsp.eventcenter.common.entity;

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
public class EventTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
	private String name;
    /**
     * 说明
     */
	private String desc;
    /**
     * 级别
     */
	private Integer level;
    /**
     * 源应用系统代码
     */
	private String appCode;
    /**
     * 是否需要推送 Y/N
     */
	private String fwNeeded;
    /**
     * 推送配置 JSON数组
     */
	private String fwConfigIds;
    /**
     * 状态
     */
	private Integer status;
}