package com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.quartz;

import cn.hutool.core.convert.Convert;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.IncomingEventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusStatService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.service.EventSendService;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
//@EnableScheduling
//@DisallowConcurrentExecution
public class ScheduledJobs {

    private final Logger logger = LoggerFactory.getLogger(getClass());


//    @Scheduled(cron = "*/3 * * * * *")
//    public void sampleJob(){
//        logger.info("scheduled job sample, exec in every 3 secs ... ");
//    }


}
