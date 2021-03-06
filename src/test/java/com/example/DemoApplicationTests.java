package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManagerFactory;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    ObjectMapper MAPPER;

//    final static ObjectMapper MAPPER = new ObjectMapper();
//
//    static {
//        MAPPER.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
//        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    }

    @After
    public void close() {
        entityManagerFactory.close();
    }

    @Test
    public void noParamGetShouldReturnDefaultMessage() throws Exception {

        Map<String, String> map = Maps.newHashMap();
        map.put("a", "Hello World!");
        String resp = this.mockMvc.perform(get("/abcd")).andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assert.assertEquals(MAPPER.writeValueAsString(map), resp);
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        String resp = this.mockMvc.perform(get("/1"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("hello worlds:" + resp);
//        Assert.assertEquals(MAPPER.writeValueAsString(map), resp);
    }

}
