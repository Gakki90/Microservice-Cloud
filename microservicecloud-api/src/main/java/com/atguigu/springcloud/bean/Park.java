package com.atguigu.springcloud.bean;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="t_park")
public class Park implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length=30)
    private  String name;//停车场名称
    @Column(length=60)
    private  String address;//地址
    @Column(length = 20)
    private String longitude;//经度
    @Column(length = 20)
    private String latitude;//纬度
    @Column(length = 10)
    private Integer totalSpace;//总共车位
    @Column(length = 10)
    private Integer remaningSpace;//剩余车位
    @Column(length = 100)
    private String remaningSpaceNum;//剩余车位的具体编号
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDateTime;//创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDateTime;//修改时间
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getChargeStanards() {
        return chargeStanards;
    }

    public void setChargeStanards(String chargeStanards) {
        this.chargeStanards = chargeStanards;
    }

    @Column()
    private String chargeStanards;//收费标准
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(length = 20)
    private String phone;

    public String getPhone() {
        return phone;
    }

    public Integer getRemaningSpace() {
        return remaningSpace;
    }

    public void setRemaningSpace(Integer remaningSpace) {
        this.remaningSpace = remaningSpace;
    }

    public void setRemaningSpaceNum(String remaningSpaceNum) {
        this.remaningSpaceNum = remaningSpaceNum;
    }

    public String getRemaningSpaceNum() {
        return remaningSpaceNum;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setTotalSpace(Integer totalSpace) {
        this.totalSpace = totalSpace;
    }


    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public void setParkType(String parkType) {
        this.parkType = parkType;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public Integer getTotalSpace() {
        return totalSpace;
    }


    public String getPic_url() {
        return pic_url;
    }

    public String getParkType() {
        return parkType;
    }


    @Column(length = 30)
    private String pic_url;
    @Column(length = 20)
    private String parkType;
}
