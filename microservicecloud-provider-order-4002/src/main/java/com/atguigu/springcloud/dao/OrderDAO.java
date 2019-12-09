package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderDAO extends JpaRepository<Order,Integer>  {
}
