package com.xdtech.project.web.listener;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.namespace.QName;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.logs.CrawlerLogs;
import com.xdtech.platform.crawler.protocol.HtmlFetcher;
import com.xdtech.platform.crawler.task.XDDownloadTwoLevelTask;
import com.xdtech.platform.crawler.task.XDOneLevelUrlGenerateTask;
import com.xdtech.platform.crawler.task.XDDownloadOneLevelTask;
import com.xdtech.platform.crawler.task.XDTwoLevelUrlGenerateTask;
import com.xdtech.platform.crawler.ws.ndispatcher.Crawler;
import com.xdtech.platform.crawler.ws.ndispatcher.IServerCrawlerService;
import com.xdtech.platform.crawler.ws.ndispatcher.ServerCrawlerServiceImplService;
import com.xdtech.platform.util.AppConf;
import com.xdtech.platform.util.SpringUtil;
import com.xdtech.platform.util.TimerTaskRegister;
import com.xdtech.platform.webcrawler.task.XDWebCrawlerDownloadUrlTask;
import com.xdtech.platform.webcrawler.task.XDWebCrawlerUrlGenerateTask;

/**
 * 系统启动、关闭监听，启动时负责系统数据+服务的初始化。系统关闭时负责系统资源的释放以及任务的停止。
 * 
 * @author zhangjianbing@msn.com
 */
public class WebApplicationContextListener implements ServletContextListener {

	/**
	 * 系统关闭执行方法
	 * 
	 * @param servletContextEvent
	 *            servletContextEvent
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ControlStatus.run = false;
		ControlStatus.shutdown = true;
	}

	/**
	 * 系统启动执行方法
	 * 
	 * @param servletContextEvent
	 *            servletContextEvent
	 */
	public void contextInitialized(ServletContextEvent sce) {
		SpringUtil.setWac(WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()));
		//NConfigManager.getInstance().initCrawlerConfig();
		//开始一级地址任务
		System.out.println("*************开始启动一级地址任务****************");
		XDOneLevelUrlGenerateTask oneLevelTask = new XDOneLevelUrlGenerateTask();
		Thread t = new Thread(oneLevelTask);
		t.start();
		System.out.println("*************一级地址任务启动完成****************");
		
		
		//开始一级地址任务
		System.out.println("*************开始启动二级地址任务****************");
		XDDownloadOneLevelTask twoLevelTask = new XDDownloadOneLevelTask();
		Thread two = new Thread(twoLevelTask);
		two.start();
		System.out.println("*************二级地址任务启动完成****************");
		
		
		//开始生成二级地址任务
		System.out.println("*************开始生成二级地址任务****************");
		XDTwoLevelUrlGenerateTask gtwoLevelTask = new XDTwoLevelUrlGenerateTask();
		Thread gt = new Thread(gtwoLevelTask);
		gt.start();
		System.out.println("*************开始生成二级地址任务****************");
		
		//开始生成二级地址任务
		System.out.println("*************开始生成二级地址任务****************");
		XDDownloadTwoLevelTask tdTask = new XDDownloadTwoLevelTask();
		Thread tdt = new Thread(tdTask);
		tdt.start();
		System.out.println("*************开始生成二级地址任务****************");
		
		//开始生成二级地址任务
		System.out.println("*************开始生成二级地址任务****************");
		NConfigManager config = new NConfigManager();
		config.start();
		System.out.println("*************开始生成二级地址任务****************");
		
		
		CrawlerLogs logs = new CrawlerLogs();
		logs.start();
		
		
		XDWebCrawlerDownloadUrlTask crawlerTask = new XDWebCrawlerDownloadUrlTask();
		crawlerTask.start();
		
		XDWebCrawlerUrlGenerateTask generateTask = new XDWebCrawlerUrlGenerateTask();
		generateTask.start();
		
	}
}
