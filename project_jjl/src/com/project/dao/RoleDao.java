package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.project.po.Role;
import com.project.po.User;
import com.project.util.C3P0Utils;

public class RoleDao {

	private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());

	public Role findRoleById(Long roleId) {
		try {
			String sql = "select * from role where id = ?";
			Role role = runner.query(sql, new BeanHandler<>(Role.class),roleId);
			return role;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addUser_Role(User user, Role role) {
		try {
			String sql = "insert into user_role (user_id,role_id) values(?,?)";
			runner.update(sql,user.getId(),role.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Role> findAll() {
		try {
			String sql = "select * from role";
			List<Role> list = runner.query(sql, new BeanListHandler<>(Role.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Long> findByRoleIdAndByUserId(Long id) {
		try {
			String sql = "select role_id from user_role where user_id = ?";
			List<Long> list = runner.query(sql, new ColumnListHandler<Long>(),id);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void save(Role role) {
		try {
			String sql = "insert into role (name,description) values(?,?)";
			runner.update(sql,role.getName(),role.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateRole(Role role) {
		try {
			String sql = "update role set name = ?,description = ? where id =?";
			runner.update(sql,role.getName(),role.getDescription(),role.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void delete(int id) {
		try {
			String sql = "delete from role where id = ?";
			runner.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteUserRole(User user) {
		try {
			String sql = "delete from user_role where user_id=?";
			runner.update(sql,user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
