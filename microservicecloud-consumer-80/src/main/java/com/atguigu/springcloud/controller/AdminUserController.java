package com.atguigu.springcloud.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.Dept;
import com.atguigu.springcloud.bean.User;
import com.atguigu.springcloud.service.DeptService;
import com.atguigu.springcloud.service.UserService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/add")
    public JSONObject add(@RequestBody @Valid User user,BindingResult bindingResult ) throws Exception {
        JSONObject result = new JSONObject();
        if(bindingResult.hasErrors()){
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        }else {


            JSONObject jsonObject= restTemplate.postForObject(UrlPath.ADD_USER, user,JSONObject.class);
            return jsonObject;
        }
    }
    @ResponseBody
    @RequestMapping("/set_new_pwd")
    public JSONObject set_new_pwd(User  user)throws Exception {
        JSONObject jsonObject= restTemplate.postForObject(UrlPath.UPDATE_USER_PWD, user,JSONObject.class);

        return jsonObject;
    }
    @ResponseBody
    @RequestMapping("/update")
    public JSONObject update(@RequestBody @Valid User user ,BindingResult bindingResult)throws Exception {
        JSONObject result = new JSONObject();

        if(bindingResult.hasErrors()){
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        }else{

            return restTemplate.postForObject(UrlPath.UPDATE_USER,user,JSONObject.class);
        }
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false,defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit", required = false,defaultValue = "10") Integer limit){
        Map<String,Integer> postParameters=new HashMap<>();
        postParameters.put("page",page);
        postParameters.put("limit",limit);
        Map<String, Object> list= restTemplate.getForObject(UrlPath.GET_USER_LIST+"?page={page}&limit={limit}", HashMap.class,postParameters);

        return list;
    }


}
