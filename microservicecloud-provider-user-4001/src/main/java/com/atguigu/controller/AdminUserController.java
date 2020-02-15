package com.atguigu.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.dao.UserDAO;
import com.atguigu.service.UserService;
import com.atguigu.springcloud.bean.User;
import com.atguigu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/admin/user")
@RestController
@CrossOrigin
public class AdminUserController {
	
	@Resource
	private UserDAO userDao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	 
	   @Resource 
	   private UserService userService;
	@RequestMapping(value = "/getOne",method = RequestMethod.POST)
	public User getOne(@RequestParam("id") Integer id){
		return userService.getOne(id);
	}
	 /**
		 *   /admin/user/add
		 */
		@ResponseBody
		@RequestMapping(value = "/add",method = RequestMethod.POST)
		public JSONObject add( @RequestBody  User user  ) throws Exception {

			JSONObject result = new JSONObject();

				user.setCreateDateTime(new Date());
				user.setPwd(encoder.encode(user.getPwd()));
				userService.save(user);
				result.put("success", true);
				result.put("msg", "添加成功");
				return result;

		}
		
		
		

		/**
		 * /admin/user/update
		 */
		@ResponseBody
		@RequestMapping("/update")
		public JSONObject update(@RequestBody User user )throws Exception {
			JSONObject result = new JSONObject();

				user.setUpdateDateTime(new Date());
				userService.update(user);
				result.put("success", true);
				result.put("msg", "修改成功");
				return result;

		}
	@ResponseBody
	@RequestMapping("/updatePlateNum")
	public JSONObject updatePlateNum(User user )throws Exception {
		JSONObject result = new JSONObject();
		user.setUpdateDateTime(new Date());
		userService.update(user);
		result.put("success", true);
		result.put("msg", "修改成功");
		return result;

	}
		
		/**
		 * /admin/user/set_new_pwd
		 */
		@ResponseBody
		@RequestMapping("/set_new_pwd")
		public JSONObject set_new_pwd(User user)throws Exception {
			JSONObject result = new JSONObject();
			
			user.setUpdateDateTime(new Date());
			if(StringUtil.isNotEmpty(user.getPwd())){
				user.setPwd(encoder.encode(user.getPwd()));
			}
			System.out.println(user.getUpdateDateTime());

			userService.update(user);
			result.put("success", true);
			result.put("msg", "修改成功");
			return result;
			
		}
		
		
		/**
		 * /admin/user/list
		 * @param page    默认1
		 * @param limit   数据多少
		 */
		@ResponseBody
		@RequestMapping("/list")
		public Map<String, Object> list(@RequestParam(value = "page", required = false,defaultValue = "1") Integer page,
				@RequestParam(value = "limit", required = false,defaultValue = "500") Integer limit

				) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<User> userList = userService.list(map, page-1, limit);
			long total = userService.getTotal(map);
			map.put("data", userList);
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
		public JSONObject delete(@RequestParam(value = "ids", required = false) String ids)
				throws Exception {
			String[] idsStr = ids.split(",");
			JSONObject result = new JSONObject();

			for (int i = 0; i < idsStr.length; i++) {
				userService.delete(Integer.parseInt(idsStr[i]));
			}
			result.put("success", true);
			return result;
		}
		
		
	
}
