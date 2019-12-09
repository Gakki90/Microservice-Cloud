package com.atguigu.controller;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.dao.MenuDAO;
import com.atguigu.service.MenuService;
import com.atguigu.springcloud.bean.Menu;
import com.atguigu.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin/menu")
@CrossOrigin
public class AdminMenuController {
	
	@Resource
	private MenuService menuService;
	@Resource
	private MenuDAO menuDao;

	@ResponseBody
	@RequestMapping("/getOne")
	public Menu getOne(@RequestParam("id") Integer id){
		return menuDao.findOne(id);
	}
	/**
	 * /admin/menu/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(@RequestBody Menu menu) throws Exception {
		JSONObject result = new JSONObject();

			menuDao.save(menu);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;

	}
	
	/**
	 * /admin/menu/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject update( @RequestBody Menu menu )throws Exception {
		JSONObject result = new JSONObject();
		menuService.update(menu);
		result.put("success", true);
		result.put("msg", "修改成功");
		return result;
	}
	
	/**
	 * /admin/menu/list
	 * @param page    默认1
	 * @param limit   数据多少
	 * @param pId   父节点  id
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pId", required = false) Integer pId
			) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("pId", pId);
		System.out.println(page+" "+limit+" "+pId);
		List<Menu> list = menuService.list(map, page-1, limit);
		long total = menuService.getTotal(map);
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * /admin/menu/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < idsStr.length; i++) {
			try {
				map.put("pId", Integer.parseInt(idsStr[i]));
				//看看下面有没有菜单   一同删除
				List<Menu> menuList = menuService.list(map, 0, 100);
				for(Menu menu:menuList) {
					menuDao.delete(menu.getId());
				}
				//看看下面有没有菜单   一同删除
				
				menuDao.delete(Integer.parseInt(idsStr[i]));
			} catch (Exception e) {
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", "有角色正在使用些菜单");
				return result;
			}
			
		}
		
		result.put("success", true);
		return result;
	}
	
	
	
	
}
