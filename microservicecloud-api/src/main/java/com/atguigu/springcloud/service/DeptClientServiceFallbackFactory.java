package com.atguigu.springcloud.service;

import com.atguigu.springcloud.bean.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptService> {
    @Override
    public DeptService create(Throwable throwable) {
        return new DeptService() {
            @Override
            public Dept get(long id) {
                Dept dept=new Dept();
                dept.setDeptno((int) id);
                dept.setDname("服务降级");
                dept.setDb_source("NULL");
                return dept;
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }
        };
    }
}
