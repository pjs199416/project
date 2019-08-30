package com.project.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.project.po.Addr;
import com.project.po.User;
import com.project.service.AddrService;

/**
 * Servlet implementation class AddrAction
 */
@WebServlet("/afterLogin/addr.action")
public class AddrAction extends BaseServlet {
	private AddrService addrService = new AddrService();

	/**
	 * 获取所有的省
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getProvinceAll(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取所有地址
		List<Object[]> list = addrService.findProvinceAll();
		//2.将地址转为json格式
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
	
	/**
	 * 获取所有市
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getCityAll(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Long provinceId = Long.parseLong(request.getParameter("provinceId"));
		List<Object[]>list = addrService.findCityAll(provinceId);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
	
	/**
	 * 获取所有县区
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getDistrict(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Long cityId = Long.parseLong(request.getParameter("cityId"));
		List<Object[]>list = addrService.findDistrictAll(cityId);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
	
	/**
	 * 将添加的数据保存到数据库中
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getSaveAddr(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			//1.获取提交参数保存到数据库
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String district = request.getParameter("district");
			String description = request.getParameter("description");
			String shName = request.getParameter("shName");
			String shPhone = request.getParameter("shPhone");
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("frontUser");
			Addr addr = new Addr();
			addr.setProvince(province);
			addr.setCity(city);
			addr.setDistrict(district);
			addr.setDescription(description);
			addr.setShName(shName);
			addr.setShPhone(shPhone);
			addr.setUser(user);
			//addr.setStatus(1);
			//2.把所地址保存到数据库
			addrService.saveAddr(addr);
		} catch (Exception e) {
			response.getWriter().print("0");
			e.printStackTrace();
		}
		response.getWriter().print("1");
	}
	
	/**
	 * 获取所有地址信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAddrAll(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获得用户地址信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("frontUser");
		Long user_id = user.getId();
		//2.根据用户id查询所有地址
		List<Addr> list = addrService.findAddrAll(user_id);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
	
	/**
	 * 删除地址信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteAddr(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		addrService.deleteAddr(id);
		response.getWriter().print(id);
	}
	
	/**
	 * 跳转到地址页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String addrUI(HttpServletRequest request,HttpServletResponse response){
		return "/WEB-INF/front/afterLogin/trade.jsp";
		
	}
	
	/**
	 * 跳转到地址修改页面
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private String updateAddrUI(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		Long id = Long.parseLong(request.getParameter("id"));
		
		Addr addr = addrService.findAddrById(id);
		request.setAttribute("addr", addr);
		//请求转发到修改页面
		return "/WEB-INF/front/afterLogin/updateAddr.jsp";
	}
	
	/**
	 * 编辑地址信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void editAddr(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//获取提交参数
		Long id = Long.parseLong(request.getParameter("id"));
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String description = request.getParameter("description");
		String shName = request.getParameter("shName");
		String shPhone = request.getParameter("shPhone");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("frontUser");
		Addr addr = new Addr();
		addr.setId(id);
		addr.setProvince(province);
		addr.setCity(city);
		addr.setDistrict(district);
		addr.setDescription(description);
		addr.setShName(shName);
		addr.setShPhone(shPhone);
		addr.setUser(user);
		addrService.updateAddr(addr);
		
		//重定向到修改页面
		response.sendRedirect(request.getContextPath()+"/afterLogin/addr.action?method=addrUI");
	}
	
	/**
	 * 默认地址的设置
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void defaultAddr(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Long id = Long.parseLong(request.getParameter("id"));
		Addr addr = addrService.setDefaultAddr(id);
		//response.sendRedirect(request.getContextPath()+"/afterLogin/addr.action?method=addrUI");
		response.getWriter().print(id);
	}
}
