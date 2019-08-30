package com.project.po;


import java.io.Serializable;

//** 订单表

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Orders implements Serializable {
	/** 订单id 就是订单编号*/
	private String id;
	/** 订单时间 */
	private Timestamp createdate;
	/** 订单总金额 */
	private double total;
	/** 订单项  和订单项目  一 对多关系 */
	private List<OrdersItem> list = new ArrayList<OrdersItem>();
	/** 订单状态  1代表付款 0代表未付款 */
	private int status;
	/** 订单所有人  和 用户 多对一关系*/
	private User user;
	/** 收货人地址 */
	private String shAddr;
	/** 收货人*/
	private String shName;
	/**收货人手机号*/
	private String shPhone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<OrdersItem> getList() {
		return list;
	}
	public void setList(List<OrdersItem> list) {
		this.list = list;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getShAddr() {
		return shAddr;
	}
	public void setShAddr(String shAddr) {
		this.shAddr = shAddr;
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
	
	
}
