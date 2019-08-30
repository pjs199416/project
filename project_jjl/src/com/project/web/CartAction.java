package com.project.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.po.Cart;
import com.project.po.CartItem;
import com.project.po.Item;
import com.project.po.User;
import com.project.service.CartService;
import com.project.service.ItemService;

/**
 * 购物车Action层
 */
@WebServlet("/cart.action")
public class CartAction extends BaseServlet {
	private ItemService itemService = new ItemService();
	private CartService cartService = new CartService();
	/**
	 *添加购物车
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void addCartUI(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			//1.获取提交参数id
			Long id = Long.parseLong(request.getParameter("id"));
			Item item = itemService.findItemById(id);
			int num = Integer.parseInt(request.getParameter("num"));
			//2.生成总价格
			double currentTotal = num*item.getPrice();
			//3.创建会话对象
			HttpSession session = request.getSession();
			//4.从会话中取出购物车对象
			Cart  cart = (Cart) session.getAttribute("cart");
			if (cart!=null) {
				//5.取出购物车所有购物项
				Map<Long, CartItem> map = cart.getMap();
				//6.取出购物车所有商品集合
				Set<Long> itemIds = map.keySet();
				if (itemIds.contains(id)) {
					//7.取出购物车对象,如果购物车包含这个购物项则改变购物项的数量和小计以及购物车总价
					CartItem cartItem = map.get(id);
					cartItem.setChecked(1);
					cartItem.setNum(cartItem.getNum()+1);
					cartItem.setSubtotal(cartItem.getSubtotal()+currentTotal);
					map.put(id,cartItem);
					
				}else {
					//8.购物车不包含这个购物项,则就在购物车中添加这个购物项,然后再修改总价
					CartItem cartItem = new CartItem();
					cartItem.setChecked(1);
					cartItem.setItem(item);
					cartItem.setNum(num);
					cartItem.setSubtotal(currentTotal);
					cart.getMap().put(id, cartItem);
					
				}
				
			}else {
				//9.如果本次会话中没有购物对象,则就创建对象,再添加商品信息
				cart = new Cart();
				//10.准备购物项
				CartItem cartItem = new CartItem();
				cartItem.setChecked(1);
				cartItem.setItem(item);
				cartItem.setNum(num);
				cartItem.setSubtotal(currentTotal);
				Map<Long, CartItem>map = new HashMap<>();
				map.put(id, cartItem);
				//11.添加购物车
				cart.setMap(map);
				
			}
			
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath()+"/cart.action?method=cartUI");
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转到购物车页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String cartUI(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("cart");
		if (obj!=null) {
			Cart cart = (Cart) obj;
			//从购物车中取出购物列表
			Map<Long, CartItem> map = cart.getMap();
			double total = 0.0;
			int cartNum = 0;
			//遍历购物项
			for(CartItem cartItem :map.values()){
				if (cartItem.getChecked()==1) {
					total+=cartItem.getSubtotal();
					cartNum+=cartItem.getNum();
				}
			}
			
			cart.setCartNum(cartNum);
			cart.setTotal(total);
			session.setAttribute("cart", cart);
			//把cart保存到数据库
			//判断用户是否登录
			User user = (User) session.getAttribute("frontUser");
			if (user != null) {
				cart.setUser_id(user.getId());
				cartService.addCart(cart);
			}
		}
		
		
		return "/WEB-INF/front/page/car.jsp";
		
	}
	
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void clearCart(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		response.sendRedirect(request.getContextPath()+"/cart.action?method=cartUI");
	}
	
	/**
	 * 删除购物车项
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleCartItem(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获得要删除的购物项目
		Long id = Long.parseLong(request.getParameter("id"));
		//2.从会话中取出购物车对象
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		Map<Long,CartItem> map  = cart.getMap();
		map.remove(id);
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/cart.action?method=cartUI");
	}
	
	/**
	 * 批量删除购物项
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delBatchCarItem(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		String ids = request.getParameter("ids");
		if (ids != null && !"".equals("ids")) {
			//从会话中取出购物车对象的购物项列表
			Map<Long, CartItem> map = cart.getMap();
			String[] split = ids.split(",");
			for (String string : split) {
				//根据id删除购物项
				Long id = Long.parseLong(string);
				map.remove(id);
			}
		}
		
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/cart.action?method=cartUI");
	}
	
	
	/**
	 * 修改购物项数量
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateCarItemNum(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获得要修改的购物项目
		Long id = Long.parseLong(request.getParameter("id"));
		//2.获得要修改的数量
		int num = Integer.parseInt(request.getParameter("num"));
		//3.从会话中取出购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		//4.从购物车中取出购物列表
		Map<Long,CartItem> map  = cart.getMap();
		//5.从购物列表中取出要修改的购物项目
		CartItem cartItem = map.get(id);
		//6.修改购物项数量
		cartItem.setNum(num);
		//7.修改购物项小计
		double newSubtotal = cartItem.getItem().getPrice()*num;
		cartItem.setSubtotal(newSubtotal);
		cartItem.setChecked(1);
		//8.修改总价格
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/cart.action?method=cartUI");
		
	}
	
	
	/**
	 * 修改购物项 选中状态 
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateCarItemChecked(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取要修改的购物项id
		Long id = Long.parseLong(request.getParameter("id"));
		//2.获取选中的状态
		int checked = Integer.parseInt(request.getParameter("checked"));
		//3.从会话中获取购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		//4.从购物车中取出购物列表
		Map<Long, CartItem> map = cart.getMap();
		//5.从购物列表中取出要修改的购物项
		CartItem cartItem = map.get(id);
		cartItem.setChecked(checked);
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/cart.action?method=cartUI");
	}
	
	/**
	 * 批量选中购物车状态
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateCarItemCheckedAll(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取选中的状态
		int checked = Integer.parseInt(request.getParameter("checked"));
		//2.从会话中获取购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		//3.从购物车中取出购物列表
		Map<Long, CartItem> map = cart.getMap();
		//4.取出所有购物项目
		Collection<CartItem> cartItems = map.values();
		//5.遍历购物项
		for (CartItem cartItem : cartItems) {
			cartItem.setChecked(checked);
		}
		
		session.setAttribute("checked", checked);
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/cart.action?method=cartUI");
	}
	
}
