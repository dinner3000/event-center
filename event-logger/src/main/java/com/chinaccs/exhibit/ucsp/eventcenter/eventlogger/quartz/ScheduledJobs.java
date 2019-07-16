package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.quartz;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusStatService;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@DisallowConcurrentExecution
public class ScheduledJobs {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EventStatusStatService statService;

//    @Scheduled(cron = "*/3 * * * * *")
    public void sampleJob(){
        logger.info("scheduled job sample, exec in every 3 secs ... ");
    }

    @Scheduled(cron = "0 * * * * *")
    public void resolvePerformanceStatPerMinute(){
        logger.info("stat for event resolve performance, exec in every 1 min ... ");

        int interval = 1;

        Date statTime = new Date();
        statService.statResolvePerformance(statTime, interval);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void resolvePerformanceStatPerHour(){
        logger.info("stat for event resolve performance, exec in every 1 hour ... ");

        int interval = 60;

        Date statTime = new Date();
        statService.statResolvePerformance(statTime, interval);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void resolvePerformanceStatPerDay(){
        logger.info("stat for event resolve performance, exec in every 1 day ... ");

        int interval = 60 * 24;

        Date statTime = new Date();
        statService.statResolvePerformance(statTime, interval);
    }
}
