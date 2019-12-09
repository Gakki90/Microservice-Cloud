package com.atguigu.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.Order;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
	
	@Resource
	RestTemplate restTemplate;


		
		

		
		
		/**
		 * /admin/order/list
		 * @param page    默认1
		 * @param limit   数据多少
		 */
		@ResponseBody
		@RequestMapping("/list")
		public Map<String, Object> list(@RequestParam(value = "page", required = false,defaultValue = "1") Integer page,
				@RequestParam(value = "limit", required = false,defaultValue = "500") Integer limit
				) throws Exception {
			HashMap map=new HashMap();
			map.put("page",page);
			map.put("limit",limit);
			return restTemplate.getForObject(UrlPath.GET_ORDER_LIST+"?page={page}&limit={limit}",Map.class,map);
		}
		
		/**
		 * /admin/user/delete
		 */
		@ResponseBody
		@RequestMapping("/delete")
		public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
				throws Exception {
			MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
			map.add("ids",ids);
			return restTemplate.postForObject(UrlPath.DELETE_ORDER,map,JSONObject.class);
		}
		
		
	
}
