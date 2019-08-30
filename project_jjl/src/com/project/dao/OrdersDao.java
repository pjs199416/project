package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.project.po.Orders;
import com.project.po.OrdersItem;
import com.project.util.C3P0Utils;

/**
 * 订单操作数据库
 * @author Administrator
 *
 */
public class OrdersDao {

	private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());

	public void saveOrders(Orders orders) {
		try {
			String sql = "insert into orders (id,createdate,total,status,shAddr,shName,shPhone,user_id) values(?,?,?,?,?,?,?,?)";
			runner.update(sql,
					orders.getId(),
					orders.getCreatedate(),
					orders.getTotal(),
					orders.getStatus(),
					orders.getShAddr(),
					orders.getShName(),
					orders.getShPhone(),
					orders.getUser().getId()
					);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveOrdersItem(OrdersItem ordersItem) {
		//System.out.println("aaaaaaa进来了...");
		try {
			String sql = "insert into orders_item (id,item_id,orders_id,num,subtotal) values(?,?,?,?,?)";
			runner.update(sql,
					ordersItem.getId(),
					ordersItem.getItem().getId(),
					ordersItem.getOrders().getId(),
					ordersItem.getNum(),
					ordersItem.getSubtotal());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Orders> findOrdersList(Long id) {
		try {
			String sql = "select * from orders where user_id=?";
			List<Orders> list = runner.query(sql, new BeanListHandler<>(Orders.class),id);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<OrdersItem> findOrdersItemByOrderId(String id) {
		try {
			String sql = "select * from orders_item where orders_id = ?";
			List<OrdersItem> list = runner.query(sql, new BeanListHandler<>(OrdersItem.class),id);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteOrders(String id) {
		try {
			String sql = "delete from orders where id = ?";
			runner.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
