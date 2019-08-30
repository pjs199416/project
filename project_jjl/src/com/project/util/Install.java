package com.project.util;

import com.project.dao.PrivilegeDao;
import com.project.dao.UserDao;
import com.project.po.Privilege;

public class Install {

	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		PrivilegeDao privilegeDao = new PrivilegeDao();
		//1.初始化超级管理员权限  
	/*	User user = new User();
		user.setName("admin");
		user.setPwd(MD5Util.getMD5Str("admin"));  
		userDao.add(user);*/
		//2.初始化权限数据 
		//顶级权限数据安装 
		/*Privilege menu1 = new Privilege("系统管理", null, null);
		Privilege menu2 = new Privilege("数据字典", null, null);
		Privilege menu3 = new Privilege("个人信息", null, null);
		Privilege menu4 = new Privilege("内容管理模块", null, null);
		Privilege menu5 = new Privilege("商品管理模块", null, null);
		Privilege menu6 = new Privilege("订单管理", null, null);
		privilegeDao.add(menu1);
		privilegeDao.add(menu2);
		privilegeDao.add(menu3);
		privilegeDao.add(menu4);
		privilegeDao.add(menu5);
		privilegeDao.add(menu6);*/
		//二级权限安装 
		/*Privilege menu11 = new Privilege("用户管理", "user.action", 1L);
		Privilege menu12 = new Privilege("角色管理", "role.action", 1L);
		Privilege menu21 = new Privilege("数据字典管理",null, 2L);
	
		Privilege menu31 = new Privilege("查看个人信息", "person.action?method=showUI", 3L);
		Privilege menu32 = new Privilege("修改个人信息", "person.action?method=editUI", 3L);
		Privilege menu41 = new Privilege("内容分类管理", "contentCategory.action", 4L);
		Privilege menu42 = new Privilege("内容管理", "content.action", 4L);
		Privilege menu51 = new Privilege("商品分类管理", "itemCategory.action", 5L);
		Privilege menu52 = new Privilege("商品管理", "item.action", 5L);
		Privilege menu61 = new Privilege("订单查询", null, 6L);
		privilegeDao.add(menu11);
		privilegeDao.add(menu12);
		privilegeDao.add(menu21);
		privilegeDao.add(menu31);
		privilegeDao.add(menu32);
		privilegeDao.add(menu41);
		privilegeDao.add(menu42);
		privilegeDao.add(menu51);
		privilegeDao.add(menu52);
		privilegeDao.add(menu61);*/
		//三级权限 
		/*Privilege menu111 = new Privilege("添加用户", "user.action?method=add", 7L);
		Privilege menu112 = new Privilege("启用用户", "user.action?method=enable", 7L);
		Privilege menu113 = new Privilege("禁用用户", "user.action?method=disable", 7L);
		Privilege menu114 = new Privilege("修改用户", "user.action?method=edit", 7L);
		Privilege menu115 = new Privilege("重置密码", "user.action?method=resetPwd", 7L); 
		
		Privilege menu121 = new Privilege("添加角色", "role.action?method=add", 8L);
		Privilege menu122 = new Privilege("删除角色", "role.action?method=delete", 8L);
		Privilege menu123 = new Privilege("修改角色", "role.action?method=edit", 8L);
		Privilege menu124 = new Privilege("权限管理", "role.action?method=privilege", 8L);
		
		Privilege menu411 = new Privilege("添加内容模块", "contentCategory.action?method=add", 12L);
		Privilege menu412 = new Privilege("修改内容模块", "contentCategory.action?method=edit", 12L);
		Privilege menu413 = new Privilege("删除内容模块", "contentCategory.action?method=delete", 12L);
		Privilege menu414 = new Privilege("启用内容模块", "contentCategory.action?method=enable", 12L);
		Privilege menu415 = new Privilege("禁用内容模块", "contentCategory.action?method=disable", 12L);
		
		Privilege menu421 = new Privilege("添加内容", "content.action?method=add", 13L);
		Privilege menu422 = new Privilege("修改内容", "content.action?method=edit", 13L);
		Privilege menu424 = new Privilege("启用内容", "content.action?method=enable", 13L);
		Privilege menu425 = new Privilege("禁用内容", "content.action?method=disable", 13L); 
		
		Privilege menu511 = new Privilege("添加商品分类", "itemCategory.action?method=add", 14L);
		Privilege menu512 = new Privilege("修改商品分类", "itemCategory.action?method=edit", 14L);
		Privilege menu513 = new Privilege("上移商品分类", "itemCategory.action?method=moveUp", 14L);
		Privilege menu514 = new Privilege("下移商品分类", "itemCategory.action?method=moveDown", 14L);
		Privilege menu515 = new Privilege("删除商品分类", "itemCategory.action?method=delete", 14L);
		Privilege menu516 = new Privilege("启用商品分类", "itemCategory.action?method=enable", 14L);
		Privilege menu517 = new Privilege("禁用商品分类", "itemCategory.action?method=disable", 14L); 
		
		Privilege menu521 = new Privilege("添加商品", "item.action?method=add", 15L);
		Privilege menu522 = new Privilege("修改商品", "item.action?method=edit", 15L);
		Privilege menu523 = new Privilege("启用商品", "item.action?method=enable", 15L);
		Privilege menu524 = new Privilege("禁用商品", "item.action?method=disable", 15L);
		privilegeDao.add(menu111);
		privilegeDao.add(menu112);
		privilegeDao.add(menu113);
		privilegeDao.add(menu114);
		privilegeDao.add(menu115);
		privilegeDao.add(menu121);
		privilegeDao.add(menu122);
		privilegeDao.add(menu123);
		privilegeDao.add(menu124);
		privilegeDao.add(menu411);
		privilegeDao.add(menu412);
		privilegeDao.add(menu413);
		privilegeDao.add(menu414);
		privilegeDao.add(menu415);
		privilegeDao.add(menu421);
		privilegeDao.add(menu422);
		privilegeDao.add(menu424);
		privilegeDao.add(menu425);
		privilegeDao.add(menu511); 
		privilegeDao.add(menu512);
		privilegeDao.add(menu513);
		privilegeDao.add(menu514);
		privilegeDao.add(menu515);
		privilegeDao.add(menu516);
		privilegeDao.add(menu517);
		privilegeDao.add(menu521);
		privilegeDao.add(menu522);
		privilegeDao.add(menu523);
		privilegeDao.add(menu524);*/
		
		/*Privilege menu901 = new Privilege("添加数据字典", "dataDic.action?method=add", 9L);
		Privilege menu902 = new Privilege("修改数据字典", "dataDic.action?method=edit", 9L);
		Privilege menu903 = new Privilege("启用数据字典", "dataDic.action?method=enable", 9L);
		Privilege menu904 = new Privilege("禁用数据字典", "dataDic.action?method=disable", 9L);
		privilegeDao.add(menu901);
		privilegeDao.add(menu902);
		privilegeDao.add(menu903);
		privilegeDao.add(menu904);*/
	}
}
