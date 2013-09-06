package com.xdtech.cloudsearch.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xdtech.cloudsearch.util.AppContextUtils;
import com.xdtech.cloudsearch.util.InitData;
import com.xdtech.cloudsearch.webservice.xdservice.CrawlerConfigManager;
import com.xdtech.cloudsearch.webservice.xdservice.CrawlerStatusManager;
import com.xdtech.cloudsearch.webservice.xdservice.DataCache;

/**
 * web应用监听器
 * @author WangWei
 *
 */
public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		AppContextUtils.setServletContext(arg0.getServletContext());
		System.out.println("开始初始化数据！");
		InitData.initSiteIsPart();
		System.out.println("初始化数据结束！");
		
		DataCache.init();
		CrawlerConfigManager.refreshInit();
		System.out.println("*************开始爬虫监听线程****************");
		CrawlerStatusManager config = new CrawlerStatusManager();
		config.start();
		System.out.println("*************结束爬虫监听线程****************");
	}

}
