package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.project.po.ItemCategory;
import com.project.util.C3P0Utils;
import com.sun.org.apache.regexp.internal.recompile;

public class CategoryDao {
	private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
	
	public List<ItemCategory> findAll() throws SQLException {
		String sql = "select * from item_category order by sort asc";
		List<ItemCategory> list = runner.query(sql, new BeanListHandler<>(ItemCategory.class));
		return list;
	}

	public int findBySortMax() throws SQLException {
		String sql = "select max(sort) from item_category";
		Integer sortMax = runner.query(sql, new ScalarHandler<Integer>());
		if(sortMax==null){
			return 0;
		}
		return sortMax;
	}
	
	public void save(ItemCategory category) throws SQLException {
		String sql = "insert into item_category(name,sort,status,createdate) values(?,?,?,?)";
		runner.update(sql,category.getName(),category.getSort(),category.getStatus(),category.getCreatedate());
		
	}

	public ItemCategory findCategoryById(int id) throws SQLException {
		String sql = "select *from item_category where id = ?";
		ItemCategory category = runner.query(sql, new BeanHandler<>(ItemCategory.class),id);
		return category;
	}

	public void update(ItemCategory category) throws SQLException {
		String sql = "update item_category set name=?,sort=?,status=?,createdate=? where id=?";
		runner.update(sql,category.getName(),category.getSort(),category.getStatus(),category.getCreatedate(),category.getId());
		
	}

	public void delete(int id) throws SQLException {
		String sql = "delete from item_category where id = ?";
		runner.update(sql,id);
		
	}

	public ItemCategory moveUp(int sort) throws SQLException {
		String sql = "select * from item_category where sort<? order by sort desc limit 0,1";
		ItemCategory category = runner.query(sql, new BeanHandler<>(ItemCategory.class),sort);
		return category;
	}

	public ItemCategory moveDown(int sort) throws SQLException {
		String sql = "select * from item_category where sort>? order by sort asc limit 0,1";
		ItemCategory category = runner.query(sql, new BeanHandler<>(ItemCategory.class),sort);
		return category;
	}

	public List<ItemCategory> findAllList() {
		try {
			String sql = "select * from item_category where status = 1 order by sort asc";
			List<ItemCategory> list = runner.query(sql, new BeanListHandler<>(ItemCategory.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
