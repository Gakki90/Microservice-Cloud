package com.atguigu.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.dao.MenuDAO;
import com.atguigu.dao.RoleDAO;
import com.atguigu.dao.RoleMenuDAO;
import com.atguigu.service.RoleService;
import com.atguigu.springcloud.bean.Menu;
import com.atguigu.springcloud.bean.Role;
import com.atguigu.springcloud.bean.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/admin/role")
@CrossOrigin
public class AdminRoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private RoleMenuDAO roleMenuDao;
	@Autowired
	private MenuDAO menuDao;
	@ResponseBody
	@RequestMapping(value = "/getOne",method = RequestMethod.POST)
	public Role getOne(@RequestParam("id") Integer id){
		return  roleDao.findOne(id);
	}
	/**
	 * /admin/role/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(@RequestBody  Role role) throws Exception {
		JSONObject result = new JSONObject();
		role.setCreateDateTime(new Date());
			roleDao.save(role);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
	}
	
	/**
	 * /admin/role/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject update(@RequestBody Role role)throws Exception {
		JSONObject result = new JSONObject();
		

			role.setUpdateDateTime(new Date());
			roleService.update(role);
			result.put("success", true);
			result.put("msg", "修改成功");
			return result;

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
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Role> list = roleService.list(map, page-1, limit);
		long total = roleService.getTotal(map);
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * /admin/role/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids )
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		
		for (int i = 0; i < idsStr.length; i++) {
			try {
				roleDao.delete(Integer.parseInt(idsStr[i]));
			} catch (Exception e) {
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", "有用户正在使此角色");
				return result;
			}
		}
		
		result.put("success", true);
		return result;
	}
	@ResponseBody
	@RequestMapping("/getCheckedMenuData")
	public List<JSONObject>  getCheckedMenuData(@RequestParam(value = "roleId", required = false) Integer roleId, HttpServletResponse response)
			throws Exception {

		List<JSONObject>  list =  new ArrayList<JSONObject>();
		List<Menu> menuList = menuDao.findByPId(-1);

		for (Menu menu : menuList) {
			JSONObject node = new JSONObject();
			node.put("id", menu.getId());
			node.put("title", menu.getName());
			node.put("spread", true);

			node.put("children", getChildren(menu.getId(),roleId));
			list.add(node);
		}
		return list;
	}

	/**
	 * 辅助方法  辅助 上面的 admin_main（getCheckedMenuData）
	 * @param pId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<JSONObject> getChildren(Integer pId, Integer roleId)  throws Exception {
		List<Menu> menuList = menuDao.findByPId(pId);
		List<JSONObject>  list =  new ArrayList<JSONObject>();
		for (Menu menu : menuList) {
			JSONObject node = new JSONObject();
			node.put("id", menu.getId());
			node.put("title", menu.getName());

			//判断 当前菜单     当前角色有没有拥有。
			RoleMenu roleMenu=	roleMenuDao.findByRoleIdAndMenuId(roleId, menu.getId());
			if(roleMenu!=null){
				node.put("checked", true);
			}
			//判断 当前菜单     当前角色有没有拥有。

			list.add(node);
		}
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
		JSONObject result = new JSONObject();

		roleService.updateMenu(roleId, menuIds);

		result.put("success", true);
		result.put("msg", "修改成功");
		return result;

	}
	
	
}
