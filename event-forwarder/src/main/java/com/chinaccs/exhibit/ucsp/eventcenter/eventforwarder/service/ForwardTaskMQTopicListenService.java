package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
public interface ForwardTaskMQTopicListenService {

    void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack) throws Exception;

}