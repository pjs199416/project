package com.project.web.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.po.User;
import com.project.service.UserService;

public class FrontUserFilter implements Filter{

	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		/*//2.获取cookie中的用户名和密码
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			//3.遍历出每一个cookie
			for (Cookie cookie : cookies) {
				if ("info".equals(cookie.getName())) {
					//4.拿到info,进行分割
					String info = URLDecoder.decode(cookie.getValue(), "utf-8");
					String[] infos = info.split("#");
					//获取用户名和密码
					String username = infos[0];
					String password = infos[1];
					//调用service层,进行登录校验
					UserService userService = new UserService();
					User user = userService.login(username, password);
				}
			}
		}*/
		
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("frontUser");
		if (obj != null) {
			chain.doFilter(req, resp);
		}else {
			//重定向到登录页面
			resp.sendRedirect(req.getContextPath()+"/loginAndregist.action?method=loginUI");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

	@Override
	public void destroy() {
		
		
	}
	
}
