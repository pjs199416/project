package com.project.service;

import java.sql.SQLException;
import java.util.List;

import com.project.dao.PrivilegeDao;
import com.project.dao.RoleDao;
import com.project.dao.UserDao;
import com.project.po.Privilege;
import com.project.po.Role;
import com.project.po.User;
import com.project.util.MD5Util;

/**
 * 用户业务层
 * @author Administrator
 *
 */
public class UserService {
	private UserDao userDao = new UserDao();
	private RoleDao roleDao = new RoleDao();
	private PrivilegeDao privilegeDao = new PrivilegeDao();
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public User login(String username, String password){
		//1.密码加密
		String md5Pwd = MD5Util.getMD5Str(password);
		System.out.println("加密密码="+md5Pwd);
		User user = userDao.login(username,md5Pwd);
		if (user !=null) {
			//2.获取用户id
			Long userId = user.getId();
			//3.根据userId查询角色id集合
			List<Long> roleIds = roleDao.findByRoleIdAndByUserId(userId);
			for (Long roleId : roleIds) {
				//4.根据角色id查询角色对象
				Role role = roleDao.findRoleById(roleId);
				//5.将角色对象添加的用户的角色集合中
				user.getList().add(role);
			}
			
			//6.遍历用户的角色集合,映射权限
			for(Role role : user.getList()){
				//7.获取角色id
				Long roleId = role.getId();
				//8.根据角色id查询权限id集合
				List<Long>privilegeIds = privilegeDao.findPrivilegeIdsByRoleId(roleId);
				for (Long privilegeId : privilegeIds) {
					//9.根据权限id查询权限对象
					Privilege privilege = privilegeDao.findPrivilegeById(privilegeId);
					//10.将权限对象添加到角色的权限集合中
					role.getPrivileges().add(privilege);
				}
			}
		}
		return user;
	}
	/**
	 * 用户修改
	 * @param user
	 * @throws SQLException
	 */
	public void update(User user) throws SQLException {
		//user.setPwd(MD5Util.getMD5Str(user.getPwd()));
		userDao.update(user);
		//修改用户所属角色
		List<Role> list = user.getList();
		//清除之前的角色
		roleDao.deleteUserRole(user);
		for (Role role : list) {
			//修改现在的角色
			roleDao.addUser_Role(user, role);
		}
		
	}
	/**
	 * 查询记录数
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public int findCount(String name) throws SQLException {
		int count = userDao.findCount(name);
		return count;
	}
	/**
	 * 分页查询
	 * @param begin
	 * @param pageSize
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public List<User> findByPage(int begin, int pageSize, String name) throws SQLException {
		List<User>list = userDao.findByPage(begin,pageSize,name);
		//遍历所有用户
		for (User user : list) {
			//根据用户id查询所有角色id
			List<Long>roleIds = roleDao.findByRoleIdAndByUserId(user.getId());
			for (Long roleId : roleIds) {
				Role role = roleDao.findRoleById(roleId);
				user.getList().add(role);
			}
		}
		return list;
	}
	/**
	 * 用户添加
	 * @param user
	 */
	public void add(User user) {
		user.setPwd(MD5Util.getMD5Str(user.getPwd()));
		userDao.add(user);
		List<Role> list = user.getList();
		for (Role role : list) {
			roleDao.addUser_Role(user,role);
		}
	}
	/**
	 * 根据id查询所有用户
	 * @param id
	 * @return
	 */
	public User findUserById(Long id) {
		User user = userDao.findUserById(id);
		List<Long> list = roleDao.findByRoleIdAndByUserId(id);
		for (Long roleId : list) {
			Role role = roleDao.findRoleById(roleId);
			user.getList().add(role);
		}
		return user;
	}
	/**
	 * 修改图片
	 * @param user
	 */
	public void updatePic(User user)throws SQLException {
		userDao.updatePic(user);
		
	}
	/**
	 * 修改密码
	 * @param user
	 * @throws SQLException
	 */
	public void updatePwd(User user)throws SQLException {
		user.setPwd(MD5Util.getMD5Str(user.getPwd()));
		userDao.updatePwd(user);
		
	}
	/**
	 * 修改状态
	 * @param id
	 * @param i
	 */
	public void updateStatus(Long id, int status) {
		userDao.updateStatus(id,status);
	}
	/**
	 * 重置密码
	 * @param id
	 */
	public void resetPwd(Long id, String string) {
		userDao.resetPwd(id,MD5Util.getMD5Str(string));
		
	}
	
	
	/**
	 * 用户注册
	 * @param user
	 */
	public void regist(User user) {
		user.setPwd(MD5Util.getMD5Str(user.getPwd()));
		userDao.addRegist(user);
	}
	
	

}
