package com.project.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.project.po.Item;
import com.project.util.C3P0Utils;

public class ItemDao {
	private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
	
	public int findCount(String title) throws SQLException {
		String sql = "select count(*) from item  where title like ?";
		Long count = (Long) runner.query(sql, new ScalarHandler<Long>(),title);
		return count.intValue();
	}

	public List<Item> findByPage(int begin, int pageSize, String title) throws SQLException {
		String sql = "select * from item  where title like ? order by createdate desc limit ?,?";
		List<Item> list = runner.query(sql, new BeanListHandler<>(Item.class),title,begin,pageSize);
		return list;
	}

	public void save(Item item) throws SQLException {
		String sql = "insert into item(title,description,pic,price,weight,num,status,createdate,item_category_id) values(?,?,?,?,?,?,?,?,?)";
		runner.update(sql,
				item.getTitle(),
				item.getDescription(),
				item.getPic(),item.getPrice(),
				item.getWeight(),item.getNum(),
				item.getStatus(),
				item.getCreatedate(),
				item.getItemCategory().getId()
				);
		
	}

	public Map<String, Object> findItemById(Long id) throws SQLException {
		String sql = "select t.*,c.name from item t,item_category c where t.item_category_id=c.id and t.id=?";
		Map<String, Object> map = runner.query(sql, new MapHandler(),id);
		return map;
	}

	public void update(Item item) throws SQLException {
		String sql = "update item set title=?,description=?,pic=?,price=?,weight=?,num=?,status=?,createdate=? where id = ?";
		runner.update(sql,item.getTitle(),
				item.getDescription(),
				item.getPic(),item.getPrice(),
				item.getWeight(),item.getNum(),
				item.getStatus(),
				item.getCreatedate(),
				item.getId()
				);
		
	}

	public int getfindCountBySql(String sqlCount) {
		try {
			Long count = runner.query(sqlCount, new ScalarHandler<Long>());
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Item> findListBySql(String sql) {
		try {
			List<Item> list = runner.query(sql, new BeanListHandler<>(Item.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Item findModelById(Long itemId) {
		try {
			String sql = "select * from item where id = ?";
			Item item = runner.query(sql, new BeanHandler<Item>(Item.class),itemId);
			return item;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
