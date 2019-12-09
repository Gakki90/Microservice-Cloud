package com.atguigu.springcloud.controller;

import com.alibaba.fastjson.JSONObject;

import com.atguigu.springcloud.bean.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("/admin/role")
public class AdminRoleController {

	@Autowired
	RestTemplate restTemplate;
	
	/**
	 * /admin/role/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(@Valid Role role, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		role.setCreateDateTime(new Date());
		
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{


			return restTemplate.postForObject(UrlPath.ADD_ROLE,role,JSONObject.class);
		}
	}
	
	/**
	 * /admin/role/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject update(@Valid Role role,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{

			return restTemplate.postForObject(UrlPath.UPDATE_ROLE,role,JSONObject.class);

		}
	}
	
	/**
	 * /admin/role/list
	 * @param page    默认1
	 * @param limit   数据多少
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false,defaultValue = "1") Integer page,
			@RequestParam(value = "limit", required = false,defaultValue = "11") Integer limit,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		HashMap<String,Object> parameters=new HashMap<>();
		parameters.put("page",page);
		parameters.put("limit",limit);

		return restTemplate.getForObject(UrlPath.GET_ROLE_LIST+"?page={page}&limit={limit}",HashMap.class,parameters);

	}
	
	
	/**
	 * /admin/role/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids)
			throws Exception {
		Map<String,String> map=new HashMap<>();
		map.put("ids",ids);
		return restTemplate.postForObject(UrlPath.DELETE_ROLE,map,JSONObject.class);
	}
	@ResponseBody
	@RequestMapping("/getCheckedMenuData")
	public List<JSONObject>  getCheckedMenuData(@RequestParam(value = "roleId", required = false) Integer roleId)
			throws Exception {
		Map<String ,Integer > map=new HashMap<>();
		map.put("roleId",roleId);
		List<JSONObject> list=restTemplate.postForObject(UrlPath.GET_CHECKED_MENU,map,List.class);
		return list;
	}



	/**
	 * @param roleId  =  12
	 * @param menuIds  1,2,5,6,8,5
	 */
	@ResponseBody
	@RequestMapping("/updateMenu")
	public JSONObject updateMenu(@RequestParam(value = "roleId", required = false) Integer roleId,
								 @RequestParam(value = "menuIds", required = false) String menuIds)throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("roleId",roleId);
		map.put("menuIds",menuIds);
		JSONObject result = restTemplate.postForObject(UrlPath.UPDATE_ROLE_MENU,map,JSONObject.class);
		return result;

	}
	
	
}
