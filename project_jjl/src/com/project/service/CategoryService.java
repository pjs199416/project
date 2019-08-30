package com.project.service;

import java.sql.SQLException;
import java.util.List;

import com.project.dao.CategoryDao;
import com.project.po.ItemCategory;

public class CategoryService {

	private CategoryDao dao = new CategoryDao();

	/**
	 * 查询所有商品分类信息
	 * @return
	 * @throws SQLException
	 */
	public List<ItemCategory> findAll() throws SQLException {
		
		return dao.findAll();
	}

	/**
	 * 将添加的商品分类保存到数据库
	 * @param category
	 * @throws SQLException 
	 */
	public void save(ItemCategory category) throws SQLException {
		int sortMax = dao.findBySortMax();
		category.setSort(sortMax++);
		dao.save(category);
		
	}

	/**
	 * 根据id查询商品分类信息将数据显示到修改页面
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ItemCategory findCategoryById(int id) throws SQLException {
		
		return dao.findCategoryById(id);
	}

	/**
	 * 修改商品分类信息
	 * @param category
	 * @throws SQLException
	 */
	public void update(ItemCategory category) throws SQLException {
		dao.update(category);
		
	}

	/**
	 * 删除商品分类信息
	 * @param id
	 * @throws SQLException
	 */
	public void delete(int id) throws SQLException {
		dao.delete(id);
		
	}

	/**
	 * 向上移动商品分类信息
	 * @param id
	 * @throws SQLException
	 */
	public void moveUp(int id) throws SQLException {
		ItemCategory category = dao.findCategoryById(id);
		int sort = category.getSort();
		ItemCategory before = dao.moveUp(sort);
		if (before != null) {
			//就交换两个对象排序字段
			int t = before.getSort();
			category.setSort(t);
			before.setSort(sort);
			//将对象修改到数据库中
			dao.update(before);
			dao.update(category);
		}
	}

	/**
	 * 向下移动商品分类信息
	 * @param id
	 * @throws SQLException
	 */
	public void moveDown(int id) throws SQLException {
		ItemCategory category = dao.findCategoryById(id);
		int sort = category.getSort();
		ItemCategory after = dao.moveDown(sort);
		if (after != null) {
			//就交换两个对象排序字段
			int t = after.getSort();
			category.setSort(t);
			after.setSort(sort);
			//将对象修改到数据库中
			dao.update(after);
			dao.update(category);
		}
	}

	/**
	 * 商品启用
	 * @param id
	 * @throws SQLException
	 */
	public void enable(int id) throws SQLException {
		ItemCategory category = dao.findCategoryById(id);
		category.setStatus(1);
		dao.update(category);
		
	}

	/**
	 * 商品禁用
	 * @param id
	 * @throws SQLException
	 */
	public void disable(int id) throws SQLException {
		ItemCategory category = dao.findCategoryById(id);
		category.setStatus(0);
		dao.update(category);
		
	}

	public List<ItemCategory> findAllList() {
		List<ItemCategory>list = dao.findAllList();
		return list;
	}

	
}
