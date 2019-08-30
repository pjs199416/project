package com.project.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**    角色表 */
public class Role implements Serializable {
	/** 角色id */
	private Long id;
	/** 角色 名字 */
	private String name;
	/** 角色描述  */
	private String description;
	
	/** 角色可以被多个用户拥有 */
	private List<User> users = new ArrayList<User>();
	
	/** 一个角色可以拥有多个 权限 */
	private List<Privilege> privileges = new ArrayList<Privilege>();
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
}
