package com.project.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.po.Cart;
import com.project.po.CartItem;
import com.project.po.Item;
import com.project.po.Orders;
import com.project.po.OrdersItem;
import com.project.po.User;
import com.project.service.OrdersService;
import com.project.util.UUIDUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;

/**
 * 订单Action
 */
@WebServlet("/afterLogin/orders.action")
public class OrdersAction extends BaseServlet {
	private OrdersService ordersService = new OrdersService();

	/**
	 * 跳转到添加订单页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String toSubmitOrderUI(HttpServletRequest request,HttpServletResponse response){
		
		//1.获得提交的购物项id
		String cartItemIds = request.getParameter("cartItemIds");
		String[] split = cartItemIds.split(",");
		//2.从会话中获取购物车信息
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		//3.获取购物项数据
		Map<Long, CartItem> map = cart.getMap();
		//4.获取订单提交页面数据
		List<OrdersItem> list = new ArrayList<OrdersItem>();
		//5.获得已经选中的总价
		double total = cart.getTotal();
		for(String str :split ){
			//6.遍历出选中的购物项id
			Long carItemId = Long.parseLong(str);
			//7.获取选中的购物项数据
			CartItem cartItem = map.get(carItemId);
			//8.获取提交订单项
			OrdersItem ordersItem = new OrdersItem();
			//9.设置订单项id
			ordersItem.setId(UUIDUtils.getUUID());
			//10.获取订单商品
			Item item = cartItem.getItem();
			ordersItem.setItem(item);
			//11.获取订单项目购买数量
			int num = cartItem.getNum();
			ordersItem.setNum(num);
			//12.获取订单项小计
			double subtotal = cartItem.getSubtotal();
			ordersItem.setSubtotal(subtotal);
			list.add(ordersItem);
			
		}
		
		//存储所有订单集合
		request.setAttribute("list", list);
		//存储订单总价
		request.setAttribute("total", total);
		return "/WEB-INF/front/afterLogin/trade.jsp";
		
	}
	
	/**
	 * 提交订单
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void submitOrders(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.准备订单数据
		Orders orders = new Orders();
		//2.生成订单编号
		orders.setId(UUIDUtils.getUUID());
		
		//3.生成订单时间
		orders.setCreatedate(new Timestamp(new Date().getTime()));
		//4.获取订单项
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Map<Long, CartItem> map = cart.getMap();
		List<OrdersItem>list = new ArrayList<>();
		String ids = request.getParameter("ids");
		String[] split = ids.split(",");
		for (String str : split) {
			//6.遍历出选中的购物项id
			Long cartItemId = Long.parseLong(str);
			//7.获取选中的购物项数据
			CartItem cartItem = map.get(cartItemId);
			//8.获取提交订单项
			OrdersItem ordersItem = new OrdersItem();
			//9.设置订单项id
			ordersItem.setId(UUIDUtils.getUUID());
			//10.获取订单商品
			Item item = cartItem.getItem();
			ordersItem.setItem(item);
			//11.获取订单项目购买数量
			int num = cartItem.getNum();
			ordersItem.setNum(num);
			//12.获取订单项小计
			double subtotal = cartItem.getSubtotal();
			ordersItem.setSubtotal(subtotal);
			ordersItem.setOrders(orders);
			list.add(ordersItem);
		}
		double total = cart.getTotal();
		orders.setList(list);
		orders.setTotal(total);
		//13.设置订单收货信息
		String shAddr = request.getParameter("shAddr");
		String shName = request.getParameter("shName");
		String shPhone = request.getParameter("shPhone");
		
		orders.setShAddr(shAddr);
		orders.setShName(shName);
		orders.setShPhone(shPhone);
		//14.设置订单状态默认未支付
		orders.setStatus(0);
		//15.获取订单属有人
		User user = (User) session.getAttribute("frontUser");
		orders.setUser(user);
		//16.保存订单到数据库
		ordersService.addOrders(orders);
		session.setAttribute("orders", orders);
		response.sendRedirect(request.getContextPath()+"/afterLogin/orders.action?method=ordersList");
	}
	
	
	/**
	 * 订单列表查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void ordersList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("frontUser");
		//从数据库中查询所有订单
		List<Orders>list =ordersService.findOrdersList(user.getId());
		session.setAttribute("ordersList", list);
		response.sendRedirect(request.getContextPath()+"/afterLogin/orders.action?method=orderListUI");
	}
	
	/**
	 * 跳转到订单页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String orderListUI(HttpServletRequest request,HttpServletResponse response){
		
		return "/WEB-INF/front/afterLogin/orderlist.jsp";
		
	}
	
	/**
	 * 删除订单信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteOrders(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String id = request.getParameter("id");
		System.out.println("订单id="+id);
		ordersService.deleteOrders(id);
		HttpSession session = request.getSession();
		session.removeAttribute("orders");
		response.sendRedirect(request.getContextPath()+"/afterLogin/orders.action?method=ordersList");
	}
}
