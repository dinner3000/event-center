package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dao;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventEntity;
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
public class EventDaoTest {

    @Autowired
    EventDao eventDao;


    @Test
    public void test() {
//        Init a entity for test
        EventEntity entity4Insert = buildEntity();

//        insert
        int affected = eventDao.insert(entity4Insert);
        assertTrue(affected > 0);

        Long id = entity4Insert.getId();
        assertNotNull(id);
        assertTrue(id > 0);

//        query
        EventEntity entity4Query = eventDao.selectById(id);
        assertEquals(entity4Insert.getId(), entity4Query.getId());
        assertEquals(entity4Insert.getMessage(), entity4Query.getMessage());
        assertEquals(entity4Insert.getStatus(), entity4Query.getStatus());

//        update
        EventEntity entity4Update = (EventEntity) SerializationUtils.clone(entity4Query);
        entity4Update.setStatus(1);
        eventDao.updateById(entity4Update);

        entity4Query = eventDao.selectById(id);
        assertEquals(entity4Update.getStatus(), entity4Query.getStatus());

//        delete
        affected = eventDao.deleteById(id);
        assertTrue(affected > 0);
    }

    private EventEntity buildEntity(){
        UUID uuid = UUID.randomUUID();

        EventEntity entity = new EventEntity();
        entity.setAppCode(String.format("%s - app code", uuid.toString()));
        entity.setTypeId(1L);
        entity.setMessage(String.format("%s - message", uuid.toString()));
        entity.setStatus(0);

        return entity;
    }
}
