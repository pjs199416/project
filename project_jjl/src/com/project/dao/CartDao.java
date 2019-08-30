package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.project.po.Cart;
import com.project.po.CartItem;
import com.project.util.C3P0Utils;

public class CartDao {

	private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
	
	public Cart findCartByUserId(Long id) {
		try {
			String sql = "select * from cart where user_id=?";
			Cart car = runner.query(sql, new BeanHandler<Cart>(Cart.class),id);
			return car;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<CartItem> findCartItemByCartId(Long cartId) {
		try {
			String sql = "select * from cart_item where cart_id=?";
			List<CartItem> list = runner.query(sql,  new BeanListHandler<CartItem>(CartItem.class),cartId);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteCarByUserId(Long user_id) {
		try {
			String sql = "delete from cart where user_id = ?";
			runner.update(sql,user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addCart(Cart cart) {
		try {
			String sql = "insert into cart (id,total,cartNum,user_id) values(?,?,?,?)";
			runner.update(sql,cart.getId(),cart.getTotal(),cart.getCartNum(),cart.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addCartItem(CartItem cartItem) {
		try {
			String sql = "insert into cart_item (num,subtotal,cart_id,item_id,checked) values(?,?,?,?,?)";
			runner.update(sql,cartItem.getNum(),cartItem.getSubtotal(),cartItem.getCart_id(),cartItem.getItem_id(),cartItem.getChecked());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
