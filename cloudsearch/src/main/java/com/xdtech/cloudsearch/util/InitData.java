package com.xdtech.cloudsearch.util;


import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerService;

public class InitData {
	private static CrawlerService crawlerService;
	/**
	 * 初始化爬虫状态数据
	 */
	public static void initSiteIsPart(){
		crawlerService = AppContextUtils.getBean("crawlerService");
		//将站点信息更新为未分配状态
		crawlerService.getDao().getTemplate().bulkUpdate("update Crawler set crawlerStatus =1,isDeath = 1,autoConnects = 0");
	}

}
