package com.project.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.project.po.Privilege;
import com.project.service.PrivilegeService;

/**
 * 监听器
 *
 */
@WebListener
public class InitPrivilegeListener implements ServletContextListener {

	 
   private PrivilegeService privilegeService = new PrivilegeService();

	
    public void contextInitialized(ServletContextEvent event)  { 
        //1.加载顶级权限
    	List<Privilege>topPrivileges = privilegeService.findTopPrivileges();
    	//2.把数据粗存储到servletContext的域对象中
    	ServletContext context = event.getServletContext();
    	context.setAttribute("topPrivileges", topPrivileges);
    	//3.加载所有权限控制的url
    	List<String> urls = privilegeService.findAllPrivilegeUrls();
    	context.setAttribute("urls", urls);
    }
    
    public void contextDestroyed(ServletContextEvent arg0)  { 
        
    }
	
}
