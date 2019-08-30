package com.project.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class DateTagSupport extends TagSupport{

	private String pattern = "yyyy-MM-dd HH:mm:ss";
	private Date date;
	
	@Override // 标签开始 
	public int doStartTag() throws JspException {
		try {
			//日期格式
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			//打印结果  
			String result = simpleDateFormat.format(date);
			//TagSupport 提供pageContext对象 
			JspWriter out = pageContext.getOut();
			out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
		//return SKIP_BODY; // 跳过内容  单标签
		//return EVAL_BODY_INCLUDE;// 计算包含的内容  成对标签 if
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
