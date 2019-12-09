package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class MicroservicecloudPrividerUser4001Application {


    public static void main(String[] args) {
        SpringApplication.run(MicroservicecloudPrividerUser4001Application.class,args);
    }
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
