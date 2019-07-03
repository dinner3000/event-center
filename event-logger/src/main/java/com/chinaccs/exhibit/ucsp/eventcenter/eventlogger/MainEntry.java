/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger;

import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.service.EventListenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainEntry implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MainEntry.class, args);
	}

//	@Autowired
//    EventListenService eventListenService;

    @Override
    public void run(String... args) throws Exception {
//        eventListenService.onMessage();
    }
}
