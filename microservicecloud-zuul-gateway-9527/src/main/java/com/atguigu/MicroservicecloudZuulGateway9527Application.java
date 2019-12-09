package com.atguigu;

import com.atguigu.springcloud.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class MicroservicecloudZuulGateway9527Application {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicecloudZuulGateway9527Application.class, args);
    }
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

}
