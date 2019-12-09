package com.atguigu.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.Park;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/park")
public class AdminParkController {
	

	   @Autowired
	RestTemplate restTemplate;
	  
	 /**
		 *   /admin/user/add
		 */
		@ResponseBody
		@RequestMapping("/add")
		public JSONObject add(@RequestBody @Valid Park park  , BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
			JSONObject result = new JSONObject();
			if(bindingResult.hasErrors()){
				result.put("success", false);
				result.put("msg", bindingResult.getFieldError().getDefaultMessage());
				return result;
			}else{
				return restTemplate.postForObject(UrlPath.ADD_PARK,park,JSONObject.class);
			}
		}
		
		
		

		/**
		 * /admin/user/update
		 */
		@ResponseBody
		@RequestMapping("/update")
		public JSONObject update(@RequestBody @Valid Park park ,BindingResult bindingResult, HttpServletRequest request)throws Exception {
			JSONObject result = new JSONObject();
			
			if(bindingResult.hasErrors()){
				result.put("success", false);
				result.put("msg", bindingResult.getFieldError().getDefaultMessage());
				return result;
			}else{

				return restTemplate.postForObject(UrlPath.UPDATE_PARK,park,JSONObject.class);
			}
		}
		
		
		/**
		 * /admin/user/set_new_pwd
		 */

		
		
		/**
		 * /admin/user/list
		 * @param page    默认1
		 * @param limit   数据多少
		 */
		@ResponseBody
		@RequestMapping("/list")
		public Map<String, Object> list(@RequestParam(value = "page", required = false,defaultValue = "1") Integer page,
				@RequestParam(value = "limit", required = false,defaultValue = "500") Integer limit,
				HttpServletResponse response,
				HttpServletRequest request) throws Exception {
			HashMap map=new HashMap();
			map.put("page",page);
			map.put("limit",limit);
		return restTemplate.getForObject(UrlPath.GET_PARK_LIST+"?page={page}&limit={limit}",Map.class,map);


		}
		
		/**
		 * /admin/user/delete
		 */
		@ResponseBody
		@RequestMapping("/delete")
		public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
				throws Exception {
			MultiValueMap<String,String > map=new LinkedMultiValueMap<>();
			map.add("ids",ids);
			return restTemplate.postForObject(UrlPath.DELETE_PARK,map, JSONObject.class);
		}
		
		
	
}
