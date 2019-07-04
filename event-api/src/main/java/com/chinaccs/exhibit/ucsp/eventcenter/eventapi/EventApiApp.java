/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.chinaccs.exhibit.ucsp.eventcenter")
@MapperScan("com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao")
public class EventApiApp {

	public static void main(String[] args) {
		SpringApplication.run(EventApiApp.class, args);
	}

}
