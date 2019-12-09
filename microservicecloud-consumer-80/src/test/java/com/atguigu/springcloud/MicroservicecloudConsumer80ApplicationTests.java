package com.atguigu.springcloud;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.Order;
import com.atguigu.springcloud.bean.Role;
import com.atguigu.springcloud.bean.User;
import com.atguigu.springcloud.controller.UrlPath;
import com.atguigu.springcloud.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroservicecloudConsumer80ApplicationTests {

    @Autowired
    RestTemplate restTemplate;
    @Test
    public void test() {
        MultiValueMap<String,Integer> map=new LinkedMultiValueMap<>();
        map.add("id",1);
        Order order =restTemplate.postForObject(UrlPath.GET_ORDER,map,Order.class);
        System.out.println(order);
    }
}


