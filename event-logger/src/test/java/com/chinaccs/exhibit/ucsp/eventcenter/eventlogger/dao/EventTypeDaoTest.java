package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dao;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventTypeDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventTypeEntity;
import org.apache.commons.lang.SerializationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventTypeDaoTest {

    @Autowired
    EventTypeDao eventTypeDao;


    @Test
    public void test() {
//        Init a entity for test
        EventTypeEntity entity4Insert = buildEntity();

//        insert
        int affected = eventTypeDao.insert(entity4Insert);
        assertTrue(affected > 0);

        Long id = entity4Insert.getId();
        assertNotNull(id);
        assertTrue(id > 0);

//        query
        EventTypeEntity entity4Query = eventTypeDao.selectById(id);
        assertEquals(entity4Insert.getId(), entity4Query.getId());
        assertEquals(entity4Insert.getName(), entity4Query.getName());
        assertEquals(entity4Insert.getStatus(), entity4Query.getStatus());

//        update
        EventTypeEntity entity4Update = (EventTypeEntity) SerializationUtils.clone(entity4Query);
        entity4Update.setStatus(1);
        eventTypeDao.updateById(entity4Update);

        entity4Query = eventTypeDao.selectById(id);
        assertEquals(entity4Update.getStatus(), entity4Query.getStatus());

//        delete
        affected = eventTypeDao.deleteById(id);
        assertTrue(affected > 0);
    }

    private EventTypeEntity buildEntity(){
        UUID uuid = UUID.randomUUID();

        EventTypeEntity entity = new EventTypeEntity();
        entity.setName(uuid.toString());
        entity.setDesc(String.format("%s - desc", uuid.toString()));
        entity.setAppCode(String.format("%s - app code", uuid.toString()));
        entity.setLevel(0);
        entity.setStatus(0);
        entity.setFwEnabled(1);

        List<Long> targetList = new ArrayList<>();
        targetList.add(1L);
        targetList.add(2L);
        targetList.add(3L);

        return entity;
    }
}
