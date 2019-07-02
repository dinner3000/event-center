package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("event")
public class EventEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
	private Long typeId;
    /**
     * 内容
     */
	private String message;
    /**
     * 源应用系统代码
     */
	private String appCode;
    /**
     * 状态
     */
	private Integer status;
}