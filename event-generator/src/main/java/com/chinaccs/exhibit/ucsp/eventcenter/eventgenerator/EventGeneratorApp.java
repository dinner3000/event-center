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

import java.util.Date;

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

        int level = Convert.toInt(id % 3);
        incomingEventDTO.setLevel(level);

        long typeId = id % 2;
        incomingEventDTO.setTypeId(typeId);

        incomingEventDTO.setTitle("test title");

        incomingEventDTO.setMessage(String.format("%s, configId: %d, message",
                incomingEventDTO.getAppCode(), incomingEventDTO.getTypeId()));

        incomingEventDTO.setOccurTime(new Date());

        return incomingEventDTO;
    }
}
