package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.Constant;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.dto.EventDTO;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
//@AutoConfigureMockMvc
//@WebAppConfiguration
//@WebMvcTest(EventQueryApiController.class)
public class EventQueryApiControllerTest {

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


    private MultiValueMap<String, String> buildPageParams(){

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(Constant.LIMIT, "5");
        params.add(Constant.PAGE, "1");
        params.add(Constant.ORDER_FIELD, "occur_time");
        params.add(Constant.ORDER, Constant.DESC);

        return params;
    }

    @Test
    public void page() throws Exception {

        MultiValueMap<String, String> params = buildPageParams();

        String jsonResponse =
                mockMvc.perform(MockMvcRequestBuilders.get("/query/page")
                        .accept(MediaType.APPLICATION_JSON_UTF8).params(params))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("success"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                        .andReturn().getResponse().getContentAsString();

        JSONObject result = JSON.parseObject(jsonResponse);
        JSONObject data = result.getJSONObject("data");
        List<EventDTO> eventDTOList = data.getJSONArray("list").toJavaList(EventDTO.class);


        assertNotNull(eventDTOList);
        assertTrue(eventDTOList.size() > 0);
        assertEquals(5, eventDTOList.size());
        assertTrue(eventDTOList.get(0).getOccurTime().compareTo(new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-19")) >= 0);
    }

    private MultiValueMap<String, String> buildListParams(){

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(Constant.APP_CODE, "app-1");
        params.add(Constant.TYPE_ID, "1");
        params.add(Constant.LEVEL, "3");
        params.add(Constant.START_TIME, "2019-07-07 00:00:00");
        params.add(Constant.END_TIME, "2019-07-09 00:00:00");

        return params;
    }

    @Test
    public void list() throws Exception {
        MultiValueMap<String, String> params = buildListParams();

        String jsonResponse =
                mockMvc.perform(MockMvcRequestBuilders.get("/query/list")
                        .accept(MediaType.APPLICATION_JSON_UTF8).params(params))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("success"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                        .andReturn().getResponse().getContentAsString();

        JSONObject result = JSON.parseObject(jsonResponse);
        List<EventDTO> eventDTOList = result.getJSONArray("data").toJavaList(EventDTO.class);


        assertNotNull(eventDTOList);
        assertTrue(eventDTOList.size() >= 1);
        EventDTO eventDTO = eventDTOList.get(0);
        assertEquals("app-1", eventDTO.getAppCode());
        assertEquals(1L, eventDTO.getTypeId().longValue());
        assertEquals(3, eventDTO.getLevel().intValue());
        assertTrue(eventDTO.getOccurTime().compareTo(new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-07")) > 0);
        assertTrue(eventDTO.getOccurTime().compareTo(new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-09")) < 0);
    }

    @Test
    public void getById() throws Exception {
        String jsonResponse = mockMvc.perform(MockMvcRequestBuilders.get("/query/1").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("success"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
            .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = JSON.parseObject(jsonResponse);
        EventDTO eventDTO = jsonObject.getObject("data", EventDTO.class);

        assertNotNull(eventDTO);
        assertEquals(1L, eventDTO.getTypeId().longValue());
    }
}