package com.project.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.po.Privilege;
import com.project.po.User;


/**
 * 权限过滤器
 * @author Administrator
 *
 */
public class PrivilegeFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse reqs = (HttpServletResponse) response;
		//解决中文乱码
		req.setCharacterEncoding("utf-8");
		//1.统一资源标识符
		String uri = req.getRequestURI();
		//2.过滤路径
		String path = uri.replace(req.getContextPath()+"/", "");
		//3.获取方法
		String method = req.getParameter("method");
		if (method != null && !method.equals("")) {
			//获取路径
			path = path+"?method"+method;
		}
		//4.获取数据控制权限的路径
		ServletContext context = req.getServletContext();
		List<String>urls = (List<String>) context.getAttribute("urls");
		//5.path访问路径受权限控制
		if (urls.contains(path)) {
			//7.如果受权限控制,就判断用户是否登录,如果没有登录,就跳转到登录页面
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("loginUser");
			if (obj==null) {
				//没有登录
				reqs.sendRedirect(req.getContextPath()+"/adminlogin.jsp");
			}else {
				//已经登录,看该用户权限是否拥有,如果拥有就放行,没有就跳转到权限不足页面
				User user = (User) obj;
				System.out.println("user===="+user);
				if (user.hasPrivilegeByUrl(path)) {
					System.out.println();
					//拥有权限,放行
					chain.doFilter(req, reqs);
				}else {
					//跳转到权限不足页面
					reqs.sendRedirect(req.getContextPath()+"/noPrivilege.jsp");
				}
				
			}
		}else {
			//不受权限控制的页面.放行
			chain.doFilter(req, reqs);
			
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	public void destroy() {
		
	}

	

}
