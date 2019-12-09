package com.atguigu.springcloud.bean;

import java.io.Serializable;

public class Dept implements Serializable {
    private Integer deptno;
    private String dname;
    private String db_source;

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setDb_source(String db_source) {
        this.db_source = db_source;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public String getDname() {
        return dname;
    }

    public String getDb_source() {
        return db_source;
    }
}
