package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class MicroservicecloudProviderPark4003Application {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicecloudProviderPark4003Application.class, args);
    }

}
