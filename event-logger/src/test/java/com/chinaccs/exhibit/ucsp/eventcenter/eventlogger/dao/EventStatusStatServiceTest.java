package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dao;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventForwardLogDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusStatService;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventStatusStatServiceTest {

    @Autowired
    EventStatusStatService statusStatService;


    @Test
    public void test() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date startTime = sdf.parse("2019-07-16 10:00");
        Date endTime = sdf.parse("2019-07-16 11:00");

        statusStatService.statResolvePerformance(startTime, endTime, 1);
        statusStatService.statResolvePerformance(startTime, endTime, 60);
        statusStatService.statResolvePerformance(startTime, endTime, 1440);
    }
}
