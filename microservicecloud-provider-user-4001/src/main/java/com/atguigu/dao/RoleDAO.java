package com.atguigu.dao;

import com.atguigu.springcloud.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleDAO extends JpaRepository<Role,Integer> {
}
