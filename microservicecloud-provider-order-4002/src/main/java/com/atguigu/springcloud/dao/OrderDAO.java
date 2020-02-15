package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderDAO extends JpaRepository<Order,Integer>  {

    @Query(value="select * from t_order where user_id = ?1 ",nativeQuery = true)
    List<Order> getByUserId(Integer id);
}
