package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventTypeEntity;
import lombok.Data;

import java.io.Serializable;


/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Data
public class ForwardNoticeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private EventEntity eventEntity;

	private EventTypeEntity typeEntity;

    public ForwardNoticeDTO(EventEntity eventEntity, EventTypeEntity typeEntity){
        this.eventEntity = eventEntity;
        this.typeEntity = typeEntity;
    }

}