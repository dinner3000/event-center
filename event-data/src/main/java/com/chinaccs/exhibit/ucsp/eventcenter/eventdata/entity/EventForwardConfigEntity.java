package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("event_forward_config")
public class EventForwardConfigEntity implements Serializable{
	private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

	private String name;

	@TableField("`desc`")
	private String desc;

	private String appCode;

	private Long typeId;

	private Integer level;

	private Date createTime;

	private Integer fwEnabled;

	private String fwType;

	private String fwTargets;

	private String fwTplText;

	private String fwTplId;

	private Integer status;
}