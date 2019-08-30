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
import com.project.po.Content;
import com.project.po.ContentCategory;
import com.project.service.ContentCategoryService;
import com.project.service.ContentService;
import com.project.util.MyDateConverter;



@WebServlet("/content.action")
public class ContentAction extends BaseServlet{
	private ContentService contentService = new ContentService();
	private ContentCategoryService categoryService = new ContentCategoryService();
	
	/**
	 * 查询所有内容数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private String list(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		try {
			//1.查询数据库所有内容分类数据
			List<ContentCategory> list = categoryService.findAll();
			
			//2.获得提交的内容分类id值  如果没有获取到取集合中第0个分类对象id值
			Long contentCategoryId = null;
			String params =request.getParameter("contentCategoryId");
			if(params==null || params.equals("")){
				ContentCategory contentCategory = list.get(0);
				if(contentCategory!=null){
					contentCategoryId = contentCategory.getId();
				}
			} else {
				contentCategoryId = Long.parseLong(params);
			}
			//3.根据选中的id值 把 内容分类对象的selected属性改为true 其他的改为false.
			for(ContentCategory contentCategory : list){
				if(contentCategory.getId()==contentCategoryId){
					contentCategory.setSelected(true);
				}else {
					contentCategory.setSelected(false);
				}
			}
			//4.根据指定内容分类id查找内容管理数据 
			List<Content> list2 = contentService.findListByCategoryId(contentCategoryId);
			request.setAttribute("contentCategoryId", contentCategoryId);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3.请求转发到页面
		return "/WEB-INF/admin/page/page/content/table.jsp";
		
	}
	 
	/**
	 * 转发到添加页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private String addUI(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		try {
			//1.获得选中的分类id
			int contentCategoryId = Integer.parseInt(request.getParameter("contentCategoryId"));
			//2.找到内容分类对象
			ContentCategory contentCategory = null;
			
				contentCategory = categoryService.findContentCategoryById(contentCategoryId);
			
			request.setAttribute("contentCategory", contentCategory);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return "/WEB-INF/admin/page/page/content/add.jsp";
		
	}
	
	
	/**
	 * 保存添加页面数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
			//1.获得页面所有数据 
			Map<String,String[]> map = request.getParameterMap();
			Content content = new Content();
			//2.把map集合数据内省到bean中
			try {
				ConvertUtils.register(new MyDateConverter(), Date.class);
				BeanUtils.populate(content, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
			} finally {
				//3.获得内容分类id 并设置到content对象中
				Long contentCategoryId = Long.parseLong(request.getParameter("contentCategoryId"));
				ContentCategory contentCategory = new ContentCategory();
				contentCategory.setId(contentCategoryId);
				content.setContentCategory(contentCategory);
				//4.保存到数据库
				contentService.add(content);
				//5.重定向
				response.sendRedirect(request.getContextPath()+"/content.action?contentCategoryId="+contentCategoryId);
			}
			
		
	}

	 
	/**
	 * 跳转到修改页面并且显示当前id的内容分类信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private String editUI(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		//1.获得提交参数id
		int id = Integer.parseInt(request.getParameter("id"));
		//2.根据id查找内容分类信息
		Content content = contentService.findContentById(id);
		//3.将商品分类信息请求转发到修改页面
		request.setAttribute("content", content);
		return "/WEB-INF/admin/page/page/content/modify.jsp";
		
	}
	
	
	/**
	 * 保存修改数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void edit(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		try {
			//1.获得页面所有数据 
			Map<String,String[]> map = request.getParameterMap();
			Content content = new Content();
			//2.把map集合数据内省到bean中
			ConvertUtils.register(new MyDateConverter(), Date.class);
			BeanUtils.populate(content, map);
			//3.保存到数据库
			contentService.edit(content);
			int contentCategoryId = Integer.parseInt(request.getParameter("contentCategoryId"));
			//4.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/content.action?contentCategoryId="+contentCategoryId);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 启用
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void enable(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		//1.获得提交参数id
		int id = Integer.parseInt(request.getParameter("id"));
		//2.启用该内容分类
		contentService.enable(id);
		int contentCategoryId = Integer.parseInt(request.getParameter("contentCategoryId"));
		//3.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/content.action?contentCategoryId="+contentCategoryId);
	}
	
	/**
	 * 禁用
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void disable(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		//1.获得提交参数id
		int id = Integer.parseInt(request.getParameter("id"));
		//2.禁用该内容分类
		contentService.disable(id);
		int contentCategoryId = Integer.parseInt(request.getParameter("contentCategoryId"));
		//3.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/content.action?contentCategoryId="+contentCategoryId);
	}
	
	/**
	 * 根据模块名字获取内容列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void showContent(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取内容模块的名字
		String name = request.getParameter("name");
		List<Content>list = contentService.getListContent(name);
		//2.把数据转成json格式
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
}
