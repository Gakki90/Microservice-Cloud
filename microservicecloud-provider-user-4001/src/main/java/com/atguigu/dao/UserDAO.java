package com.atguigu.dao;

import com.atguigu.springcloud.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public interface UserDAO extends JpaRepository<User,Integer>  {
    User findByName(String name);

    @Query("UPDATE User u SET u.pwd=?2 WHERE u.name=?1")
    @Modifying
    @Transactional
    void updatePwd(String userName, String pwd);

}
