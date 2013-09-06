package com.xdtech.project.web.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xdtech.project.modules.webdb.service.BerkeleyDB;
import com.xdtech.project.modules.webdb.service.BerkeleyDBTool;
import com.xdtech.project.modules.webdb.service.DBCrawlerService;
import com.xdtech.project.util.SpringUtil;

/**
 * web应用初始化加载
 * 
 * @author Administrator
 */
public class WebApplicationContextListener implements ServletContextListener {

	/**
	 * 关闭
	 * 
	 * @param servletContextEvent
	 *            servletContextEvent
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		BerkeleyDBTool.syncAllDb();
		BerkeleyDBTool.closeDB();
	}

	/**
	 * 启动
	 * 
	 * @param servletContextEvent
	 *            servletContextEvent
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		SpringUtil.setWac(WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()));
		try {
			BerkeleyDBTool.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		DBCrawlerService dbCrawlerService = new DBCrawlerService();
		dbCrawlerService.start();
	}

}
