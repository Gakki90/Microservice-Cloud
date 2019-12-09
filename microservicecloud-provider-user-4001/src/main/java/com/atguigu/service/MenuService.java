package com.atguigu.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.atguigu.dao.MenuDAO;
import com.atguigu.springcloud.bean.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;


@Service("menuService")
public class MenuService {
	
	@Resource
	private MenuDAO menuDao;
	
	
	
	public Integer update(Menu menu) {
		Menu origin = menuDao.findOne(menu.getId());
		BeanUtil.copyProperties(menu,origin, CopyOptions.create().setIgnoreNullValue(true));
		menuDao.save(origin);
		return 1;
	}
	
	public List<Menu> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "orderNo");
		Page<Menu> pages = menuDao.findAll(new Specification<Menu>() {
			
			@Override
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				// 加入 等于 divId  父节点 
				if (map.get("pId") != null) {
					predicate.getExpressions().add(cb.equal(root.get("pId"), map.get("pId")));
				}
				return predicate;
			}
		}, pageable);
		return pages.getContent();
	}
	
	public Long getTotal(Map<String, Object> map){
		Long count=menuDao.count(new Specification<Menu>() {
			@Override
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				// 加入 等于 divId  父节点 
				if (map.get("pId") != null) {
					predicate.getExpressions().add(cb.equal(root.get("pId"), map.get("pId")));
				}
				return predicate;
			}
		});
		return count;
	}
	
	


	

}
