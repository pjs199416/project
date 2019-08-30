package com.project.po;

import java.io.Serializable;

/** 订单项表 */
public class OrdersItem implements Serializable {
	/**订单项目id*/
	private String id;
	/** 该订单项买的是什么商品   和商品 一 对一的关系*/
	private Item  item;
	/** 属于哪个订单 */
	private Orders orders; 
	/** 该订单项目的购买数量*/
	private int num; 
	/** 订单小计 */
	private double subtotal; 
	
	private Long item_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	
	
	
}
