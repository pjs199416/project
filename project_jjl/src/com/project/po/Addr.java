package com.project.po;

import java.io.Serializable;

/**地址表 */
public class Addr implements Serializable {
	private Long id;
	private User user;
	private String province;
	private String city;
	private String district;
	private String description;
	private String shName;
	private String shPhone;
	//是否是默认地址 1代表默认地址 0代表非默认地址
	private int status = 0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getShName() {
		return shName;
	}
	public void setShName(String shName) {
		this.shName = shName;
	}
	public String getShPhone() {
		return shPhone;
	}
	public void setShPhone(String shPhone) {
		this.shPhone = shPhone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
