package com.atguigu.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.User;
import com.atguigu.springcloud.cfgbeans.TokenInterceptor;
import com.atguigu.springcloud.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping( value = {"/","/login"})
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/admin/login",method = RequestMethod.POST)
    public ModelAndView admin(@RequestParam(value = "name")String name, @RequestParam(value = "password")String password) throws JSONException{
        System.out.println(name+" "+password);
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("name", "admin");
        postParameters.add("password", "248092");
        JSONObject jsonObject= restTemplate.postForObject(UrlPath.GET_ROLE_MENU, postParameters, JSONObject.class);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("admin/main");
        modelAndView.addObject("user", jsonObject.get("user"));
        modelAndView.addObject("msg",jsonObject.get("msg"));
        List<JSONObject> list= (List<JSONObject>) jsonObject.get("treeList");
        modelAndView.addObject("treeList", list);
        String token= "Bearer "+(String) jsonObject.get("token");
        System.out.println(token);
        restTemplate.setInterceptors(Collections.singletonList(new TokenInterceptor(token)));
        return modelAndView;
    }

    @RequestMapping("/logout")
    public String logout()throws Exception{
        restTemplate.setInterceptors(Collections.singletonList(new TokenInterceptor("error")));
        return "redirect:login";
    }




}
