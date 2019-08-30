package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.project.po.DataDicType;
import com.project.po.DataItem;
import com.project.util.C3P0Utils;

public class DataDicDao {

	private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
	
	public List<DataDicType> findAll() {
		try {
			String sql = "select * from dict_type order by id desc";
			List<DataDicType> list = runner.query(sql, new BeanListHandler<>(DataDicType.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DataDicType findDataDicTypeById(Long dataDicTypeId) {
		try {
			String sql = "select * from dict_type where id = ?";
			DataDicType type = runner.query(sql, new BeanHandler<>(DataDicType.class),dataDicTypeId);
			return type;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<DataItem> findDataItemListByDataTypeId(Long dataDicTypeId) {
		try {
			String sql = "select * from dict_item where dict_type_id = ?";
			List<DataItem> list = runner.query(sql, new BeanListHandler<>(DataItem.class),dataDicTypeId);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void save(DataItem dataItem,Long dataDicTypeId) {
		try {
			String sql = "insert into dict_item (item_name,status,dict_type_id) values(?,?,?)";
			runner.update(sql,dataItem.getItem_name(),dataItem.getStatus(),dataDicTypeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DataItem findDataItemById(Long id) {
		try {
			String sql = "select * from dict_item where id = ?";
			DataItem dataItem = runner.query(sql, new BeanHandler<>(DataItem.class),id);
			return dataItem;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateDataItem(DataItem dataItem) {
		try {
			String sql = "update dict_item set item_name = ?,status = ? where id = ?";
			runner.update(sql,dataItem.getItem_name(),dataItem.getStatus(),dataItem.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Long findDataDicTypeByTypeId(String typecode) {
		try {
			String sql = "select id from dict_type  where typecode=?";
			Long id = runner.query(sql, new ScalarHandler<Long>(),typecode);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<DataItem> getListDataItem(Long datatypeId) {
		try {
			String sql = "select * from dict_item  where dict_type_id=?";
			List<DataItem> list = runner.query(sql, new BeanListHandler<>(DataItem.class),datatypeId);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void selected(int i,Long id) {
		try {
			String sql = "update dict_item set selected=? where id=?";
			runner.update(sql, i,id);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
