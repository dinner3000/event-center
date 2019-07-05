/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.chinaccs.exhibit.ucsp.eventcenter")
@MapperScan("com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao")
public class EventLoggerApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EventLoggerApp.class, args);
	}

//	@Autowired
//    EventMQTopicListenService eventListenService;

    @Override
    public void run(String... args) throws Exception {
//        eventListenService.onMessage();
    }
}
