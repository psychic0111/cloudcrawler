package com.xdtech.cloudsearch.webservice.xdservice;

import java.util.List;


import javax.jws.WebService;

import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;
import com.xdtech.cloudsearch.module.webcrawler.bean.Engine;
import com.xdtech.cloudsearch.module.webcrawler.bean.Keyword;
import com.xdtech.cloudsearch.webservice.bean.TimeRange;

/**
 * 调度端的webservice接口
 * 
 * @author WangWei
 * 2013-07-01
 */
@WebService(targetNamespace = "http://www.xd-tech.com")
public interface IServerCrawlerService {
	/**
	 * 根据爬虫的代码，获取爬虫的配置信息
	 * @param code 爬虫所在服务器配置文件中，爬虫的代码
	 * @return 爬虫的配置信息
	 */
	Crawler getCrawlerByCode(String code);
	/**
	 * 获取所有的关键词
	 * @return
	 */
	List<Keyword> getKeywords();
	/**
	 * 获取所有的搜索引擎配置信息
	 * @return
	 */
	List<Engine> getEngines();
	/**
	 * 获取爬虫要爬取的站点
	 * @return 站点信息
	 */
	Crawler getCrawlerSite(String crawlerCode);
	/**
	 * 获取休眠时间段
	 * 
	 * @return
	 */
	public TimeRange[] getWaitTimeRange();
	/**
	 * 获取爬虫和调度直接的连接状态
	 * @return
	 */
	public Integer getCrawlerConnectionStatusManage();
	/**
	 * 初始化爬虫的状态信息 isDeath 和 autoConnects
	 * @param code
	 */
	public void initCrawlerInfo(String code);
}
