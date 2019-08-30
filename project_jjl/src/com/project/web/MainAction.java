package com.project.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理页面加载
 */
@WebServlet("/main.action")
public class MainAction extends BaseServlet {
	private String mainUI(HttpServletRequest request,HttpServletResponse response){
		return "/WEB-INF/admin/page/main/main.jsp";
		
	}
	private String topUI(HttpServletRequest request,HttpServletResponse response){
		return "/WEB-INF/admin/page/main/top.jsp";
		
	}
	private String leftUI(HttpServletRequest request,HttpServletResponse response){
		return "/WEB-INF/admin/page/main/left.jsp";
		
	}
	private String rightUI(HttpServletRequest request,HttpServletResponse response){
		return "/WEB-INF/admin/page/main/right.jsp";
		
	}
	private String rightTopUI(HttpServletRequest request,HttpServletResponse response){
		return "/WEB-INF/admin/page/main/right_top.jsp";
		
	}
	
}
