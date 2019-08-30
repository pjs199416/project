package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.project.po.ContentCategory;
import com.project.util.C3P0Utils;

/**
 * 内容分类信息操作数据库层
 * @author Administrator
 *
 */
public class ContentCategoryDao {

	private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());

	public List<ContentCategory> findAll() throws SQLException {
		String sql = "select * from content_category";
		List<ContentCategory> list = runner.query(sql, new BeanListHandler<>(ContentCategory.class));
		return list;
	}

	public void save(ContentCategory contentCategory) throws SQLException {
		String sql = "insert into content_category (name,description,status) values(?,?,?)";
		runner.update(sql,contentCategory.getName(),contentCategory.getDescription(),contentCategory.getStatus());
		
	}

	public ContentCategory findContentCategoryById(int id) throws SQLException {
		String sql = "select * from content_category  where id = ?";
		ContentCategory contentCategory = runner.query(sql, new BeanHandler<ContentCategory>(ContentCategory.class),id);
		return contentCategory;
	}

	public void update(ContentCategory category) throws SQLException {
		String sql = "update content_category set name=?,description=?,status=? where id=?";
		runner.update(sql,category.getName(),category.getDescription(),category.getStatus(),category.getId());
		
	}

	public void delete(int id) throws SQLException {
		String sql = "delete from content_category where id = ?";
		runner.update(sql,id);
		
	}

	public Long findIdByName(String name) {
		try {
			String sql = "select id from content_category where name = ?";
			Long id = runner.query(sql, new ScalarHandler<Long>(),name);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
