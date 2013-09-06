package com.xdtech.platform.crawler.webservice;

import javax.jws.WebService;

import com.xdtech.platform.crawler.control.bean.FetchTemplate;
import com.xdtech.platform.crawler.webservice.bean.ParseResult;

@WebService(targetNamespace = "http://www.xd-tech.com")
public interface CrawlerServiceInterface {
	/**
	 * 启动
	 */
	public void start();

	/**
	 * 停止
	 */
	public void stop();

	/**
	 * 获取状态
	 * 
	 * @return
	 */
	public String findRunStatus();

	/**
	 * 解析一个URL
	 * 
	 * @param url
	 * @return
	 */
	public ParseResult parser(String tableName,String url, FetchTemplate fetchTemplate);
	
	/**
	 * 获取当前爬虫的所有即时状态
	 * @return
	 */
	public String[][] findAllCrawlerStatus();
	/**
	 * 获取当前爬虫的连接状态
	 * @return
	 */
	public Integer getCrawlerConnectionStatus();
	/**
	 * 重新加载配置
	 */
	public void setCrawlerConfig();
}
