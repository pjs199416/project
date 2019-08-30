package com.project.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.po.Role;
import com.project.po.User;
import com.project.service.RoleService;
import com.project.service.UserService;

/**
 * 用户信息Action层
 */
@WebServlet("/user.action")
public class UserAction extends BaseServlet {
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	
	/**
	 * 用户管理分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	private String list(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获得模糊查询条件
			String name = request.getParameter("name");
			if (name==null || name.equals("name")) {
				name = "%%";
			}else {
				name = "%"+name+"%";
			}
			//2.获取当前是第几页
			int currentPage = request.getParameter("currentPage")==null?1:Integer.parseInt(request.getParameter("currentPage"));
			//3.设置每页显示记录数
			int pageSize = 5;
			//4.查询记录数
			int totalSize = userService.findCount(name);
			//5.计算出总页数
			int totalPage = (int)Math.ceil(totalSize/(double)pageSize);
			
			//6.计算出数据第几条记录开始查询
			int begin = (currentPage-1)*pageSize;
			//7.分页查询
			List<User>list = userService.findByPage(begin, pageSize, name);
			//8.将数据存储到作用域中
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalSize", totalSize);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageSize", pageSize);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		//请求转发到查询页面
		return "/WEB-INF/admin/page/page/user/table.jsp";
		
	}
	
	/**
	 * 跳转到添加页面
	 * @param request
	 * @param response
	 */
	private String addUI(HttpServletRequest request,HttpServletResponse response){
		List<Role> list = roleService.findAll();
		request.setAttribute("list", list);
		return "/WEB-INF/admin/page/page/user/add.jsp";
		
	}
	
	/**
	 * 添加用户信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void add(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取提交参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String[] roleIds = request.getParameterValues("roleId");
		//2.把提交的数据封装到bean中
		User user = new User();
		user.setId(new Date().getTime());
		user.setName(name);
		user.setPwd(pwd);
		user.setPhone(phone);
		user.setStatus(1);
		//3.遍历roleId
		for (String str : roleIds) {
			Long roleId = Long.parseLong(str);
			Role role = roleService.findRoleById(roleId);
			user.getList().add(role);
		}
		//4.包对象保存到数据库
		userService.add(user);
		//5.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/user.action");
	}
	
	/**
	 * 跳转到修改页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String editUI(HttpServletRequest request,HttpServletResponse response){
		//获取提交参数id
		Long id = Long.parseLong(request.getParameter("id"));
		List<Role> list = roleService.findAll();
		User user = userService.findUserById(id);
		request.setAttribute("user", user);
		request.setAttribute("list", list);
		return "/WEB-INF/admin/page/page/user/modify.jsp";
		
	}
	
	private void edit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			//1.获取提交参数
			Long id = Long.parseLong(request.getParameter("id"));
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			String phone = request.getParameter("phone");
			String[] roleIds = request.getParameterValues("roleId");
			int status = Integer.parseInt(request.getParameter("status"));
			//2.把提交参数封装到对象中
			User user = new User();
			user.setId(id);
			user.setName(name);
			user.setPwd(pwd);
			user.setPhone(phone);
			user.setStatus(status);
			//3.遍历roleId
			for (String str : roleIds) {
				Long roleId = Long.parseLong(str);
				Role role = roleService.findRoleById(roleId);
				user.getList().add(role);
			}
			//4.把修改的对象保存到数据库中
			userService.update(user);
			//5.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/user.action");
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户启用
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void enable(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取提交参数
		Long id = Long.parseLong(request.getParameter("id"));
		userService.updateStatus(id,1);
		//2.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/user.action");
	}
	
	/**
	 * 用户禁用
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void disable(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取提交参数
		Long id = Long.parseLong(request.getParameter("id"));
		userService.updateStatus(id, 0);
		//2.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/user.action");
	}
	
	
	/**
	 * 重置密码
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void resetPwd(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取提交参数id
		Long id = Long.parseLong(request.getParameter("id"));
		userService.resetPwd(id,"123456");
		//2.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/user.action");
	}
	
	
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 */
	private String login(HttpServletRequest request,HttpServletResponse response){
		
		try {
			//1.获取提交参数
			String username = request.getParameter("name");
			String password = request.getParameter("pwd");
			//2.调用service层进行登录
			User user = userService.login(username,password);
			if (user!=null) {
				//登录成功,保存用户信息到会话
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);
				//给用户赋值数据库权限控制访问路径
				List<String> urls = (List<String>) request.getServletContext().getAttribute("urls");
				user.setUrls(urls);
				//重定向到管理页面
				response.sendRedirect(request.getContextPath()+"/main.action?method=mainUI");
			}else {
				//登录失败,给一个错误提示信息请求转发到登录页面
				request.setAttribute("msg", "用户名或密码错误");
				return "/adminlogin.jsp";
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/adminlogin.jsp");
	}

}
