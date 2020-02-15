package com.atguigu.springbootquick;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.dao.UserDAO;
import com.atguigu.service.UserService;
import com.atguigu.springcloud.bean.Role;
import com.atguigu.springcloud.bean.User;
import com.atguigu.springcloud.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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
public class SpringBootQuickApplicationTests {
    @Autowired
    UserDAO userDAO;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserService userService;
    @Test
    public void contextLoads() throws SQLException {
        userDAO.updatePwd("admin",encoder.encode("248092"));
    }

    @Autowired
    RestTemplate restTemplate;
    @Test
    public void test() {
        Map<String,Object> parameters=new HashMap<>();
        Map<String, Object> list=restTemplate.getForObject("http://localhost:4001/admin/role/list?page={page}&limit={limit}",JSONObject.class,parameters);
        System.out.println(list);
    }

    @Test
    public void ss(){
        User user=userService.getByOpenid("oSDVa5E_aBpP5czdPCtzEq36qc7U");
        System.out.println(user.toString());
    }
}
