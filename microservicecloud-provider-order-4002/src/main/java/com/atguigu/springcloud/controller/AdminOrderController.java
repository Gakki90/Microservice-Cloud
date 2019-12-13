package com.atguigu.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.Order;
import com.atguigu.springcloud.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	private OrderService orderService;

	@ResponseBody
	@RequestMapping("/getOne")
	public Order getOne(@RequestParam("id") Integer id){
		return orderService.findOne(id);
	}
		/**
		 * /admin/order/list
		 * @param page    默认1
		 * @param limit   数据多少
		 */
		@ResponseBody
		@RequestMapping("/list")
		public Map<String, Object> list(@RequestParam(value = "page", required = false,defaultValue = "1") Integer page,
				@RequestParam(value = "limit", required = false,defaultValue = "500") Integer limit,
				HttpServletResponse response,
				HttpServletRequest request) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<Order> orderList = orderService.list(map, page-1, limit);
			long total = orderService.getTotal(map);
			map.put("data", orderList);
			map.put("count", total);
			map.put("code", 0);
			map.put("msg", "");
			return map;
		}
		
		/**
		 * /admin/user/delete
		 */
		@ResponseBody
		@RequestMapping("/delete")
		public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
				throws Exception {
			String[] idsStr = ids.split(",");
			JSONObject result = new JSONObject();

			for (int i = 0; i < idsStr.length; i++) {
				orderService.delete(Integer.parseInt(idsStr[i]));
			}
			result.put("success", true);
			return result;
		}
		
		
	
}
