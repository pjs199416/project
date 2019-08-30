package com.project.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/** 用户表 */
public class User implements Serializable{
	/** 用户id */
	private Long id;
	/** 用户名字 */
	private String name;
	/** 用户密码 */
	private String pwd;
	/** 用户手机号 */
	private String phone;
	/** 用户照片路径  */
	private String pic;
	/** 用户状态 1 正常 0 废弃 */
	private int status = 1;
	
	/** 一个用户可以拥有多个角色*/
	private List<Role> list = new ArrayList<Role>(); 
	
	/** 数据库初始化权限路径 */
	private List<String> urls = null ;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", phone=" + phone + ", pic=" + pic + "]";
	}
	public List<Role> getList() {
		return list;
	}
	public void setList(List<Role> list) {
		this.list = list;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * 查看该用户是否拥有某个权限 
	 * @param name
	 * @return
	 */
	public boolean hasPrivilegeByName(String name){
		//1.如果用户名是admin 拥有所有权限
		if("admin".equals(this.name)){
			return true;
		} else {
			boolean hasPrivilege = false;
			//2.否则查看当期用户中拥有的角色
			List<Role> roles = this.getList();
			//3.遍历所有角色 
			for(Role role : roles){
				//4.获取 role拥有的权限 
				List<Privilege> privileges = role.getPrivileges(); 
				for(Privilege privilege : privileges){
					//5.获得具体权限名字
					String privilegeName = privilege.getName();
					if(name.equals(privilegeName)){
						hasPrivilege = true;
					}
				}
			}
			return hasPrivilege;
		}
	}
	
	/**
	 * 查看是否拥有该路径访问的权限
	 * @param url
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String url){
		url = url.replace("UI", "");
		//1.如果用户名是admin 拥有所有权限
		if("admin".equals(this.name)){
			return true;
		} 
		//2.如果权限控制的路径集合 没有该路径 ，该路径不受权限控制
		if(!urls.contains(url)){
			return true;
		} else {
			//3.如果是需要控制的权限路径 查看该用户的角色是否拥有这个权限 
			boolean hasPrivilegeUrl = false;
			//4.获得当前用户的所有角色
			List<Role> roles = this.getList();
			//5.遍历所有角色 
			for(Role role : roles){
			//6.获取 role拥有的权限 
			List<Privilege> privileges = role.getPrivileges(); 
				for(Privilege privilege : privileges){
					//5.获得具体权限名字
					String privilegeUrl = privilege.getUrl();
					if(url.equals(privilegeUrl)){
						hasPrivilegeUrl = true;
					}
				}
			}
			return hasPrivilegeUrl;
		}

	}
	
	
	/**
	 * 查看是否拥有该角色
	 * @param roleId
	 * @return
	 */
	public boolean hasRoleByRoleId(long roleid){
		boolean hasRoleId = false;
		//1.获得当前用户的所有角色
		List<Role> roles = this.getList();
		//2.遍历所有角色 
		for(Role role : roles){
			if(role.getId()==roleid){
				hasRoleId = true;
			}
		}
		return hasRoleId;
	}
	
	
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
}
