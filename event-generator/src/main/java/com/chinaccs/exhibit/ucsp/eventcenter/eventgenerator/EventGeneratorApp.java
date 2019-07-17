/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator;

import com.chinaccs.exhibit.ucsp.eventcenter.eventgenerator.quartz.EventGeneratorJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@SpringBootApplication
public class EventGeneratorApp implements CommandLineRunner {

    @Autowired
    private Scheduler scheduler;

    public static void main(String[] args) {
		SpringApplication.run(EventGeneratorApp.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        Trigger trigger = newTrigger()
//                .withIdentity("trigger1", "group1") //定义name/group
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInMinutes(3)
                        .repeatForever())
                .build();

        JobDetail job = newJob(EventGeneratorJob.class)
//                .withIdentity("EventGeneratorJob", "TestJobAGroup")
//                .usingJobData("appCode", "app-1")
//                .usingJobData("eventId", System.currentTimeMillis())
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

//        scheduler.shutdown(true);

    }

}
