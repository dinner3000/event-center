package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "global-params")
public class GlobalParams {
    @Getter
    @Setter
    private String mqTopic;
}
