package com.project.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.google.gson.Gson;
import com.project.po.ItemCategory;
import com.project.service.CategoryService;
import com.project.util.MyDateConverter;


@WebServlet("/category.action")
public class ItemCategoryAction extends BaseServlet {

	private CategoryService service = new CategoryService();
	
	/**
	 * 查询所有商品信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private String list(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		try {
			//1.查询数据库中的所有商品
			List<ItemCategory>list = service.findAll();
			//2.将list存储到域对象中
			request.setAttribute("list", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3.请求转发到table.jsp页面
		return "/WEB-INF/admin/page/page/item_category/table.jsp";
		
	}
	
	/**
	 * 跳转到添加页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String addUI(HttpServletRequest request,HttpServletResponse response){
		//请求转发到添加页面
		return "/WEB-INF/admin/page/page/item_category/add.jsp";
	}
	
	/**
	 * 添加商品分类信息
	 * @param request
	 * @param response
	 */
	private void add(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取所有请求数据
			Map<String, String[]> map = request.getParameterMap();
			//2.注册日期格式化
			ConvertUtils.register(new MyDateConverter(), Date.class);
			//3.将所有提交数据存储到bean中
			ItemCategory category = new ItemCategory();
			BeanUtils.populate(category, map);
			//4.调用service层添加商品保存到数据库
			service.save(category);
			//5.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/category.action");
		} catch (IllegalAccessException | InvocationTargetException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id显示修改页面的商品分类信息
	 * @param request
	 * @param response
	 * @return
	 */
	private String editUI(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取请求参数id
			int id = Integer.parseInt(request.getParameter("id"));
			ItemCategory category = service.findCategoryById(id);
			//2.将category存储到域对象中
			request.setAttribute("category", category);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		//3.请求转发到修改页面
		return "/WEB-INF/admin/page/page/item_category/modify.jsp";
	}
	
	/**
	 * 修改商品分类信息
	 * @param request
	 * @param response
	 */
	private void edit(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取所有请求参数
			Map<String, String[]> map = request.getParameterMap();
			//2.注册日期格式化
			ConvertUtils.register(new MyDateConverter(), Date.class);
			//3.将所有请求参数存储到bean对象中
			ItemCategory category = new ItemCategory();
			BeanUtils.populate(category, map);
			//4.调用service层去修改数据
			service.update(category);
			//重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/category.action");
		} catch (IllegalAccessException | InvocationTargetException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除商品分类信息
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取请求参数id
			int id = Integer.parseInt(request.getParameter("id"));
			//2.调用service层删除商品信息
			service.delete(id);
			//3.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/category.action");
		} catch (NumberFormatException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向上移动商品分类信息
	 * @param request
	 * @param response
	 */
	private void moveUp(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取请求参数id
			int id = Integer.parseInt(request.getParameter("id"));
			//2.调用service层上移商品信息
			service.moveUp(id);
			//3.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/category.action");
		} catch (NumberFormatException  | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向下移动商品分类信息
	 * @param request
	 * @param response
	 */
	private void moveDown(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取请求参数id
			int id = Integer.parseInt(request.getParameter("id"));
			//2.调用service层下移商品信息
			service.moveDown(id);
			//3.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/category.action");
		} catch (NumberFormatException  | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 启用商品分类信息
	 * @param request
	 * @param response
	 */
	private void enable(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取请求参数id
			int id = Integer.parseInt(request.getParameter("id"));
			//2.调用service层启用商品信息
			service.enable(id);
			//3.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/category.action");
		} catch (NumberFormatException  | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 禁用商品分类信息
	 * @param request
	 * @param response
	 */
	private void disable(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取请求参数id
			int id = Integer.parseInt(request.getParameter("id"));
			//2.调用service层禁用商品信息
			service.disable(id);
			//3.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/category.action");
		} catch (NumberFormatException  | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 前台显示商品分类列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void showItemCategoryList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<ItemCategory>list = service.findAllList();
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
}
