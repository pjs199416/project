package com.project.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 	权限表 
 * */
public class Privilege implements Serializable {
	/**权限id */
	private Long id;
	/**权限名字 */
	private String name;
	/**权限访问路径 */
	private String url;
	/**父权限id */
	private Long parentId; 
	/** 一个权限可以被多个角色拥有  */
	private List<Role> roles = new ArrayList<Role>();
	
	/** 当前权限 对应多个子权限 */
	private List<Privilege> children = new ArrayList<Privilege>();
	/** 当前权限 只可以有一个父权限  */
	private Privilege parent;
	
	/** 是否选中  z-tree*/
	private boolean checked = false;
	/** 是否打开  z-tree*/
	private boolean open = true;
	/** 是否选中  z-tree 父id*/
	private Long pId;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Privilege> getChildren() {
		return children;
	}
	public void setChildren(List<Privilege> children) {
		this.children = children;
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	public Privilege(String name, String url, Long parentId) {
		this.name = name;
		this.url = url;
		this.parentId = parentId;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public Privilege() {
		
	}
	
}
