package com.atguigu.springcloud.microservicecloudapi;

import com.atguigu.springcloud.bean.Config;
import com.atguigu.springcloud.service.UserService;
import com.atguigu.springcloud.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MicroservicecloudApiApplicationTests {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;
    @Test
    public void contextLoads() {
        System.out.println(userService);
        Claims claims=jwtUtil.parseJWT(jwtUtil.createJWT("1","liu","wo"));
        System.out.println(claims.getSubject());

    }

}
