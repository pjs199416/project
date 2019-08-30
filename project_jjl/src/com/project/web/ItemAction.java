package com.project.web;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.project.po.Item;
import com.project.po.ItemCategory;
import com.project.service.CategoryService;
import com.project.service.DataDicService;
import com.project.service.ItemService;
import com.project.util.MyDateConverter;
import com.project.util.StepUtils;


/**
 * 商品信息Action层
 * @author Administrator
 *
 */
@WebServlet("/item.action")
public class ItemAction extends BaseServlet {

	private ItemService service = new ItemService();
	private CategoryService categoryService = new CategoryService();
	private DataDicService dataDicService = new DataDicService();
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
			//1.获取提交参数
			String title = request.getParameter("title");
			if (title ==null) {
				title = "%%";
			}else {
				title = "%"+title+"%";
			}
			//2.获取当前是第几页
			int currentPage = request.getParameter("currentPage")==null?1:Integer.parseInt(request.getParameter("currentPage"));
			//3.设置每页显示记录数
			int pageSize = 10;
			//4.查询记录数
			int totalSize = service.findCount(title);
			//5.计算出总页数
			int totalPage = (int)Math.ceil(totalSize/(double)pageSize);
			
			//6.计算出数据第几条记录开始查询
			int begin = (currentPage-1)*pageSize;
			//7.查询分页数据
			List<Item>list = service.findByPage(begin, pageSize, title);
			
			//8.将数据存储到作用域中
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalSize", totalSize);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageSize", pageSize);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		//9.请求转发到table.jsp页面
		return "/WEB-INF/admin/page/page/item/table.jsp";
		
	}
	 
	/**
	 * 跳转到添加页面
	 * @param request
	 * @param response
	 * @return
	 */
	private String addUI(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.查询所有商品分类数据
			List<ItemCategory>list = categoryService.findAll();
			//将商品分类存到作用域
			request.setAttribute("list", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//请求转发到添加页面
		return "/WEB-INF/admin/page/page/item/add.jsp";
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
			Item item = new Item();
			BeanUtils.populate(item, map);
			//4.获取商品分类id
			long categoryId = Long.parseLong(request.getParameter("category_id"));
			ItemCategory category = new ItemCategory();
			category.setId(categoryId);
			item.setItemCategory(category);
			//4.调用service层添加商品保存到数据库
			service.save(item);
			//5.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/item.action");
		} catch (IllegalAccessException | InvocationTargetException | IOException | SQLException e) {
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
			Long id = Long.parseLong(request.getParameter("id"));
			Item item = service.findItemById(id);
			//2.将item存储到域对象中
			request.setAttribute("item", item);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		//3.请求转发到修改页面
		return "/WEB-INF/admin/page/page/item/modify.jsp";
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
			Item item = new Item();
			BeanUtils.populate(item, map);
			//4.调用service层去修改数据
			service.update(item);
			//重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/item.action");
		} catch (IllegalAccessException | InvocationTargetException | SQLException | IOException e) {
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
			Long id = Long.parseLong(request.getParameter("id"));
			//2.调用service层启用商品信息
			service.enable(id);
			//3.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/item.action");
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
			Long id = Long.parseLong(request.getParameter("id"));
			//2.调用service层禁用商品信息
			service.disable(id);
			//3.重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/item.action");
		} catch (NumberFormatException  | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 前台显示商品详情信息
	 * @param request
	 * @param response
	 */
	private String showItemList(HttpServletRequest request,HttpServletResponse response){
		//1.定义全局参数sql语句
		String sql = "select * from item where 1=1";
		String sqlCount ="select count(*) from item where 1=1";
		//2.获取商品分类id
		Long itemCategoryId = Long.parseLong(request.getParameter("itemCategoryId"));
		System.out.println("itemCategoryId========"+itemCategoryId);
		if (itemCategoryId!=null && !"".equals(itemCategoryId)) {
			sql+=" and item_category_id="+itemCategoryId+" ";
			sqlCount+=" and item_category_id="+itemCategoryId+" ";
		}
		//3.获取价格区间
		String pricesection = request.getParameter("pricesection");
		if (pricesection != null && !"".equals(pricesection)) {
			double minPrice = Double.parseDouble(pricesection.substring(0, pricesection.indexOf("-")));
			///System.out.println("aaaaa===="+minPrice);
			double maxPrice = Double.parseDouble(pricesection.substring(pricesection.indexOf("-")+1));
			sql+=" and price between "+minPrice+" and "+maxPrice+" ";
			sqlCount+=" and price between "+minPrice+" and "+maxPrice+" ";
		}
		//4.获取商品重量区间
		String weightsection = request.getParameter("weightsection");
		if (weightsection != null && !"".equals(weightsection)) {
			double minWeight = Double.parseDouble(weightsection.substring(0, weightsection.indexOf("-")));
			double maxWeight = Double.parseDouble(weightsection.substring(weightsection.indexOf("-")+1));
			sql+=" and weight between "+minWeight+" and "+maxWeight+" ";
			sqlCount+=" and weight between "+minWeight+" and "+maxWeight+" ";
		}
		//5.模糊查询参数
		String title = request.getParameter("title");
		if (title != null && !"".equals(title)) {
			sql+=" and title like '%"+title+"%' ";
			sqlCount+=" and title like '%"+title+"%' ";
		}
		//6.其他条件
		sql+="and status=1  ";
		//7.获取当前页
		int currentPage = request.getParameter("currentPage")==null?1:Integer.parseInt(request.getParameter("currentPage"));
		//8.设置每页显示记录数
		int pageSize = 10;
		//9.查询记录数
		int totalSize = service.getfindCountBySql(sqlCount);
		//10.计算出总页数
		int totalPage = (int)Math.ceil(totalSize/(double)pageSize);
		if (totalPage<1) {
			totalPage=1;
		}
		if (currentPage>totalPage) {
			currentPage = totalPage;
		}
		if (currentPage<1) {
			currentPage = 1;
		}
		//11.获取limit,查询条件
		int begin = (currentPage-1)*pageSize;
		sql+=" order by createdate desc limit "+begin+","+pageSize;
		//12.根据商品分类id查询商品列表数据
		List<Item>list = service.findListBySql(sql);
		
		//13.处理步长数据
		int[] steps = StepUtils.getSteps(currentPage, totalPage, 5);
		List<Integer>septsList = new ArrayList<>();
		int start = steps[0];
		for (int i = steps[0]; i <= steps[1]; i++) {
			septsList.add(start);
			start++;
		}
		
		//处理回显数据
		dataDicService.selected("001",pricesection);
		dataDicService.selected("002",weightsection);
		
		//13.将数据存储到作用域中
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalSize", totalSize);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("septsList", septsList);
		
		return "/WEB-INF/front/page/itemlist.jsp";
	}
	
	/**
	 * 显示前端商品详情页
	 * @param request
	 * @param response
	 */
	private String showItem(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.获取商品id
			Long id = Long.parseLong(request.getParameter("id"));
			//2.根据商品id查找对象
			Item item = service.findItemById(id);
			//3.把item存储到作用域中
			request.setAttribute("item", item);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return "/WEB-INF/front/page/item.jsp";
	}
}

