package com.atguigu.springcloud;

import com.atguigu.springcloud.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableConfigurationProperties(JwtUtil.class)
public class MicroservicecloudApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicecloudApiApplication.class, args);
    }

}
