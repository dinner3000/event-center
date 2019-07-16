package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.impl;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service.impl.BaseServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventStatusStatDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventStatusStatEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.service.EventStatusStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * 事件 存储事件信息，每个事件作为一条记录
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class EventStatusStatServiceImpl extends BaseServiceImpl<EventStatusStatDao, EventStatusStatEntity> implements EventStatusStatService {

    @Autowired
    private EventStatusStatDao statusStatDao;

    @Override
    public void statResolvePerformance(Date statTime, Integer interval) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(statTime);
        calendar.add(Calendar.MINUTE, interval * -1);

        Date startTime = calendar.getTime();
        Date endTime = statTime;

        int statusList[] = new int[] {1, 2, 3};
        int overtimeList[] = new int[] {0, 1};

        for(int status : statusList) {
            for (int overtime : overtimeList) {
                statusStatDao.statResolvePerformance(startTime, endTime, status, overtime, endTime, interval);
            }
        }
    }

    @Override
    public void statResolvePerformance(Date startTime, Date endTime, Integer interval) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endTime);

        while (calendar.getTime().after(startTime)) {
            this.statResolvePerformance(calendar.getTime(), interval);
            calendar.add(Calendar.MINUTE, interval * -1);
        }

    }
}