package com.project.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.po.DataDicType;
import com.project.po.DataItem;
import com.project.service.DataDicService;

/**
 *数据字典action层
 */
@WebServlet("/dataDic.action")
public class DataDicAction extends BaseServlet {
	
	private DataDicService dataDicService = new DataDicService();
	
	/**
	 * 查询所有数据字典信息
	 * @param request
	 * @param response
	 * @return
	 */
	private String list(HttpServletRequest request,HttpServletResponse response){
		//1.从数据库中获取所有的数据字典类别信息
		List<DataDicType>list = dataDicService.findAll();
		//2.获取指定的数据字典id类别信息列表
		Long dataDicTypeId = request.getParameter("dataDicTypeId")==null?list.get(0).getId()
				:Long.parseLong(request.getParameter("dataDicTypeId"));
		//3.根据数据字典类别id查询数据类别字典信息
		DataDicType dataDicType = dataDicService.findDataDicTypeById(dataDicTypeId);
		//4.根据数据字典类别id查询数据字典迭代描述信息
		List<DataItem>list2 = dataDicService.findDataItemListByDataTypeId(dataDicTypeId);
		//5.把数据存储到域对象中
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("dataDicTypeId", dataDicTypeId);
		request.setAttribute("dataDicType", dataDicType);
		//请求转发到查询页面
		return "/WEB-INF/admin/page/page/data_dictionary/table.jsp";
		
	}
	
	/**
	 * 跳转至添加页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String addUI(HttpServletRequest request,HttpServletResponse response){
		//1.获取请求参数dataDicTypeId
		Long dataDicTypeId = Long.parseLong(request.getParameter("dataDicTypeId"));
		//2.根据dataDicTypeId查询数据字典类别信息
		DataDicType dataDicType = dataDicService.findDataDicTypeById(dataDicTypeId);
		//3.把数据存储到作用域中
		request.setAttribute("dataDicType", dataDicType);
		//4.请求转发到添加页面
		return "/WEB-INF/admin/page/page/data_dictionary/add.jsp";
		
	}
	
	/**
	 * 添加数据字典类别迭代项
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void add(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取提交参数
		Long dataDicTypeId = Long.parseLong(request.getParameter("dataDicTypeId"));
		String itemName = request.getParameter("item_name");
		int status = Integer.parseInt(request.getParameter("status"));
		//2.把提交的数据存储对象中
		DataItem dataItem = new DataItem();
		dataItem.setItem_name(itemName);
		dataItem.setStatus(status);
		//3.将数据添加到数据库
		dataDicService.addDataItem(dataItem,dataDicTypeId);
		//4.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/dataDic.action?method=list&dataDicTypeId="+dataDicTypeId);
	}
	
	/**
	 * 跳转到修改页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String editUI(HttpServletRequest request,HttpServletResponse response){
		//1.获取请求参数dataDicTypeId
		Long dataDicTypeId = Long.parseLong(request.getParameter("dataDicTypeId"));
		//2.根据dataDicTypeId查询数据字典类别信息
		DataDicType dataDicType = dataDicService.findDataDicTypeById(dataDicTypeId);
		//3.获取数据字典迭代项id
		Long id = Long.parseLong(request.getParameter("id"));
		DataItem dataItem = dataDicService.findDataItemById(id);
		//4.把数据存储到作用域中
		request.setAttribute("dataDicType", dataDicType);
		request.setAttribute("dataItem", dataItem);
		return "/WEB-INF/admin/page/page/data_dictionary/modify.jsp";
		
	}
	
	/**
	 * 修改数据字典信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void edit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取请求参数dataDicTypeId
		Long dataDicTypeId = Long.parseLong(request.getParameter("dataDicTypeId"));
		//2.获取请求参数数据字典迭代项id
		Long id = Long.parseLong(request.getParameter("id"));
		String itemName = request.getParameter("item_name");
		int status = Integer.parseInt(request.getParameter("status"));
		//3.创建迭代项对象,把提交的参数封装到对象中
		DataItem dataItem = new DataItem();
		dataItem.setId(id);
		dataItem.setItem_name(itemName);
		dataItem.setStatus(status);
		//4.调用service,包修改的数据保存到数据库中
		dataDicService.updateDataItem(dataItem);
		//5.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/dataDic.action?method=list&dataDicTypeId="+dataDicTypeId);
	}
	
	/**
	 * 数据字典信息启用
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void enable(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取提交参数
		Long dataDicTypeId = Long.parseLong(request.getParameter("dataDicTypeId"));
		Long id = Long.parseLong(request.getParameter("id"));
		DataItem dataItem = dataDicService.findDataItemById(id);
		dataItem.setStatus(1);
		dataDicService.updateDataItem(dataItem);
		//2.重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/dataDic.action?method=list&dataDicTypeId="+dataDicTypeId);
		
	}
	/**
	 * 数据字典信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void disable(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取提交参数dataDicTypeId
		Long dataDicTypeId = Long.parseLong(request.getParameter("dataDicTypeId"));
		Long id = Long.parseLong(request.getParameter("id"));
		DataItem dataItem = dataDicService.findDataItemById(id);
		dataItem.setStatus(0);
		dataDicService.updateDataItem(dataItem);
		//重定向
		response.sendRedirect(request.getContextPath()+"/dataDic.action?method=list&dataDicTypeId="+dataDicTypeId);
	}
	
	/**
	 * 显示前台数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void showDataItem(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1.获取请求参数
		String typecode = request.getParameter("typecode");
		//2.根据数据字典代码号查询数据字典迭代项
		List<DataItem>list = dataDicService.findDataDicTypeBytypecode(typecode);
		//3.把数据转换成json格式
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
}
