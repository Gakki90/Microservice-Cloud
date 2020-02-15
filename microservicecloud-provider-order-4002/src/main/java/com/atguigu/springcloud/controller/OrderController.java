package com.atguigu.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.Order;
import com.atguigu.springcloud.bean.User;
import com.atguigu.springcloud.dao.OrderDAO;
import com.atguigu.springcloud.service.OrderService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    OrderDAO orderDAO;


    @RequestMapping("add")
    public JSONObject add( @RequestBody Order order){
        orderService.add(order);

        System.out.println(order.getParkId());
        JSONObject result=new JSONObject();
        result.put("success", true);
        result.put("msg","成功");
        return result;
    }

    @RequestMapping("get")
    public JSONObject get(Integer id){
        List<Order> orders=orderDAO.getByUserId(id);
        System.out.println(orders);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("orders",orders);
        jsonObject.put("count",orders.size());
        return jsonObject;
    }
}
