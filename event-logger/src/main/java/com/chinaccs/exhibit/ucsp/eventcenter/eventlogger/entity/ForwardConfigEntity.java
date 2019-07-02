package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 事件推送配置 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("forward_config")
public class ForwardConfigEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

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
     * 推送接口地址
     */
	private String apiUrl;
    /**
     * 推送类型 如：SMS，VOICE
     */
	private String type;
    /**
     * 推送目标列表 JSON数组
     */
	private String targetList;
    /**
     * 状态
     */
	private Integer status;
}