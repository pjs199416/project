package com.project.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {

	//1.获得数据源
	private static DataSource dataSource;
	static{
		//2.
		dataSource = new ComboPooledDataSource();
	}
	//3.获得连接池
	private static Connection connection;
	//4.从连接池中获得一个连接
	public static Connection getConnection() throws SQLException{
		if (connection == null) {
			connection = dataSource.getConnection();
		}
		return connection;
	}
	//5.获取一个数据源
	public static DataSource getDataSource(){
		return dataSource;
	}
	//6.关闭资源,也就是将使用完的连接放入连接池中
	public static void closeResource() throws SQLException{
		if (connection!=null) {
			connection.close();
		}
	}
	//7.开启事务
	public static void beginTransaction() throws SQLException{
		//获得一个 连接
		connection = getConnection();
		if (connection != null) {
			//开启
			connection.setAutoCommit(false);
		}
	}
	//8.提交事务
	public static void commitTransaction() throws SQLException{
		if (connection != null) {
			connection.commit();
		}
	}
	//9.回滚事务
	public  static void rollbackTrancation(){
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
