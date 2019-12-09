package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicecloudPrividerOrder4002Application {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicecloudPrividerOrder4002Application.class, args);
    }

}
