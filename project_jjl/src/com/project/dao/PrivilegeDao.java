package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.project.po.Privilege;
import com.project.util.C3P0Utils;

/**
 * 权限操作数据库层
 * @author Administrator
 *
 */
public class PrivilegeDao {

	private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());

	
	public void add(Privilege privilege) {
		try {
			String sql = "insert into privilege(name,url,parentId) values(?,?,?)";
			runner.update(sql, privilege.getName(),privilege.getUrl(),privilege.getParentId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public List<Privilege> findAll() {
		try {
			String sql = "select id,name,url,parentId PId from privilege";
			List<Privilege> list = runner.query(sql, new BeanListHandler<>(Privilege.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean hasPrivilege(int id, Long id2) {
		try {
			String sql = "select count(*) from role_privilege where role_id=? and privilege_id=?";
			Long count = runner.query(sql, new ScalarHandler<Long>(),id,id2);
			if (count != null && count == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void delete(long roleId) {
		try {
			String sql = "delete from role_privilege where role_id=?";
			runner.update(sql,roleId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void addPrivilege(long roleId, Long priId) {
		try {
			String sql = "insert into role_privilege (role_id,privilege_id) values(?,?)";
			runner.update(sql,roleId,priId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Privilege> findTopPrivileges() {
		try {
			String sql = "select * from privilege where parentId is null";
			List<Privilege> list = runner.query(sql, new BeanListHandler<>(Privilege.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Privilege> findChildrenPrivilegeByParentId(Long parentId) {
		try {
			String sql = "select * from privilege where parentId = ?";
			List<Privilege> list = runner.query(sql, new BeanListHandler<>(Privilege.class),parentId);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> findAllPrivilegeUrls() {
		try {
			String sql = "select url from privilege where url is not null";
			List<String> list = runner.query(sql, new ColumnListHandler<String>());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Long> findPrivilegeIdsByRoleId(Long roleId) {
		try {
			String sql = "select privilege_id from role_privilege where role_id=?";
			List<Long> list = runner.query(sql, new ColumnListHandler<Long>(),roleId);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Privilege findPrivilegeById(Long privilegeId) {
		try {
			String sql = "select * from privilege where id = ?";
			Privilege privilege = runner.query(sql, new BeanHandler<>(Privilege.class),privilegeId);
			return privilege;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
