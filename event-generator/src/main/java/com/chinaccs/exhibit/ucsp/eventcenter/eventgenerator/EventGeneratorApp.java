/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator;

import cn.hutool.core.convert.Convert;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.IncomingEventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.service.EventSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventGeneratorApp implements CommandLineRunner {

    @Autowired
    private EventSendService eventSendService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
		SpringApplication.run(EventGeneratorApp.class, args);
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

    private IncomingEventDTO buildEventDTO() {
        IncomingEventDTO incomingEventDTO = new IncomingEventDTO();
        Long id = System.currentTimeMillis();
        incomingEventDTO.setId(Convert.toStr(id));
        incomingEventDTO.setAppCode("app-1");
        if (id % 2 == 0) {
            incomingEventDTO.setTypeId(0L);
        } else {
            incomingEventDTO.setTypeId(1L);
        }
        incomingEventDTO.setMessage(String.format("%s, typeId: %d, message",
                incomingEventDTO.getAppCode(), incomingEventDTO.getTypeId()));
        return incomingEventDTO;
    }
}
