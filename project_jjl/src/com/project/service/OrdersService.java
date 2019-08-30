package com.project.service;

import java.util.List;

import com.project.dao.ItemDao;
import com.project.dao.OrdersDao;
import com.project.po.Item;
import com.project.po.Orders;
import com.project.po.OrdersItem;

/**
 * 处理订单业务层
 * @author Administrator
 *
 */
public class OrdersService {

	private OrdersDao ordersDao = new OrdersDao();
	private ItemDao itemDao = new ItemDao();

	public void addOrders(Orders orders) {
		//1.保存订单到数据库
		ordersDao.saveOrders(orders);
		//2.保存订单项到数据库
		List<OrdersItem> list = orders.getList();
		
		for (OrdersItem ordersItem : list) {
			ordersDao.saveOrdersItem(ordersItem);
		}
	}

	/**
	 * 查询所有订单列表
	 * @param id
	 * @return
	 */
	public List<Orders> findOrdersList(Long id) {
		List<Orders> list =ordersDao.findOrdersList(id);
		for (Orders orders : list) {
			List<OrdersItem> ordersItems = ordersDao.findOrdersItemByOrderId(orders.getId());
			for (OrdersItem ordersItem : ordersItems) {
				Long itemId = ordersItem.getItem_id();
				Item item = itemDao.findModelById(itemId);
				ordersItem.setItem(item);
			}
			orders.setList(ordersItems);
		}
		return list;
	}

	public void deleteOrders(String id) {
		ordersDao.deleteOrders(id);
		
	}
}
