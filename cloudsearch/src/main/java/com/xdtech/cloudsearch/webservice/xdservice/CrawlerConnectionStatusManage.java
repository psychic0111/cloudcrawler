package com.xdtech.cloudsearch.webservice.xdservice;

public class CrawlerConnectionStatusManage {
	/**
	 * 当状态等于2时，代表所有的爬虫都已经拿到最新的配置，状态等于1时，代表爬虫和调度系统连接正常，当状态等于0时，代表其中有一个爬虫无法获取连接
	 */
	public static Integer crawlerConnectionStatus = 1;
	
	

}
