package com.atguigu.springcloud.service;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "MICROSERVICECLOUD-User")
@RequestMapping("/")
public interface MenuService
{
}
