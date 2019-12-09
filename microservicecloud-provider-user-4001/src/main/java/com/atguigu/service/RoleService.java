package com.atguigu.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.atguigu.dao.MenuDAO;
import com.atguigu.dao.RoleDAO;
import com.atguigu.dao.RoleMenuDAO;
import com.atguigu.springcloud.bean.Role;
import com.atguigu.springcloud.bean.RoleMenu;
import com.atguigu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("roleService")
public class RoleService {

	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private MenuDAO menuDao;
	@Autowired
	private RoleMenuDAO roleMenuDao;
	
	
	public Integer update(Role role) {
		Role origin = roleDao.findOne(role.getId());
		BeanUtil.copyProperties(role,origin,true, CopyOptions.create().setIgnoreNullValue(true));

		roleDao.save(origin);
		return 1;
	}
	
	public List<Role> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "id");
		Page<Role> list = roleDao.findAll(pageable);
		return list.getContent();// 拿到list集合
	}

	public Long getTotal(Map<String, Object> map) {
		return roleDao.count();
	}


	/**
	 * 可以回滚数据
	 */
	@Transactional
	public Integer updateMenu(Integer roleId, String menuIds) {
		String[] idsStr = menuIds.split(",");
		RoleMenu roleMenu ;

		//删除之前的菜单  根据 角色id
		roleMenuDao.deleteByRoleId(roleId);
		//删除之前的菜单  根据 角色id

		//添加现在新的 （角色 对应的菜单 ）
		for (int i = 0; i < idsStr.length; i++) {
			if(StringUtil.isNotEmpty(idsStr[i])) {
				roleMenu = new RoleMenu();
				roleMenu.setRole(roleDao.findOne(roleId));
				roleMenu.setMenu(menuDao.findOne(Integer.parseInt(idsStr[i])));
				roleMenuDao.save(roleMenu);
			}
		}

		//修改角色 的更新 时间
		Role role = roleDao.findOne(roleId);
		role.setUpdateDateTime(new Date());
		roleDao.save(role);
		//修改角色 的更新 时间

		return 1;
	}


}
