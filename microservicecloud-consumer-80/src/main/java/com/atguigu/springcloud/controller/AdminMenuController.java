package com.atguigu.springcloud.controller;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.rmi.MarshalException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {
	
	@Autowired
	RestTemplate restTemplate;

	
	/**
	 * /admin/menu/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(@RequestBody @Valid Menu menu , BindingResult bindingResult) throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{

			return restTemplate.postForObject(UrlPath.ADD_MENU,menu,JSONObject.class);
		}
	}
	
	/**
	 * /admin/menu/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject update( Menu menu )throws Exception {

		return restTemplate.postForObject(UrlPath.UPDATE_MENU, menu,JSONObject.class);
	}
	
	/**
	 * /admin/menu/list
	 * @param page    默认1
	 * @param limit   数据多少
	 * @param pId   父节点  id
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false,defaultValue = "1") Integer page,
			@RequestParam(value = "limit", required = false,defaultValue = "100") Integer limit,
			@RequestParam(value = "pId", required = false,defaultValue = "-1") Integer pId
			) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page",page);
		map.put("limit",limit);
		map.put("pId",pId);
		System.out.println(page+" "+limit+" "+pId);
		return restTemplate.getForObject(UrlPath.GET_MENU_LIST+"?page={page}&limit={limit}&pId={pId}",Map.class,map);
	}
	
	
	/**
	 * /admin/menu/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
		map.add("ids",ids);
		return restTemplate.postForObject(UrlPath.DELETE_MENU,map,JSONObject.class);
	}
	
	
	
	
}
