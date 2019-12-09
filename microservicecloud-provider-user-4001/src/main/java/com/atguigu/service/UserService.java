package com.atguigu.service;

import java.util.List;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.atguigu.dao.UserDAO;
import com.atguigu.springcloud.bean.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserService {

    @Autowired
    private UserDAO userDao;

    @CachePut(value = "user" ,key = "'user_'+#user.id")
    public User update(User user) {
        User origin = userDao.findOne(user.getId());
        //将新的user 的非空属性传入旧的user
        BeanUtil.copyProperties(user,origin,true, CopyOptions.create().setIgnoreNullValue(true));

        //把没有值的数据  换成原数据库的数据。
//		user = repalce(user, origin);

        return userDao.save(origin);

    }

    @Cacheable(value="users",key="'page_'+#page")

    public List<User> list(Map<String, Object> map, Integer page, Integer pageSize) {
        // TODO Auto-generated method stub
        Pageable pageable=new PageRequest(page,pageSize, Sort.Direction.ASC,"id");
        Page<User> list=userDao.findAll(pageable);
        List<User> users=list.getContent();
        return users;
    }

    public Long getTotal(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return userDao.count();
    }
    @CacheEvict(value="user",key="'user_'+#id")
    public void delete(Integer id){
        userDao.delete(id);
    }

    @Cacheable(value="user",key="'user_'+#id")
    public User getOne(Integer id){
        System.out.println("数据库查询");
        return userDao.findOne(id);
    }

    @CachePut(value = "user", key = "'user_'+#user.id")
   public User save(User user){
       return userDao.save(user);
   }
}

