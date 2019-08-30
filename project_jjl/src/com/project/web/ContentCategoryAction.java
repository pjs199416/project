package com.project.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.project.po.ContentCategory;
import com.project.service.ContentCategoryService;


/**
 * 内容分类Action层
 * @author Administrator
 *
 */
@WebServlet("/contentCategory.action")
public class ContentCategoryAction extends BaseServlet {
	
	private ContentCategoryService service = new ContentCategoryService();
	
	/**
	 * 查询所有内容分类信息
	 * @param request
	 * @param response
	 */
	private String list(HttpServletRequest req,HttpServletResponse resp){
		try {
			//1.查询数据库所有内容分类数据
			List<ContentCategory> list = service.findAll();
			//2.把数据存到req对象的属性中
			req.setAttribute("list", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3.请求转发到页面
		return "/WEB-INF/admin/page/page/content_category/table.jsp";
	}
	
	/**
	 * 跳转到添加页面
	 * @param request
	 * @param response
	 */
	private String addUI(HttpServletRequest request,HttpServletResponse response){
		//请求转发到添加页面
		return "/WEB-INF/admin/page/page/content_category/add.jsp";
	}
	
	/**
	 * 添加内容分类信息
	 * @param request
	 * @param response
	 */
	private void add(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取所有提交参数
			Map<String, String[]> map = request.getParameterMap();
			//2.将所有参数封装到BeanUtils中
			ContentCategory contentCategory = new ContentCategory();
			BeanUtils.populate(contentCategory, map);
			//3.调用service层将数据保存到数据库
			service.save(contentCategory);
			//4.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/contentCategory.action");
		} catch (IllegalAccessException | InvocationTargetException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id显示修改页面的内容分类信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private String editUI(HttpServletRequest req,HttpServletResponse resp){
		try {
			//1.获得提交参数id
			int id = Integer.parseInt(req.getParameter("id"));
			//2.根据id查找内容分类信息
			ContentCategory contentCategory = service.findContentCategoryById(id);
			//3.将商品分类信息请求转发到修改页面
			req.setAttribute("contentCategory", contentCategory);
		} catch (NumberFormatException | SQLException e) { 
			e.printStackTrace();
		}
		return "/WEB-INF/admin/page/page/content_category/modify.jsp";
	}
	
	/**
	 * 修改内容分类信息
	 * @param request
	 * @param response
	 */
	private void edit(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取所有提交参数
			Map<String, String[]> map = request.getParameterMap();
			//2.把所有提交的数据存储到bean中
			ContentCategory category = new ContentCategory();
			BeanUtils.populate(category, map);
			//3.调用service层将修改的数据保存到数据库中
			service.update(category);
			//4.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/contentCategory.action");
		} catch (IllegalAccessException | InvocationTargetException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除内容分类信息
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request,HttpServletResponse response){
		try {
			//获取参数id
			int id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
			//重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/contentCategory.action");
		} catch (NumberFormatException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 启用内容分类信息
	 * @param request
	 * @param response
	 */
	private void enable(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取提交参数id
			int id = Integer.parseInt(request.getParameter("id"));
			service.enable(id);
			//2.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/contentCategory.action");
		} catch (NumberFormatException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 禁用内容分类信息
	 * @param request
	 * @param response
	 */
	private void disable(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取提交参数id
			int id = Integer.parseInt(request.getParameter("id"));
			service.disable(id);
			//2.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/contentCategory.action");
		} catch (NumberFormatException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}

}
