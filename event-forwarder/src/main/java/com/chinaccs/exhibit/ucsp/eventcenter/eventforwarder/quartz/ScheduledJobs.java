package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@DisallowConcurrentExecution
public class ScheduledJobs {

//    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
//    private EventForwardLogDao eventForwardLogDao;
//
//    @Autowired
//    private ForwardTaskExecuteService forwardTaskExecuteService;

//    @Scheduled(cron = "*/3 * * * * *")
//    public void sampleJob(){
//        logger.info("scheduled job sample, exec in every 3 secs ... ");
//    }

//    @Scheduled(cron = "0 * * * * *")
//    public void eventForwardTaskAutoRetryJob(){
//        logger.info("scheduled job for retries forward event, exec in every 1 min ... ");
//
//        QueryWrapper<EventForwardLogEntity> queryWrapper = new QueryWrapper<>();
//        // failed to call forward api last time
//        queryWrapper.eq("status", EventForwardStatus.FAILURE.getValue());
//        // failed times < 2
//        queryWrapper.le("retries", 2);
//        // order by fw_time
//        queryWrapper.orderByAsc("fw_time");
//
//        List<EventForwardLogEntity> taskList = eventForwardLogDao.selectList(queryWrapper);
//        if (taskList.size() < 1){
//            return;
//        }
//
//        for (EventForwardLogEntity task : taskList) {
//            forwardTaskExecuteService.forward(task);
//        }
//    }

}
