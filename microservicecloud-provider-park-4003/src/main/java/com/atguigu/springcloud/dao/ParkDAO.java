package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.bean.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ParkDAO extends JpaRepository<Park,Integer> {
}
