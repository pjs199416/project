package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.project.po.Addr;
import com.project.util.C3P0Utils;

/**
 * 地址信息数据库操作
 * @author Administrator
 *
 */
public class AddrDao {

	private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());

	public List<Object[]> findProvinceAll() {
		try {
			String sql = "select id,name from china_city where level = 1";
			List<Object[]> list = runner.query(sql, new ArrayListHandler());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Object[]> findCityAll(Long provinceId) {
		try {
			String sql = "select id,name from china_city where pid=?";
			List<Object[]> list = runner.query(sql, new ArrayListHandler(),provinceId);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Object[]> findDistrictAll(Long cityId) {
		try {
			String sql = "select id,name from china_city where pid = ?";
			List<Object[]> list = runner.query(sql, new ArrayListHandler(),cityId);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveAddr(Addr addr) {
		
		try {
			String sql = "insert into addr (user_id,province,city,district,description,shName,shPhone,status) values(?,?,?,?,?,?,?,?)";
			runner.update(sql,
					addr.getUser().getId(),
					addr.getProvince(),
					addr.getCity(),
					addr.getDistrict(),
					addr.getDescription(),
					addr.getShName(),
					addr.getShPhone(),
					addr.getStatus());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Addr> findAddrAll(Long user_id) {
		try {
			String sql = "select * from addr where user_id=?";
			List<Addr> list = runner.query(sql, new BeanListHandler<>(Addr.class),user_id);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteAddr(String id) {
		try {
			String sql = "delete from addr where id = ?";
			runner.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Addr findAddrById(Long id) {
		try {
			String sql = "select * from addr where id = ?";
			Addr addr = runner.query(sql, new BeanHandler<>(Addr.class),id);
			return addr;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateAddr(Addr addr) {
		try {
			String sql = "update addr set user_id=?,province=?,city=?,district=?,description=?,shName=?,shPhone=?,status=? where id = ?";
			runner.update(sql,
					addr.getUser().getId(),
					addr.getProvince(),
					addr.getCity(),
					addr.getDistrict(),
					addr.getDescription(),
					addr.getShName(),
					addr.getShPhone(),
					addr.getStatus(),
					addr.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateAddrStatus(Addr addr) {
		try {
			String sql1 = "update addr set status = 0";
			runner.update(sql1);
			String sql="update addr set status = ? where id =?";
			runner.update(sql,addr.getStatus(),addr.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
