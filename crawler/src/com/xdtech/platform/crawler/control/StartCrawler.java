package com.xdtech.platform.crawler.control;

import com.xdtech.platform.crawler.ControlStatus;

import com.xdtech.platform.crawler.control.bean.CrawlerConf;
import com.xdtech.platform.crawler.logger.XDLogger;
import com.xdtech.platform.crawler.protocol.HtmlFetcher;

/**
 * 用于启动爬虫，让爬虫开始下载
 * 
 * @author zhangjianbing@msn.com
 */
public class StartCrawler {
	/**
	 * 启动爬虫任务
	 */
	public synchronized static void runCrawler(String tableName) {
		if(ControlStatus.run){
			return;
		}
		ControlStatus.run = true;
		ControlStatus.shutdown = false;
		if (NConfigManager.getCrawler() == null) {
			XDLogger.exception("没有发现爬虫或者爬虫被禁用");
			return;
		}
		if(!getIsDownload()){
			startHTMLFetcher();// 启动html下载
			setIsDownload(true);
		}
	}
	
	/**
	 * 全网监控启动爬虫任务
	 */
	public synchronized static void runCrawler() {
		startHTMLFetcher();// 启动html下载
	}
	/**
	 * 初始化爬虫配置
	 */
	public static boolean initCrawlerNConfig() {
		NConfigManager.getInstance().initCrawlerConfig();
		return true;
	}

	/**
	 * 标示下载模块是否启动
	 */
	private static boolean isDownload = false;
	/**
	 * 获取下载模块的状态
	 * @return
	 */
	public static boolean getIsDownload(){
		return isDownload;
	}
	/**
	 * 设置下载状态
	 * @param isDownload
	 */
	public static void setIsDownload(boolean isDownload){
		StartCrawler.isDownload = isDownload;
	}
	/**
	 * 启动HTML下载
	 */
	public static void startHTMLFetcher() {
		HtmlFetcher fetcher = new HtmlFetcher();
		fetcher.downLoad();
	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StartCrawler.runCrawler(null);
	}
}
