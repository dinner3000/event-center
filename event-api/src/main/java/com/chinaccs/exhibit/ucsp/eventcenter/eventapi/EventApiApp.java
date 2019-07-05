/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
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
