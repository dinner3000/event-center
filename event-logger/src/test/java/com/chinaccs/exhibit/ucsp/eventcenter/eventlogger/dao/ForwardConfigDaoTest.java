package com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.dao;

import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventlogger.entity.ForwardConfigEntity;
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
public class ForwardConfigDaoTest {

    @Autowired
    ForwardConfigDao forwardConfigDao;


    @Test
    public void test() {
//        Init a entity for test
        ForwardConfigEntity entity4Insert = buildForwardConfigEntity();

//        insert
        int affected = forwardConfigDao.insert(entity4Insert);
        assertTrue(affected > 0);

        Long id = entity4Insert.getId();
        assertNotNull(id);
        assertTrue(id > 0);

//        query
        ForwardConfigEntity entity4Query = forwardConfigDao.selectById(id);
        assertEquals(entity4Insert.getId(), entity4Query.getId());
        assertEquals(entity4Insert.getName(), entity4Query.getName());
        assertEquals(entity4Insert.getStatus(), entity4Query.getStatus());

//        update
        ForwardConfigEntity entity4Update = (ForwardConfigEntity) SerializationUtils.clone(entity4Query);
        entity4Update.setStatus(1);
        forwardConfigDao.updateById(entity4Update);

        entity4Query = forwardConfigDao.selectById(id);
        assertEquals(entity4Update.getStatus(), entity4Query.getStatus());

//        delete
        affected = forwardConfigDao.deleteById(id);
        assertTrue(affected > 0);
    }

    private ForwardConfigEntity buildForwardConfigEntity(){
        UUID uuid = UUID.randomUUID();

        ForwardConfigEntity entity = new ForwardConfigEntity();
        entity.setName(uuid.toString());
        entity.setDesc(String.format("%s - desc", uuid.toString()));
        entity.setApiUrl(String.format("http://10.211.18.49/send_sms/%s", uuid.toString()));
        entity.setType("SMS");

        List<String> targetList = new ArrayList<>();
        targetList.add("target1");
        targetList.add("target2");
        targetList.add("target3");

        entity.setTargetList(JSON.toJSONString(targetList));
        entity.setStatus(0);

        return entity;
    }
}
