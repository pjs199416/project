package com.project.service;

import java.util.List;

import com.project.dao.PrivilegeDao;
import com.project.po.Privilege;

public class PrivilegeService {

	private PrivilegeDao privilegeDao = new PrivilegeDao();
	/**
	 * 查询所有顶级权限
	 * @return
	 */
	public List<Privilege> findTopPrivileges() {
		//1.从数据库中查询顶级权限菜单
		List<Privilege>list = privilegeDao.findTopPrivileges();
		//2.遍历顶级权限,映射顶级菜单对应的子及菜单
		for (Privilege topprivilege : list) {
			//3.获取顶级菜单id
			Long parentId = topprivilege.getId();
			//4.根据父Id查询子级权限集合
			List<Privilege>childrens = privilegeDao.findChildrenPrivilegeByParentId(parentId);
			//5.映射顶级菜单的子级菜单的集合
			topprivilege.setChildren(childrens);
		}
		return list;
	}

	/**
	 * 查询权限的url
	 * @return
	 */
	public List<String> findAllPrivilegeUrls() {
		
		return privilegeDao.findAllPrivilegeUrls();
	}

}
