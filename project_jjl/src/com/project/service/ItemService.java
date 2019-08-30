package com.project.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.project.dao.ItemDao;
import com.project.po.Item;
import com.project.util.MyDateConverter;

/**
 * 商品信息业务层
 * @author Administrator
 *
 */
public class ItemService {

	private ItemDao dao = new ItemDao();
	
	
	/**
	 * 查询记录数
	 * @param title
	 * @return
	 * @throws SQLException
	 */
	public int findCount(String title) throws SQLException {
		int count = dao.findCount(title);
		return count;
	}
	/**
	 * 商品列表分页显示
	 * @param id 
	 * @param pageSize 
	 * @param currPage 
	 * @return
	 * @throws SQLException
	 */
	public List<Item> findByPage(int begin, int pageSize, String title)throws SQLException {
		List<Item> list = dao.findByPage(begin, pageSize, title);
		return list;
		
	}
	 

	/**
	 * 将添加的商品保存到数据库中
	 * @param item
	 * @throws SQLException
	 */
	public void save(Item item)throws SQLException {
		dao.save(item);
		
	}

	/**
	 * 根据id查询商品信息将数据显示到修改页面上
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Item findItemById(Long id)throws SQLException {
		try {
			Map<String, Object>map = dao.findItemById(id);
			//2.注册日期格式化
			ConvertUtils.register(new MyDateConverter(), Date.class);
			//3.将所有提交数据存储到bean中
			Item item = new Item();
			BeanUtils.populate(item, map);
			//4.将map集合中的商品分类id映射到商品对象中商品分类对象中id属性中
			item.getItemCategory().setId((Long)map.get("item_category_id"));
			item.getItemCategory().setName((String)map.get("name"));
			return item;
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改商品信息
	 * @param item
	 * @throws SQLException
	 */
	public void update(Item item)throws SQLException {
		dao.update(item);
		
	}

	/**启用商品信息
	 * @param id
	 * @throws SQLException
	 */
	public void enable(Long id)throws SQLException {
		try {
			//1.获取商品信息
			Map<String, Object> map = dao.findItemById(id);
			ConvertUtils.register(new MyDateConverter(), Date.class);
			Item item = new Item();
			BeanUtils.populate(item, map);
			item.setStatus(1);
			dao.update(item);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 禁用商品信息
	 * @param id
	 * @throws SQLException
	 */
	public void disable(Long id)throws SQLException {
		try {
			//1.获取商品信息
			Map<String, Object> map = dao.findItemById(id);
			ConvertUtils.register(new MyDateConverter(), Date.class);
			Item item = new Item();
			BeanUtils.populate(item, map);
			item.setStatus(0);
			dao.update(item);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 *查询商品记录数
	 * @param title
	 * @return
	 */
	public int getfindCountBySql(String sqlCount) {
		
		return dao.getfindCountBySql(sqlCount);
	}
	/**
	 * 前台商品分页查询
	 * @param sql
	 * @return
	 */
	public List<Item> findListBySql(String sql) {
		
		return dao.findListBySql(sql);
	}
	
	

}
