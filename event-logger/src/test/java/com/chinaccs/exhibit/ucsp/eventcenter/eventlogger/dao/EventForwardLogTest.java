package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dao;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventForwardLogDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import org.apache.commons.lang.SerializationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventForwardLogTest {

    @Autowired
    EventForwardLogDao eventForwardLogDao;


    @Test
    public void test() {
//        Init a entity for test
        EventForwardLogEntity entity4Insert = buildEntity();

//        insert
        int affected = eventForwardLogDao.insert(entity4Insert);
        assertTrue(affected > 0);

        Long id = entity4Insert.getId();
        assertNotNull(id);
        assertTrue(id > 0);

//        query
        EventForwardLogEntity entity4Query = eventForwardLogDao.selectById(id);
        assertEquals(entity4Insert.getId(), entity4Query.getId());
        assertEquals(entity4Insert.getStatus(), entity4Query.getStatus());

//        update
        EventForwardLogEntity entity4Update = (EventForwardLogEntity) SerializationUtils.clone(entity4Query);
        entity4Update.setStatus(1);
        eventForwardLogDao.updateById(entity4Update);

        entity4Query = eventForwardLogDao.selectById(id);
        assertEquals(entity4Update.getStatus(), entity4Query.getStatus());

//        delete
        affected = eventForwardLogDao.deleteById(id);
        assertTrue(affected > 0);
    }

    private EventForwardLogEntity buildEntity(){
        UUID uuid = UUID.randomUUID();

        EventForwardLogEntity entity = new EventForwardLogEntity();
        entity.setId(1L);
        entity.setText(uuid.toString());
        entity.setStatus(0);

        entity.setStatus(0);

        return entity;
    }
}
