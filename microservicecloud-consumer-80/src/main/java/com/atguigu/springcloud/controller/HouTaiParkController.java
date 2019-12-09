package com.atguigu.springcloud.controller;

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
@RequestMapping("/houtai/park")
public class HouTaiParkController {
	
	@Autowired
	RestTemplate restTemplate;
	
	/**
	 * /houtai/user/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "车场信息");
		mav.addObject("title", "车场信息");
		mav.setViewName("/admin/page/park/park_manage");
		return mav;
	}
	
	/**
	 * /houtai/user/add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();

		
		
		mav.addObject("flag", true);
		
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/park/add");
		mav.setViewName("/admin/page/park/add_update");
		return mav;
	}
	
	/**
	 * /houtai/user/edit?id=1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		MultiValueMap<String,Integer> map=new LinkedMultiValueMap<>();
		map.add("id",id);
		
		Park park = restTemplate.postForObject(UrlPath.GET_PARK,map,Park.class);
		mav.addObject("park", park);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/park/update?id=" + id);
		mav.setViewName("/admin/page/park/add_update");
		return mav;
	}

	
}
