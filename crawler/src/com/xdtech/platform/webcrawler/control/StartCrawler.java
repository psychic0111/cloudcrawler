package com.xdtech.platform.webcrawler.control;


import java.util.List;
import com.xdtech.platform.webcrawler.ControlStatus;
import com.xdtech.platform.webcrawler.CrawlerStatus;
import com.xdtech.platform.webcrawler.task.XDWebCrawlerUrlGenerateTask;
import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.nbean.NEngine;
import com.xdtech.platform.crawler.logger.XDLogger;

/**
 * 启动全网监控爬虫
 * 
 * @author WangWei
 * 2013-07-01
 */
public class StartCrawler {
	/**
	 * 启动爬虫任务
	 */
	public synchronized static void runCrawler() {
		ControlStatus.run = true;
		ControlStatus.shutdown = false;
		boolean configInit = initCrawlerConfig();// 初始化爬虫配置
		if (!configInit) {
			XDLogger.exception("没有发现爬虫或者爬虫被禁用");
			return;
		}
		startHTMLFetcher();
		
	}
	/**
	 * 启动爬虫标志位
	 */
	public synchronized static void startCrawler() {
		if(com.xdtech.platform.webcrawler.ControlStatus.run){
			return;
		}else{
			com.xdtech.platform.webcrawler.ControlStatus.run = true;
		}
		if(!com.xdtech.platform.crawler.control.StartCrawler.getIsDownload()){
			com.xdtech.platform.crawler.control.StartCrawler.startHTMLFetcher();// 启动html下载
		}
	}
	/**
	 * 启动HTML下载
	 */
	public static void startHTMLFetcher() {
		System.out.println("startHTMLFetcher");
		XDWebCrawlerUrlGenerateTask task = new XDWebCrawlerUrlGenerateTask();
		task.start();
	}
	/**
	 * 初始化爬虫配置
	 */
	public static boolean initCrawlerConfig() {
		List<NEngine> engines = NConfigManager.getNEngines();
		if (engines == null || engines.size() <= 0) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * 测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	}
}
