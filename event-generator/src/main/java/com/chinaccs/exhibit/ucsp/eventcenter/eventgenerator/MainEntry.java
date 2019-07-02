/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator;

import com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.service.EventSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

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
                Thread.sleep(3000);
                eventSendService.Send();
            }
        } catch (Exception e){
            logger.info("EventGenerator.run() stopped.");
        }
    }
}
