package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.project.po.User;
import com.project.util.C3P0Utils;

public class UserDao {
	private QueryRunner runner = new  QueryRunner(C3P0Utils.getDataSource());
	public User login(String username, String md5Pwd){
		try {
			String sql = "select * from user where name=? and pwd=? and status = 1";
			User user = runner.query(sql, new BeanHandler<>(User.class),username,md5Pwd);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void update(User user) throws SQLException {
		String sql = "update user set name=?,phone=?,status=? where id = ?";
		runner.update(sql,user.getName(),user.getPhone(),user.getStatus(),user.getId());
		
	}
	public int findCount(String name) throws SQLException {
		String sql = "select count(*) from user where name like ?";
		Long count = runner.query(sql, new ScalarHandler<Long>(),name);
		if (count != null) {
			return count.intValue();
		}
		return 0;
	}
	public List<User> findByPage(int begin, int pageSize, String name) throws SQLException {
		String sql = "select * from user where name like ? order by id desc limit ?,?";
		List<User> list = runner.query(sql, new BeanListHandler<>(User.class),name,begin,pageSize);
		return list;
	}
	public void add(User user) {
		try {
			String sql = "insert into user (id,name,pwd,phone,pic,status) values(?,?,?,?,?,?)";
			runner.update(sql,user.getId(),user.getName(),user.getPwd(),user.getPhone(),user.getPic(),user.getStatus());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public User findUserById(Long id) {
		try {
			String sql = "select * from user where id = ?";
			User user = runner.query(sql, new BeanHandler<>(User.class),id);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void updatePic(User user) throws SQLException {
		String sql = "update user set name=?,phone=?,status=? where id = ?";
		runner.update(sql,user.getPic(),user.getId());
		
	}
	public void updatePwd(User user) throws SQLException {
		String sql = "update user set pwd=? where id = ?";
		runner.update(sql,user.getPwd(),user.getId());
		
	}
	public void updateStatus(Long id, int status) {
		try {
			String sql = "update user set status = ? where id = ?";
			runner.update(sql,status,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void resetPwd(Long id, String md5Str) {
		try {
			String sql = "update user set pwd =? where id = ?";
			runner.update(sql,md5Str,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void addRegist(User user) {
		try {
			String sql = "insert into user (id,name,pwd,phone,status) values(?,?,?,?,?)";
			runner.update(sql,user.getId(),user.getName(),user.getPwd(),user.getPhone(),user.getStatus());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
