package com.project.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class BaseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//解决中文乱码
			//request.setCharacterEncoding("utf-8");
			//1.获取要走的是什么方法
			String methodName = request.getParameter("method");
			if (methodName==null) {
				methodName = "list";
			}
			//2.获取方法名对应的那个方法,用this来调用,参数1:方法名,参数2:参数类型
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//暴力反射
			method.setAccessible(true);
			//3.反射调用,invoke就是子类的方法调用
			Object object = method.invoke(this, request,response);
			//如果子类的方法有返回值,就表示它想跳转
			if (object != null) {
				request.getRequestDispatcher((String)object).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
