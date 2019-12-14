package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.bean.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/houtai/role")
public class HouTaiRoleController {
	

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * /houtai/role/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "角色管理");
		mav.addObject("title", "角色管理");
		mav.setViewName("admin/page/role/role_manage");
		return mav;
	}
	
	
	/**
	 * /houtai/role/add
	 */
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/role/add");
		mav.setViewName("admin/page/role/add_update");
		return mav;
	}
	
	
	
	/**
	 * /houtai/role/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("id",id);
		Role role= restTemplate.postForObject(UrlPath.GET_ROLE, postParameters,Role.class);
		mav.addObject("role", role);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/role/update?id=" + id);
		mav.setViewName("admin/page/role/add_update");
		return mav;
	}

	@RequestMapping("/set_menu")
	public ModelAndView set_menu(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("id",id);
		Role role= restTemplate.postForObject(UrlPath.GET_ROLE, postParameters,Role.class);
		mav.addObject("role", role);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/role/update?id=" + id);
		mav.setViewName("admin/page/role/set_menu");
		return mav;
	}
}
