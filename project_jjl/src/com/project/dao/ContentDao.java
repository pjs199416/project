package com.project.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import com.project.po.Content;
import com.project.util.C3P0Utils;



public class ContentDao {
	private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
	public List<Content> findListByCategoryId(Long contentCategoryId) {
		try {
			String sql = "select * from content where content_category_id = ? ";
			List<Content> list = queryRunner.query(sql, new BeanListHandler<Content>(Content.class),contentCategoryId);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void add(Content content) {
		try {
			String sql = "insert into content(title,description,url,pic,createdate,price,weight,num,status,content_category_id) values(?,?,?,?,?,?,?,?,?,?)";
			queryRunner.update(sql,
					content.getTitle(),
					content.getDescription(),
					content.getUrl(),
					content.getPic(),
					content.getCreatedate(),
					content.getPrice(),
					content.getWeight(),
					content.getNum(),
					content.getStatus(),
					content.getContentCategory().getId()
					);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public Content findContentById(int id) {
		try {
			String sql = "select * from content  where id = ?";
			Content content = queryRunner.query(sql, new BeanHandler<Content>(Content.class),id);
			return content;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map findContentAndContentCategoryById(int id) {
		try {
			String sql = "select a.*,b.name from content a, content_category b  where a.content_category_id=b.id and a.id = ?";
			Map<String,Object> map = queryRunner.query(sql, new MapHandler(),id);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void edit(Content content) {
		try {
			String sql = "update content set title=?,description=?,url=?,pic=?,createdate=?,price=?,weight=?,num=?,status=?"
					+ " where id = ?";
			queryRunner.update(sql,
					content.getTitle(),
					content.getDescription(),
					content.getUrl(),
					content.getPic(),
					content.getCreatedate(),
					content.getPrice(),
					content.getWeight(),
					content.getNum(),
					content.getStatus(),
					//content.getContentCategory().getId(),
					content.getId()
					);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
