package com.atguigu.springcloud.bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="t_comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 10)
    private Integer parkId;
    @Column(length = 10)
    private Integer userId;
    @Column(length = 30)
    private String name;
    @Column
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getParkId() {
        return parkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }
}
