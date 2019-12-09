package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.bean.Role;
import com.atguigu.springcloud.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/houtai/user")
public class HouTaiUserController {
	
	@Autowired
	RestTemplate restTemplate;
	/**
	 * /houtai/user/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "用户管理");
		mav.addObject("title", "用户管理");
		mav.setViewName("/admin/page/user/user_manage");
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
		
		Map<String, Object> map = null;
		HashMap<String,Object> data= restTemplate.getForObject(UrlPath.GET_ROLE_LIST, HashMap.class);

		List<Role> roleList = (List<Role>) data.get("data");
		mav.addObject("roleList", roleList);
		
		
		mav.addObject("flag", true);
		
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/user/add");
		mav.setViewName("/admin/page/user/add_update");
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
		
		Map<String, Object> map = null;
		HashMap<String,Object> data= restTemplate.getForObject(UrlPath.GET_ROLE_LIST, HashMap.class);

		List<Role> roleList = (List<Role>) data.get("data");
		mav.addObject("roleList", roleList);



		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("id", id);

		User user= restTemplate.postForObject(UrlPath.GET_USER, postParameters,User.class);
		mav.addObject("user", user);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/user/update?id=" + id);
		mav.setViewName("/admin/page/user/add_update");
		return mav;
	}
	
	/**
	 * /houtai/user/set_new_pwd?id=1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/set_new_pwd")
	public ModelAndView set_new_pwd(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("id",id);

		User user= restTemplate.postForObject(UrlPath.GET_USER, postParameters,User.class);
		mav.addObject("user", user);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/user/set_new_pwd?id=" + id);
		mav.setViewName("/admin/page/user/set_new_pwd");
		return mav;
	}
	
}
