package com.project.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.project.dao.ContentCategoryDao;
import com.project.dao.ContentDao;
import com.project.po.Content;
import com.project.po.ContentCategory;
import com.project.util.MyDateConverter;



public class ContentService {
	private ContentDao dao = new ContentDao();
	private ContentCategoryDao categoryDao = new ContentCategoryDao();

	//把数据修改到数据库
	public void edit(Content content) {
		dao.edit(content);
	}
	public Content findContentById(int id) {
		try {
			Map map = dao.findContentAndContentCategoryById(id);
			Content content = null;
			try {
				content = new Content();
				ConvertUtils.register(new MyDateConverter(), Date.class);
				BeanUtils.populate(content, map);
			} catch (Exception e) {
			}
			ContentCategory contentCategory = new ContentCategory();
			contentCategory.setId((Long)map.get("content_category_id"));
			contentCategory.setName((String)map.get("name"));
			content.setContentCategory(contentCategory);
			return content;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	//启用指定内容分类
	public void enable(int id) {
		//1.获得内容分类信息
		Content content =dao.findContentById(id);
		content.setStatus(1);
		dao.edit(content);
		
	}
	//禁用指定内容分类
	public void disable(int id) {
		//1.获得内容分类信息
		Content content =dao.findContentById(id);
		content.setStatus(0);
		dao.edit(content);
	}
	//添加内容
	public void add(Content content) {

		dao.add(content);
	}
	
	public List<Content> findListByCategoryId(Long contentCategoryId) {
		
		return dao.findListByCategoryId(contentCategoryId);
	}
	public List<Content> getListContent(String name) {
		Long contentCategoryId = categoryDao.findIdByName(name);
		List<Content> list = dao.findListByCategoryId(contentCategoryId);
		return list;
	}
}
