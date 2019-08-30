package com.project.service;

import java.sql.SQLException;
import java.util.List;

import com.project.dao.ContentCategoryDao;
import com.project.po.ContentCategory;

/**
 * 内容分类业务层
 * @author Administrator
 *
 */
public class ContentCategoryService {

	private ContentCategoryDao dao = new ContentCategoryDao();

	/**
	 * 到数据库中查询所有内容分类信息
	 * @return
	 * @throws SQLException
	 */
	public List<ContentCategory> findAll() throws SQLException {
		List<ContentCategory>list = dao.findAll();
		return list;
	}

	/**
	 * 将数据保存到数据库中
	 * @param contentCategory
	 * @throws SQLException 
	 */
	public void save(ContentCategory contentCategory) throws SQLException {
		dao.save(contentCategory);
	}

	/**
	 * 根据id到数据库中查询内容分类信息
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public ContentCategory findContentCategoryById(int id) throws SQLException {	
		return dao.findContentCategoryById(id);
	}

	/**
	 * 将修改的数据保存到数据库中
	 * @param category
	 * @throws SQLException 
	 */
	public void update(ContentCategory category) throws SQLException {
		dao.update(category);
		
	}

	/**
	 * 删除内容分类信息
	 * @param id
	 * @throws SQLException 
	 */
	public void delete(int id) throws SQLException {
		 
		dao.delete(id);
	}

	/**
	 * 启用内容分类信息
	 * @param id
	 * @throws SQLException
	 */
	public void enable(int id) throws SQLException {
		ContentCategory category = dao.findContentCategoryById(id);
		category.setStatus(1);
		dao.update(category);
		
	}

	/**
	 * 禁用内容分类信息
	 * @param id
	 * @throws SQLException
	 */
	public void disable(int id) throws SQLException {
		ContentCategory category = dao.findContentCategoryById(id);
		category.setStatus(0);
		dao.update(category);
		
	}
}
