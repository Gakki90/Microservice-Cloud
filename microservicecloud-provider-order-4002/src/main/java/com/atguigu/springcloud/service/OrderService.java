package com.atguigu.springcloud.service;

import com.atguigu.springcloud.bean.Order;
import com.atguigu.springcloud.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDao;

    public List<Order> list(Map<String, Object> map, Integer page, Integer pageSize) {
        // TODO Auto-generated method stub
        Pageable pageable=new PageRequest(page,pageSize, Sort.Direction.ASC,"id");
        Page<Order> list=orderDao.findAll(pageable);
        List<Order> orders=list.getContent();
        return orders;

    }
    public Long getTotal(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return orderDao.count();
    }
    public void delete(Integer id){
        orderDao.delete(id);

    }
    public Order findOne(Integer id){
        return orderDao.findOne(id);
    }
    public void add(Order order){
        orderDao.save(order);
    }


}
