package com.atguigu.springcloud.service;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.atguigu.springcloud.bean.Dept;

import javax.validation.Valid;

@FeignClient(value = "MICROSERVICECLOUD-User")
@RequestMapping("/user")
@Service
public interface UserService
{
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    JSONObject add(@RequestBody User user) throws Exception ;

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    JSONObject update(@RequestBody User user )throws Exception;


    @RequestMapping(value = "/set_new_pwd",method = RequestMethod.POST)
   JSONObject set_new_pwd( User user)throws Exception;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "limit", required = false) Integer limit);

}
