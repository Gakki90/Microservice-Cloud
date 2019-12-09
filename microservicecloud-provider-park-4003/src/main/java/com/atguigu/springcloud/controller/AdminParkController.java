package com.atguigu.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.bean.Park;
import com.atguigu.springcloud.dao.ParkDAO;
import com.atguigu.springcloud.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	ParkService parkService;

	@ResponseBody
	@RequestMapping("/getOne")
	public Park getOne(@RequestParam("id") Integer id){
		return parkService.getOne(id);
	}
	 /**
		 *   /admin/user/add
		 */
		@ResponseBody
		@RequestMapping("/add")
		public JSONObject add(@RequestBody Park park) throws Exception {
			JSONObject result = new JSONObject();

				park.setCreateDateTime(new Date());
				parkService.add(park);
				result.put("success", true);
				result.put("msg", "添加成功");
				return result;

		}
		
		
		

		/**
		 * /admin/user/update
		 */
		@ResponseBody
		@RequestMapping("/update")
		public JSONObject update(@RequestParam Park park )throws Exception {
			JSONObject result = new JSONObject();
			

				park.setUpdateDateTime(new Date());
				parkService.update(park);
				result.put("success", true);
				result.put("msg", "修改成功");
				return result;

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
		public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
				@RequestParam(value = "limit", required = false) Integer limit

				) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<Park> parksList = parkService.list(map, page-1, limit);
			long total = parkService.getCount();
			map.put("data", parksList);
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
				parkService.delete(Integer.parseInt(idsStr[i]));
			}
			result.put("success", true);
			return result;
		}
		
		
	
}
