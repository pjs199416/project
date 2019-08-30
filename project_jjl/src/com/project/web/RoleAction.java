package com.project.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.project.po.Privilege;
import com.project.po.Role;
import com.project.service.RoleService;

/**
 * 角色Action层
 */
@WebServlet("/role.action")
public class RoleAction extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private RoleService roleService = new RoleService();
	
	/**
	 * 查询所有角色表
	 * @param request
	 * @param response
	 * @return
	 */
	private String list(HttpServletRequest request,HttpServletResponse response){
		//从数据库中查询所有角色
		List<Role>list = roleService.findAll();
		//把list存储到作用域中
		request.setAttribute("list", list);
		//请求转发到角色页面
		return "/WEB-INF/admin/page/page/role/table.jsp";
	}
	
	/**
	 * 跳转到权限页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String privliegeUI(HttpServletRequest request,HttpServletResponse response){
		//1.获取提交参数id
		int id = Integer.parseInt(request.getParameter("id"));
		//2.将id存储到作用域中
		request.setAttribute("id", id);
		return "/WEB-INF/admin/page/page/role/privilege.jsp";
	}
	
	/**
	 * 查看权限
	 * @param request
	 * @param response
	 */
	private void privilege(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取角色id
			int id = Integer.parseInt(request.getParameter("id"));
			//2.查看所有的权限,与角色权限,根据角色id
			List<Privilege> list  = roleService.findAllPrivilegeAndRolePrivilegeByRoleId(id);
			//3.把数据转成json格式
			Gson gson = new Gson();
			String json = gson.toJson(list);
			System.out.println(json);
			//4.告诉浏览器响应 的是json数据
			response.setContentType("application/json;charset=utf-8");
			//5.将json写入
			response.getWriter().print(json);
		} catch (NumberFormatException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存权限
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void privilegeSave(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取角色id
		long roleId = Long.parseLong(request.getParameter("roleId"));
		//2.获取权限id
		String privilegeIds = request.getParameter("privilegeIds");
		if (privilegeIds==null || privilegeIds.equals("")) {
			//清除所有权限
			roleService.clearPrivilege(roleId);
		}else {
			
			//3.更新权限
			roleService.updatePrivilege(roleId,privilegeIds);
		}
		//4.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/role.action");
	}
	
	/**
	 * 跳转到添加角色页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String addUI(HttpServletRequest request,HttpServletResponse response){
		
		return "/WEB-INF/admin/page/page/role/add.jsp";
		
	}
	
	/**
	 * 添加角色
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void add(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取提交参数
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		//2.将提交的参数封装到bean中
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);
		//3.将添加的数据保存到数据库
		roleService.save(role);
		//4.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/role.action");
	}
	
	/**
	 * 跳转到修改角色页面,并显示角色内容
	 * @param request
	 * @param response
	 * @return
	 */
	private String editUI(HttpServletRequest request,HttpServletResponse response){
		//1.获取提交参数id
		Long id = Long.parseLong(request.getParameter("id"));
		Role role = roleService.findRoleById(id);
		request.setAttribute("role", role);
		//请求转发到修改页面
		return "/WEB-INF/admin/page/page/role/modify.jsp";
		
	}
	
	/**
	 * 修改角色
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void edit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			//1.获取提所有交参数
			Map<String, String[]> map = request.getParameterMap();
			Role role = new Role();
			BeanUtils.populate(role, map);
			roleService.updateRole(role);
			//重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/role.action");
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除角色
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取提交参数id
		int id = Integer.parseInt(request.getParameter("id"));
		roleService.delete(id);
		//重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/role.action");
	}
}
