package com.project.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.project.po.User;



public class PrvilegeATagSupport extends TagSupport{
	private String href;
	private String target = "right";
	private String clas;
	private String id;
	
	@Override //开始标签
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpSession session  = pageContext.getSession();
			HttpServletRequest req = (HttpServletRequest)pageContext.getRequest();
			//1.获得当前用户
			User user = (User)session.getAttribute("loginUser");
			//2.判断当前用户是否拥有访问权限 
			//${pageContext.request.contextPath }/itemAction.action?method=editUI&id=${item.id} 
			// itemAction.action?method=edit
			String url = href.replace(req.getContextPath()+"/", "");
			url = url.replaceAll("[&]{1,2}[a-z|A-Z|0-9|=]{0,}", "");
			//System.out.println(url+"---------");
			//System.out.println(url);
			boolean hshPrivilegeUrl = user.hasPrivilegeByUrl(url);
			//当前用户所拥有的权限
			//有权限 写a标签 没权限 写空字符
			if(hshPrivilegeUrl){ 
				out.print("<a href=\""+href+"\" class=\""+clas+"\" id=\""+id+"\" target=\""+target+"\" >");
				return EVAL_BODY_INCLUDE;
			} else {
				return  SKIP_BODY;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  SKIP_BODY;

	}
	@Override //结束标签
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().print("</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return EVAL_PAGE; //计算后面的页面
	}
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getClas() {
		return clas;
	}
	public void setClas(String clas) {
		this.clas = clas;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
