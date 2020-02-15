package com.atguigu.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.service.UserService;
import com.atguigu.springcloud.bean.User;
import com.atguigu.springcloud.bean.WXSessionModel;
import com.atguigu.util.HttpClientUtil;
import com.atguigu.util.JsonUtils;
import com.atguigu.util.RedisOperator;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
    @Value("${wx.appid}")
    private String AppID;
    @Value("${wx.appsecret}")
    private String AppSecret;

    @Autowired
    UserService userService;

    @Autowired
    private RedisOperator redis;

    @PostMapping("/login")
    public JSONObject login(@RequestParam("code") String code,@RequestParam("name")String name,@RequestParam("url")String urk)
    {
        JSONObject jsonObject=new JSONObject();
        System.out.println(code);
        String url="https://api.weixin.qq.com/sns/jscode2session";
        System.out.println(AppID+"=="+AppSecret);
        Map<String ,String> params=new HashMap<>();
        params.put("appid",AppID);
        params.put("secret",AppSecret);
        params.put("js_code",code);
        params.put("grant_type","authorization_code");

        String result=HttpClientUtil.doGet(url,params);
        System.out.println(result);
        WXSessionModel model= JsonUtils.jsonToPojo(result,WXSessionModel.class);

        redis.set("user-redis-session:"+model.getOpenid(),model.getSession_key(),1000*60*30);
        User user=userService.getByOpenid(model.getOpenid());
        if(user==null){
            User newUser=new User();
            newUser.setOpenId(model.getOpenid());
            newUser.setName(name);
            newUser.setUrl(url);
            userService.save(newUser);
            jsonObject.put("userInfo",newUser);
        }else {
            jsonObject.put("userInfo",user);
        }
        jsonObject.put("msg","success");
        jsonObject.put("sessionId",model.getOpenid());

        return jsonObject;

    }

    @RequestMapping("/update")
    public JSONObject updateUser(User user){
        userService.update(user);
        JSONObject result=new JSONObject();
        result.put("success", true);
        result.put("msg", "添加成功");
        return result;
    }
}
