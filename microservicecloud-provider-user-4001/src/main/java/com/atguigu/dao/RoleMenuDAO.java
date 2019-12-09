package com.atguigu.dao;

import com.atguigu.springcloud.bean.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface RoleMenuDAO extends JpaRepository<RoleMenu,Integer>  {
    @Query(value="select * from t_role_menu where role_id = ?1 and menu_id =?2",nativeQuery = true)
    public RoleMenu findByRoleIdAndMenuId(Integer roleId, Integer menuId);
    @Modifying
    @Transactional
    @Query(value="delete  from t_role_menu where role_id = ?1",nativeQuery = true)
    public Integer deleteByRoleId(Integer roleId);
    @Query(value = "select * from t_role_menu where role_id=?1",nativeQuery = true)
    List<RoleMenu> findMenuIdByRoleId(Integer roleId);
}
