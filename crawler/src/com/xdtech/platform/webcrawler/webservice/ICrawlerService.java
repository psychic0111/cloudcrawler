package com.xdtech.platform.webcrawler.webservice;

import javax.jws.WebService;

/**
 * 全网监控爬虫-爬虫控制接口
 * 
 * @author WangWei
 * 2013-07-03
 */
@WebService(targetNamespace = "http://www.xd-tech.com")
public interface ICrawlerService {
	
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

}
