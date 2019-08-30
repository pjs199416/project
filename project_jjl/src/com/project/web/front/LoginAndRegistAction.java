package com.project.web.front;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.po.Cart;
import com.project.po.CartItem;
import com.project.po.User;
import com.project.service.CartService;
import com.project.service.UserService;
import com.project.web.BaseServlet;

/**
 * 前台登录与注册
 */
@WebServlet("/loginAndregist.action")
public class LoginAndRegistAction extends BaseServlet {
	private UserService userService = new UserService();
	private CartService cartService = new CartService();
	
	/**
	 * 跳转到用户注册页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String registUI(HttpServletRequest request,HttpServletResponse response){
		//1.获取提交参数
		return "/WEB-INF/front/page/reg.jsp";
		
	}
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void showregist(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取提交参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		//2.把提交参数封装到bean对象中
		User user = new User();
		user.setId(new Date().getTime());
		user.setName(name);
		user.setPwd(pwd);
		user.setPhone(phone);
		user.setStatus(1);
		//调用service层将数据保存到数据库
		userService.regist(user);
		response.sendRedirect(request.getContextPath()+"/loginAndregist.action?method=loginUI");
		
	}
	
	/**
	 * 跳转到用户登录页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private String loginUI(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "/WEB-INF/front/page/login.jsp";
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showlogin(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
			//获取用户名或密码
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			//1.1获取记住用户名name
			String rename = request.getParameter("rename");
			//获取验证码
			String vcode = request.getParameter("vcode");
			//获取自动登录名字
			String auto = request.getParameter("auto");
			//1.2把用户名保存到cookie中
			Cookie cookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
			Cookie cookie2 = new Cookie("pwd", pwd);
			//1.3判断是否记住用户名
			if ("on".equals(rename)) {
				//记住,设置有效时间
				cookie.setMaxAge(7*24*60*60);
				cookie2.setMaxAge(7*24*60*60);
			}else {
				//不记住
				cookie.setMaxAge(0);
				cookie2.setMaxAge(0);
			}
			//1.4设置cookie携带的路径
			cookie.setPath(request.getContextPath());
			cookie2.setPath(request.getContextPath());
			//1.5添加cookie
			response.addCookie(cookie);
			response.addCookie(cookie2);
			
			//验证码校验,把客户端传过来的验证码和服务器传过来的验证码进行校验
			HttpSession session = request.getSession();
			String code = (String) session.getAttribute("code");
			//判断验证码知否正确
			if (vcode.equalsIgnoreCase(code)) {
				//验证码正确
				User user = userService.login(name, pwd);
				if (user != null) {
					//登录成功
					session.setAttribute("frontUser", user);
					
					
					//判断是否需要自动登录
					Cookie cookie3 = new Cookie("info", URLEncoder.encode(name, "UTF-8")+"#"+pwd);
					cookie3.setPath(request.getContextPath());
					if ("au".equals(auto)) {
						//需要自动登录,设置有效时间
						cookie3.setMaxAge(7*24*60*60);
						
					}else {
						//不需要自动登录
						cookie3.setMaxAge(0);
					}
					//添加到cookie中
					response.addCookie(cookie3);
					
					
					
					//1.从数据库中取出购物车的数据
					Cart dataCart = cartService.findCart(user.getId());
					Cart sessionCart = (Cart) session.getAttribute("cart");
					//2.合并dataCart和sessionCart为一个newCart
					Cart newCart = null;
					if (dataCart !=null && sessionCart == null) {
						newCart = dataCart;
					}
					if (sessionCart != null && dataCart == null) {
						newCart = sessionCart;
					}
					if (dataCart != null && sessionCart != null) {
						newCart = sessionCart;
						//1.合并会话和数据库都有的购物车数据 
						//2.让合并的购物车拥有当前会话数据
						Map<Long, CartItem> map = newCart.getMap();
						//3.获得数据库的当前购物车数据
						Map<Long, CartItem> dataMap = dataCart.getMap();
						//4.获得数据库购物项id
						Set<Long> itemIds = dataMap.keySet();
						//5.遍历数据库购物项id
						for (Long itemId : itemIds) {
							//6.如果合并后的newCar没有这个id，就把数据库的购物项合并到newCar中
							boolean hasKey = map.containsKey(itemId);
							if (!hasKey) {
								CartItem cartItem = dataMap.get(itemId);
								map.put(itemId, cartItem);
							}
						}
					}
					
					newCart.setUser_id(user.getId());
					//7.把newCar 保存到session 保存到数据库
					cartService.addCart(newCart);
					session.setAttribute("cart", newCart);
					
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}else {
					//登录失败,提示错误信息
					request.setAttribute("msg", "用户名或密码错误");
					//请求转发到login.jsp
					request.getRequestDispatcher("/WEB-INF/front/page/login.jsp").forward(request, response);
				}
			}else {
				//验证码错误,显示错误信息
				request.setAttribute("msg", "验证码错误");
				//跳转到登录页面
				request.getRequestDispatcher("/WEB-INF/front/page/login.jsp").forward(request, response);
			}
			
		
	}
	
	/**
	 * 安全退出
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void showExit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
}
