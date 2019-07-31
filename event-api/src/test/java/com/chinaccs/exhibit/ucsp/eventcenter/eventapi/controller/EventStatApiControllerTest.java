package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
//@AutoConfigureMockMvc
//@WebAppConfiguration
//@WebMvcTest(EventQueryApiController.class)
public class EventStatApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    //    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void byLevel() throws Exception {

        String jsonResponse =
                mockMvc.perform(MockMvcRequestBuilders.get("/stat/byLevel")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("success"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                        .andReturn().getResponse().getContentAsString();

        JSONObject result = JSON.parseObject(jsonResponse);
        List<Map<String, Object>> resultList = result.getJSONArray("data").toJavaList((Class<Map<String,Object>>)(Class)Map.class);

        assertNotNull(resultList);
        assertTrue(resultList.size() >= 4);
    }

    @Test
    public void byType() throws Exception {

        String jsonResponse =
                mockMvc.perform(MockMvcRequestBuilders.get("/stat/byType")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("success"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                        .andReturn().getResponse().getContentAsString();

        JSONObject result = JSON.parseObject(jsonResponse);
        List<Map<String, Object>> resultList = result.getJSONArray("data").toJavaList((Class<Map<String,Object>>)(Class)Map.class);

        assertNotNull(resultList);
        assertTrue(resultList.size() >= 2);
    }

    @Test
    public void byAppCode() throws Exception {

        String jsonResponse =
                mockMvc.perform(MockMvcRequestBuilders.get("/stat/byAppCode")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("success"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                        .andReturn().getResponse().getContentAsString();

        JSONObject result = JSON.parseObject(jsonResponse);
        List<Map<String, Object>> resultList = result.getJSONArray("data").toJavaList((Class<Map<String,Object>>)(Class)Map.class);

        assertNotNull(resultList);
        assertTrue(resultList.size() >= 1);
    }

}