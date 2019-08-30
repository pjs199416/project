package com.project.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.po.User;
import com.project.service.UserService;
import com.project.util.MD5Util;

/**
 * 个人信息管理Action层
 */
@WebServlet("/preson.action")
public class PresonAction extends BaseServlet {
	 private UserService userService = new UserService();
	 
	 /**
	  * 跳转到个人信息页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String showUI(HttpServletRequest request,HttpServletResponse response){
		return "/WEB-INF/admin/page/page/person/person_mes.jsp";
	 }
	
	/**
	 * 保存个人图片
	 * @param request
	 * @param response
	 */
	private void savePic(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取请求参数,pic
			String pic = request.getParameter("pic");
			//2.通过session获取 当前用户
			User user = (User) request.getSession().getAttribute("loginUser");
			user.setPic(pic);
			//3.将当前的用户保存到数据库中
			userService.updatePic(user);
			//4.重定向到查看个人信息
			response.sendRedirect(request.getContextPath()+"/preson.action?method=showUI");
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转到修改密码页面
	 * @param request
	 * @param response
	 */
	private String editUI(HttpServletRequest request,HttpServletResponse response){
		return "/WEB-INF/admin/page/page/person/update_password.jsp";
		
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private String edit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			//1.获得提交参数密码
			String newPwd = request.getParameter("newPwd");
			String oldPwd = request.getParameter("oldPwd");
			//2.获取域对象
			User user = (User) request.getSession().getAttribute("loginUser");
			//3.获取当前密码
			String currentPwd = user.getPwd();
			String md5StrPwd = MD5Util.getMD5Str(oldPwd);
			//判断原密码是否正确
			if (currentPwd.equals(md5StrPwd)) {
				//原密码正确,修改新密码
				user.setPwd(newPwd);
				//将密码修改到数据库中
				userService.updatePwd(user);
				request.getSession().invalidate();
				response.getWriter().print(1);
				/*//重定向到登录页面
				response.sendRedirect(request.getContextPath()+"/adminlogin.jsp");*/
			}else {
				
				response.getWriter().print(0);
				
				/*//原密码错误,提示错误信息
				request.setAttribute("msg", "原密码输入有误");
				//请求转发到update_password.jsp页面
				return "/WEB-INF/admin/page/page/person/update_password.jsp";*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
