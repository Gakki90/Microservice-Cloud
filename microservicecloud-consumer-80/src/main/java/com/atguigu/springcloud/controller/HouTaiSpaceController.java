package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.bean.Order;
import com.atguigu.springcloud.bean.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/houtai/space")
public class HouTaiSpaceController {
	

	@Autowired
	RestTemplate restTemplate;
	/**
	 * /houtai/role/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		MultiValueMap<String,Integer> map=new LinkedMultiValueMap<>();
		map.add("id",1);
		Order order =restTemplate.postForObject(UrlPath.GET_ORDER,map,Order.class);
		Park park=order.getPark();//因为还没绑定普通管理员与车场，所以展示用1来替代
		mav.addObject("park",park);
		mav.addObject("pageTitle", "车位状况");
		mav.addObject("title", "车位状况");
		mav.setViewName("admin/page/space/space_manage");
		return mav;
	}
	
	
	/**
	 * /houtai/role/add
	 */

	
	
	
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
		mav.setViewName("admin/page/order/add_update");
		return mav;
	}


}
