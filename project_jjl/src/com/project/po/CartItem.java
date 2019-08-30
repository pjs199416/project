package com.project.po;

import java.io.Serializable;

/**
 	购物项
 */
public class CartItem implements Serializable{
	/** 购物项的商品 */
	private Item item;
	/** 该购物项 在购物车的数量 */
	private int  num;
	/** 小计 */
	private double subtotal;
	/** 购物项状态  0 代表 未选中  1代表选中*/
	private int checked = 0;
	private Long item_id;
	private Long cart_id;
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
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
		this.subtotal = (double) Math.round(subtotal* 100) / 100;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public Long getCart_id() {
		return cart_id;
	}
	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}
	
	
	
}
