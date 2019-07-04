package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service;

import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dto.ForwardNoticeDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.EventEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
public interface ForwardTaskMQEnqueueService {

    void notify(ForwardNoticeDTO forwardNoticeDTO) throws Exception;

}