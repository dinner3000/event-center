package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @Test
    public void page() {
    }

    @Test
    public void list() {
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/query/1").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("success"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.typeId").value(1));
    }
}