package com.project.po;

import java.io.Serializable;

/**
 * 数据字典迭代项 描述表
 */
public class DataItem implements Serializable {
	/** id */
	private Long id;
	/** 迭代名字 */
	private String item_name;
	/** 状态 */
	private int status;
	/** 是否选中*/
	private boolean selected = false;
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "DataItem [id=" + id + ", item_name=" + item_name + ", status=" + status + "]";
	}
	
	
}
