package com.project.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  内容分类实体类
 * @author Administrator
 *
 */
public class ContentCategory implements Serializable {
	/** id 内容分类id */
	private Long id;
	/** name 内容分类 名字 */
	private String name;
	/** description 内容分类描述 */
	private String description;
	/** status  内容分类启用状态  1 是启用 0 是废弃 */
	private int status = 1; 
	/** select 是判断页面是否选中这个内容分类 和数据库无关  */
	private boolean selected = false;
	private List<Content> list = new ArrayList<Content>();
	@Override
	public String toString() {
		return "ContentCategory [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Content> getList() {
		return list;
	}
	public void setList(List<Content> list) {
		this.list = list;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	

}
