package com.project.po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品类别
 * @author Administrator
 *
 */
public class ItemCategory implements Serializable {
	/**--id　商品分类主键--*/
	private Long id;
	/**--name　商品分类名字--*/
	private String name; 
	/**--sort　商品分类排序字段--*/
	private int sort; 
	/**--status　商品分类状态--*/
	private int status;
	/**--createdate　商品分类创建时间--*/
	private Timestamp createdate;
	/**--list　商品分类一对多个 商品 关系--*/
	private List<Item> list = new ArrayList<Item>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public List<Item> getList() {
		return list;
	}
	public void setList(List<Item> list) {
		this.list = list;
	}
}
