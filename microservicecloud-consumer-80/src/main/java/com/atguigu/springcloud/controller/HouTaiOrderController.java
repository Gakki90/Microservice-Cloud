package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URL;


@Controller
@RequestMapping("/houtai/order")
public class HouTaiOrderController {
	

	@Autowired
	RestTemplate restTemplate;
	/**
	 * /houtai/role/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "订单信息");
		mav.addObject("title", "订单信息");
		mav.setViewName("/admin/page/order/order_manage");
		return mav;
	}
	
	
	/**
	 * /houtai/role/add
	 */
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/order/add");
		mav.setViewName("/admin/page/order/add_update");
		return mav;
	}
	
	
	
	/**
	 * /houtai/role/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		MultiValueMap<String,Integer> map=new LinkedMultiValueMap<>();
		map.add("id",id);
		Order order =restTemplate.postForObject(UrlPath.GET_ORDER,map,Order.class);
		mav.addObject("order", order);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/order/update?id=" + id);
		mav.setViewName("/admin/page/order/add_update");
		return mav;
	}


}
