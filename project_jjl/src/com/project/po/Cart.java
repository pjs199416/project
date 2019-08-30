package com.project.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
/**
  	购物车
 *
 */
public class Cart implements Serializable {
	private Long id;
	//1.商品总价 
	private double total;  // 1元 = 10 毛 = 100分;
	//2.该购物车中有几个购物项
	private Map<Long,CartItem> map = new HashMap<Long,CartItem>();
	//3.商品总数量
	private int cartNum;
	
	private Long user_id;
	public Map<Long, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<Long, CartItem> map) {
		this.map = map;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {

		this.total = (double) Math.round(total* 100) / 100;
		
	}
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	
}
