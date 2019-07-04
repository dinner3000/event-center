/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator;

import com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.dto.EventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.service.EventSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = "com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator")
public class MainEntry implements CommandLineRunner {

    @Autowired
    private EventSendService eventSendService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
		SpringApplication.run(MainEntry.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        try {
            logger.info("EventGenerator.run() started.");
            while (true) {
                eventSendService.send(buildEventDTO());
                Thread.sleep(5000);
            }
        } catch (Exception e){
            logger.info("EventGenerator.run() stopped.");
        }
    }

    private EventDTO buildEventDTO() {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(System.currentTimeMillis());
        eventDTO.setAppCode("app-1");
        if (eventDTO.getId() % 2 == 0) {
            eventDTO.setTypeId(0L);
        } else {
            eventDTO.setTypeId(1L);
        }
        eventDTO.setMessage(String.format("app-1, typeId: %d, message", eventDTO.getTypeId()));
        return eventDTO;
    }
}
