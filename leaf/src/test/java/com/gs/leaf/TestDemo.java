package com.gs.leaf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestDemo {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {
        System.out.println(this.restTemplate.getForObject("/resource/getResource", Object.class));
        //assertThat(body).isEqualTo("Hello World");
    }


    @Test
    public void getUserById() {
        Map<String,Object> params = new HashMap<String, Object>(1);
        params.put("id","1");
        System.out.println(this.restTemplate.getForObject("/user/get", Object.class,params));
        //assertThat(body).isEqualTo("Hello World");
    }


    @Test
    public void getFromRedis() {
        Map<String,Object> params = new HashMap<String, Object>(1);
        params.put("id","1");
        System.out.println(this.restTemplate.getForObject("/user/getFromRedis", Object.class,params));
        //assertThat(body).isEqualTo("Hello World");
    }

}
