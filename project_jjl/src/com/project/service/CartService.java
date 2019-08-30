package com.project.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.project.dao.CartDao;
import com.project.dao.ItemDao;
import com.project.po.Cart;
import com.project.po.CartItem;
import com.project.po.Item;

public class CartService {
	private CartDao cartDao = new CartDao();
	private ItemDao itemDao = new ItemDao();
	
	/**
	 * 查询所有购物车
	 * @param id
	 * @return
	 */
	public Cart findCart(Long id) {
		//1.获得购物车
		Cart cart = cartDao.findCartByUserId(id);
		if (cart != null) {
			//2.获得购物车对应购物项
			Long cartId = cart.getId();
			List<CartItem>list = cartDao.findCartItemByCartId(cartId);
			//3.找到购物项对应商品 
			for (CartItem cartItem : list) {
				Long itemId = cartItem.getItem_id();
				//4.根据商品id找到商品
				Item item = itemDao.findModelById(itemId);
				cartItem.setItem(item);
			}
			Map<Long, CartItem> map = cart.getMap();
			for (CartItem cartItem : list) {
				map.put(cartItem.getItem_id(),cartItem);
			}
		}
		return cart;
	}
	
	/**
	 * 添加购物车,吧购物车的数据保存到数据库中
	 * @param cart
	 */
	public void addCart(Cart cart) {
		
		//1.清空用户购物车
		cartDao.deleteCarByUserId(cart.getUser_id());
		Long cartId = new Date().getTime();
		cart.setId(cartId);
		cartDao.addCart(cart);
		Map<Long, CartItem> map = cart.getMap();
		Collection<CartItem> cartItems = map.values();
		for (CartItem cartItem : cartItems) {
			cartItem.setCart_id(cartId);
			cartItem.setItem_id(cartItem.getItem().getId());
			cartDao.addCartItem(cartItem);
		}
	}

	
}
