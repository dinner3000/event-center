/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dao.ForwardConfigDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.ForwardConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggerApplication.class, args);
	}

}
