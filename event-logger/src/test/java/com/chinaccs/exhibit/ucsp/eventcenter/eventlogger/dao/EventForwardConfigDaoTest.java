package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dao;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dao.EventForwardConfigDao;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardConfigEntity;
import org.apache.commons.lang3.SerializationUtils;
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
public class EventForwardConfigDaoTest {

    @Autowired
    EventForwardConfigDao eventForwardConfigDao;


    @Test
    public void test() {
//        Init a entity for test
        EventForwardConfigEntity entity4Insert = buildEntity();

//        insert
        int affected = eventForwardConfigDao.insert(entity4Insert);
        assertTrue(affected > 0);

        Long id = entity4Insert.getId();
        assertNotNull(id);
        assertTrue(id > 0);

//        query
        EventForwardConfigEntity entity4Query = eventForwardConfigDao.selectById(id);
        assertEquals(entity4Insert.getId(), entity4Query.getId());
        assertEquals(entity4Insert.getName(), entity4Query.getName());
        assertEquals(entity4Insert.getStatus(), entity4Query.getStatus());

//        update
        EventForwardConfigEntity entity4Update = (EventForwardConfigEntity) SerializationUtils.clone(entity4Query);
        entity4Update.setStatus(1);
        eventForwardConfigDao.updateById(entity4Update);

        entity4Query = eventForwardConfigDao.selectById(id);
        assertEquals(entity4Update.getStatus(), entity4Query.getStatus());

//        delete
        affected = eventForwardConfigDao.deleteById(id);
        assertTrue(affected > 0);
    }

    private EventForwardConfigEntity buildEntity(){
        UUID uuid = UUID.randomUUID();

        EventForwardConfigEntity entity = new EventForwardConfigEntity();
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
