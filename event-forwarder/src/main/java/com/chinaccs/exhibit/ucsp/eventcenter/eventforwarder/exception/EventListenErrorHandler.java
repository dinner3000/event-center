package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.exception;

import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("eventListenErrorHandler")
public class EventListenErrorHandler implements ConsumerAwareListenerErrorHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        logger.error("Error when process message: {}, details: {}", message.getPayload().toString(), exception.getMessage());
        logger.info("Add more process logic if necessary", message.getPayload().toString(), exception.getMessage());
        return null;
    }
}
