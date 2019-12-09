package com.atguigu.springcloud.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="t_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDateTime;//创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public void setParkSpaceNum(Integer parkSpaceNum) {
        this.parkSpaceNum = parkSpaceNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public Date getCreateDateTime() {
        return createDateTime;
    }
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public Date getUpdateDateTime() {
        return updateDateTime;
    }
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartTime() {
        return startTime;
    }
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndTime() {
        return endTime;
    }

    public Integer getParkId(){
        return park.getId();
    }
    public String getParkName(){
        return park.getName();
    }
    public Integer getTotalSpace(){
        return park.getTotalSpace();
    }
    public Integer getRemaningSpace(){
        return park.getRemaningSpace();
    }
    public String getRemaingSpaceNum(){
        return park.getRemaningSpaceNum();
    }
    public BigDecimal getPay() {
        return pay;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public Integer getParkSpaceNum() {
        return parkSpaceNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDateTime;//修改时间
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;//停车开始时间
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;//停车结束时间

    @ManyToOne
    @JoinColumn(name="parkId")
    private Park park; // 角色
    @Column(precision = 10, scale = 2)
    private BigDecimal pay;//支付
    @Column(length = 10)
    private Integer userId;//用户id
    @Column(length = 10)
    private String plateNum;//车牌号
    @Column(length = 10)
    private Integer parkSpaceNum;//停车位的编号
    @Column(length = 20)
    private String orderNum;//订单编号
}
