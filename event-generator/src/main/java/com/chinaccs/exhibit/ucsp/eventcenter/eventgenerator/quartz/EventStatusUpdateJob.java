package com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.quartz;

import cn.hutool.core.convert.Convert;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.EventStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.IncomingEventDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.service.EventSendService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class EventStatusUpdateJob implements Job {

    @Autowired
    private EventSendService eventSendService;

    @Autowired
    private Scheduler scheduler;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug("EventStatusUpdateJob.execute - start");

        Long eventId = jobExecutionContext.getJobDetail().getJobDataMap().getLong("eventId");
        String appCode = jobExecutionContext.getJobDetail().getJobDataMap().getString("appCode");
        Integer statusCode = jobExecutionContext.getJobDetail().getJobDataMap().getIntValue("statusCode");
        Integer nextStatusCode = statusCode + 1;

        logger.debug("EventStatusUpdateJob.execute - update event status to {}", nextStatusCode);
        this.updateEventStatus(eventId, appCode, nextStatusCode);

        if (nextStatusCode >= EventStatus.RESOLVED.getCode()){
            logger.debug("event status reaches final code, no need more scheduled task");
            return;
        }

        Random random = new Random(System.currentTimeMillis());
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new Date());
        int delay = random.nextInt(60);
        calendar.add(Calendar.SECOND, delay);
        Date jobStartTime = calendar.getTime();

        Trigger trigger = newTrigger()
                .startAt(jobStartTime)
//                .withSchedule(simpleSchedule())
                .build();

        JobDetail job = newJob(EventStatusUpdateJob.class)
//                .withIdentity("EventStatusUpdateJob", "TestJobBGroup")
                .usingJobData("appCode", appCode)
                .usingJobData("eventId", eventId)
                .usingJobData("statusCode", nextStatusCode)
                .build();

        try {
            logger.debug("EventStatusUpdateJob.execute - schedule a event status update job, start after {} secs", delay);
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        logger.debug("EventStatusUpdateJob.execute - end");
    }

    private void updateEventStatus(Long eventId, String appCode, Integer status){
        IncomingEventDTO incomingEventDTO = new IncomingEventDTO();

        incomingEventDTO.setId(Convert.toStr(eventId));

        incomingEventDTO.setAppCode(appCode);

        incomingEventDTO.setStatus(status);

        incomingEventDTO.setOvertime(Convert.toInt(System.currentTimeMillis() % 2));

        incomingEventDTO.setOwner("EventGeneratorApp");

        eventSendService.send(incomingEventDTO);
    }
}
