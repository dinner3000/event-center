package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.quartz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventForwardLogRecordService;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.constant.EventForwardStatus;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.service.ForwardTaskExecuteService;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@DisallowConcurrentExecution
public class ScheduledJobs {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EventForwardLogRecordService eventForwardLogRecordService;

    @Autowired
    private ForwardTaskExecuteService forwardTaskExecuteService;

//    @Scheduled(cron = "*/3 * * * * *")
    public void sampleJob(){
        logger.info("scheduled job sample, exec in every 3 secs ... ");
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void eventForwardTaskAutoRetryJob(){
        logger.info("scheduled job for retry forward event, exec in every 1 min ... ");

        QueryWrapper<EventForwardLogEntity> queryWrapper = new QueryWrapper<>();
        // failed to call forward api last time
        queryWrapper.eq("status", EventForwardStatus.FAILURE.getValue());
        // failed times < 2
        queryWrapper.le("retry", 2);
        // order by fw_time
        queryWrapper.orderByAsc("fw_time");

        List<EventForwardLogEntity> taskList = eventForwardLogRecordService.list(queryWrapper);
        if (taskList.size() < 1){
            return;
        }

        for (EventForwardLogEntity task : taskList) {
            forwardTaskExecuteService.forward(task);
        }
    }

}
