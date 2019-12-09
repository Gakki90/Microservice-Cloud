package com.atguigu.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.dao.RoleMenuDAO;
import com.atguigu.dao.UserDAO;
import com.atguigu.service.LoginService;
import com.atguigu.service.MenuService;
import com.atguigu.springcloud.bean.Menu;
import com.atguigu.springcloud.bean.RoleMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class loginController {
    @Autowired
    UserDAO userDao;

    @Autowired
    private LoginService loginService;
    @ResponseBody
    @PostMapping("/admin/login")
    public JSONObject admin(String name, String password) throws Exception {
        System.out.println(name+" "+password);

        JSONObject jsonObject=loginService.Login(name,password);
        if("true".equals(jsonObject.get("success"))){
            jsonObject.put("url","/admin/main");
        }else {
            jsonObject.put("url","/login");
        }
        System.out.println(jsonObject.get("treeList"));
        return jsonObject;

    }





    @RequestMapping("/logout")
    public String logout()throws Exception{
        return "redirect:/login";
    }
}
