/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.chinaccs.exhibit.ucsp.eventcenter")
@MapperScan("com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao")
public class EventForwarderApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EventForwarderApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
