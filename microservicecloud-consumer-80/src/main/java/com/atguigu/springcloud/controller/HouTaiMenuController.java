package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.bean.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
@RequestMapping("/houtai/menu")
public class HouTaiMenuController {
	
	@Autowired
	RestTemplate restTemplate;

	/**
	 *  /houtai/menu/manage?pId=-1
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(@RequestParam(value = "pId", required = false,defaultValue = "-1") Integer pId) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "菜单管理");
		mav.addObject("title", "菜单管理");
		mav.addObject("pId", pId);
		System.out.println(pId);
		mav.setViewName("admin/page/menu/menu_manage");
		return mav;
	}
	
	/**
	 * /houtai/menu/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam(value = "pId", required = false) Integer pId) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/menu/add");
		mav.addObject("pId", pId);
		mav.setViewName("admin/page/menu/add_update");
		return mav;
	}
	
	/**
	 * /houtai/menu/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		MultiValueMap<String,Integer > map=new LinkedMultiValueMap<>();
		map.add("id",id);
		Menu menu=restTemplate.postForObject(UrlPath.GET_MENU,map,Menu.class);
		mav.addObject("menu", menu);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/menu/update?id=" + id);
		mav.addObject("pId", menu.getpId());
		mav.setViewName("admin/page/menu/add_update");
		return mav;
	}
	
	
	
	
	
	
}
