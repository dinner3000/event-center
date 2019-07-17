package com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.quartz;

import cn.hutool.core.convert.Convert;
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

public class EventGeneratorJob implements Job {

    @Autowired
    private EventSendService eventSendService;

    @Autowired
    private Scheduler scheduler;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug("EventGeneratorJob.execute - start");

        logger.debug("EventGeneratorJob.execute - send new event");
        String appCode = "app-1";
        Long eventId = System.currentTimeMillis();
        this.newEvent(eventId, appCode);

        Random random = new Random(System.currentTimeMillis());
        java.util.Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int delay = random.nextInt(60);
        calendar.add(Calendar.SECOND, delay);
        Date jobStartTime = calendar.getTime();

        Trigger trigger = newTrigger()
                .startAt(jobStartTime)
                .build();

        JobDetail job = newJob(EventStatusUpdateJob.class)
                .usingJobData("appCode", "app-1")
                .usingJobData("eventId", eventId)
                .usingJobData("statusCode", 0)
                .build();

        try {
            logger.debug("EventGeneratorJob.execute - schedule a event status update job, start after {} secs", delay);
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        logger.debug("EventGeneratorJob.execute - end");
    }

    private void newEvent(Long eventId, String appCode){
        IncomingEventDTO incomingEventDTO = new IncomingEventDTO();

        incomingEventDTO.setId(Convert.toStr(eventId));

        incomingEventDTO.setAppCode(appCode);

        int level = Convert.toInt(eventId % 3);
        incomingEventDTO.setLevel(level);

        long typeId = eventId % 2;
        incomingEventDTO.setTypeId(typeId);

        incomingEventDTO.setTitle("test title");

        incomingEventDTO.setMessage(String.format("%s, configId: %d, message",
                incomingEventDTO.getAppCode(), incomingEventDTO.getTypeId()));

        incomingEventDTO.setOccurTime(new Date());

        eventSendService.send(incomingEventDTO);
    }
}
