package com.atguigu.springcloud.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * ## 后吧 用户  管理 网站的用户    
 * 
 */
@Entity
@Table(name = "t_a_user")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message="用户名不能为空！")
	@Column(length=30)
	private  String name;//用户名
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role; // 角色  
	@Column(length=200)
	private  String pwd;
	@NotNull(message="真实姓名不能为空！")
	@Column(length=200)
	private  String trueName;
	@Column(precision = 10, scale = 2)
	private BigDecimal balance;//用户余额

	@Column(length = 10)
	private Integer credit;//用户信用

	@Column(length = 10)
	private String plateNum;//车牌号
	@Column(length=200)
	private  String remark;//备注
	@Column(length=10)
	private Integer orderNo;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDateTime;//创建时间
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateDateTime;//修改时间
	public BigDecimal getBalance() {
		return balance;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getPlateNum() {
		return plateNum;
	}


	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
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
	
	
	
	
	
}
