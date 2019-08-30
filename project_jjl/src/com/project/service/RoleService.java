package com.project.service;

import java.util.List;

import com.project.dao.PrivilegeDao;
import com.project.dao.RoleDao;
import com.project.po.Privilege;
import com.project.po.Role;

/**
 * 角色业务层
 * @author Administrator
 *
 */
public class RoleService {
	private RoleDao roleDao = new RoleDao();
	private PrivilegeDao privilegeDao = new PrivilegeDao();
	/**
	 * 根据id查询所有角色
	 * @param roleId
	 * @return
	 */
	public Role findRoleById(Long roleId) {
		return roleDao.findRoleById(roleId);
	}
	
	
	/**
	 * 查询所有角色信息
	 * @return
	 */
	public List<Role> findAll() {
		
		return roleDao.findAll();
	}


	/**
	 * 查询所有权限与角色根据角色id和权限id
	 * @param id
	 * @return
	 */
	public List<Privilege> findAllPrivilegeAndRolePrivilegeByRoleId(int id) {
		//1.获取所有权限,根据权限菜单数据
		List<Privilege>list = privilegeDao.findAll();
		for (Privilege privilege : list) {
			boolean b = privilegeDao.hasPrivilege(id,privilege.getId());
			if (b) {
				privilege.setChecked(true);
			}
		}
		return list;
	}


	/**
	 * 修改权限
	 * @param roleId
	 * @param privilegeIds
	 */
	public void updatePrivilege(long roleId, String privilegeIds) {
		//1.清空roleId之前的权限
		privilegeDao.delete(roleId);
		//2.添加roleId,privilegeIds权限
		for(String privilegeId : privilegeIds.split(",")){
			Long priId = Long.parseLong(privilegeId);
			privilegeDao.addPrivilege(roleId,priId);
		}
		
	}


	/**
	 * 清除所有权限
	 * @param roleId
	 */
	public void clearPrivilege(long roleId) {
		privilegeDao.delete(roleId);
		
	}


	/**将添加的数据保存到数据库
	 * @param role
	 */
	public void save(Role role) {
		roleDao.save(role);
		
	}


	/**
	 * 修改角色信息
	 * @param role
	 */
	public void updateRole(Role role) {
		roleDao.updateRole(role);
		
	}


	/**
	 * 删除角色
	 * @param id
	 */
	public void delete(int id) {
		roleDao.delete(id);
		
	}

}
