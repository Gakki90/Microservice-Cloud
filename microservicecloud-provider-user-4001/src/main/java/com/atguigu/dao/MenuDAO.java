package com.atguigu.dao;

import com.atguigu.springcloud.bean.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuDAO extends JpaRepository<Menu,Integer> , JpaSpecificationExecutor<Menu> {
    @Query(value = "SELECT * FROM t_menu where p_id=?1",nativeQuery = true)
    public List<Menu> findByPId(Integer pId);
}
