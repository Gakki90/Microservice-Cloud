package com.atguigu.springcloud.bean;


import javax.persistence.*;
import java.io.Serializable;


/**
 *   #网站配置    实体
 */
@Entity
@Table(name = "t_config")
public class Config implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length=50)
	private String webName; //网站名称
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	
	
	
	
	
}
