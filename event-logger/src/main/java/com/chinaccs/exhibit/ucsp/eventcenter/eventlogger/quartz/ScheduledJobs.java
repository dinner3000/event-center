package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.quartz;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
//@DisallowConcurrentExecution
public class ScheduledJobs {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EventStatusStatService statService;

//    @Scheduled(cron = "*/3 * * * * *")
//    public void sampleJob(){
//        logger.info("scheduled job sample, exec in every 3 secs ... ");
//    }

    @Scheduled(cron = "0 */5 * * * *")
    public void resolvePerformanceStatTriMinutely(){
        logger.info("stat for event resolve performance, exec in every 3 minutes ... ");

        int interval = 5;

        Date statTime = new Date();
        statService.statResolvePerformance(statTime, interval);
    }

    @Scheduled(cron = "0 0 */2 * * *")
    public void resolvePerformanceStatBiHourLy(){
        logger.info("stat for event resolve performance, exec in every 2 hours ... ");

        int interval = 60 * 2;

        Date statTime = new Date();
        statService.statResolvePerformance(statTime, interval);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resolvePerformanceStatDaily(){
        logger.info("stat for event resolve performance, exec in every 1 day ... ");

        int interval = 60 * 24;

        Date statTime = new Date();
        statService.statResolvePerformance(statTime, interval);
    }
}
