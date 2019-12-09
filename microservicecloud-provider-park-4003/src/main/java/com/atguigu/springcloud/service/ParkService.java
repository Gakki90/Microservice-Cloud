package com.atguigu.springcloud.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.atguigu.springcloud.bean.Park;
import com.atguigu.springcloud.dao.ParkDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParkService {
    @Autowired
    ParkDAO parkDao;
    @Cacheable(value = "parks",key = "'page_'+#page")
    public List<Park> list(Map<String, Object> map, Integer page, Integer pageSize) {
        // TODO Auto-generated method stub
        Pageable pageable=new PageRequest(page,pageSize, Sort.Direction.ASC,"id");
        Page<Park> list=parkDao.findAll(pageable);
        List<Park> parks=list.getContent();
        return parks;

    }

    @CachePut(value = "park" ,key = "'park_'+#id")
    public void delete(Integer id){
        parkDao.delete(id);
    }
    @CachePut(value = "park",key = "'park_'+#id")
    public Park update(Park park) {
        Park origin = parkDao.findOne(park.getId());
        //将新的user 的非空属性传入旧的user
        BeanUtil.copyProperties(park,origin,true, CopyOptions.create().setIgnoreNullValue(true));

        return parkDao.save(origin);
    }
    @CachePut(value = "park",key = "'park_'+#park.id")
    public Park add(Park park){
        return parkDao.save(park);
    }
    public long getCount(){
        return parkDao.count();
    }


    @Cacheable(value = "park" ,key = "'park_'+#id")
    public Park getOne(Integer id){
        return parkDao.findOne(id);
    }

}
