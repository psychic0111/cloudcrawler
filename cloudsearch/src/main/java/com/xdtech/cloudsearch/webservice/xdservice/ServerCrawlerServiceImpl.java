package com.xdtech.cloudsearch.webservice.xdservice;

import java.util.ArrayList;


import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerService;
import com.xdtech.cloudsearch.module.task.bean.Task;
import com.xdtech.cloudsearch.module.webcrawler.bean.Engine;
import com.xdtech.cloudsearch.module.webcrawler.bean.Keyword;
import com.xdtech.cloudsearch.module.webcrawler.service.EngineService;
import com.xdtech.cloudsearch.module.webcrawler.service.KeywordService;
import com.xdtech.cloudsearch.util.AppConf;
import com.xdtech.cloudsearch.webservice.bean.TimeRange;

/**
 * 调度端的webservice实现
 * 
 * @author WangWei
 * 2013-06-28
 */
@WebService(endpointInterface="com.xdtech.cloudsearch.webservice.xdservice.IServerCrawlerService",targetNamespace = "http://www.xd-tech.com")
public class ServerCrawlerServiceImpl implements IServerCrawlerService{
	@Autowired(required = true)
	private CrawlerService crawlerService;
	@Autowired(required = true)
	private EngineService engineService;
	@Autowired(required = true)
	private KeywordService keywordService;
	/**
	 * 根据爬虫的代码，获取爬虫的配置信息
	 * @param code 爬虫所在服务器配置文件中，爬虫的代码
	 * @return 爬虫的配置信息
	 */
	public Crawler getCrawlerByCode(String code) {
		return crawlerService.getCrawlerByCode(code);
	}

	/**
	 * 获取所有的搜索引擎配置信息
	 */
	public List<Engine> getEngines() {
		String queryString  = " from Engine where isUse =1 ";
		return engineService.find(queryString);
	}

	/**
	 * 获取所有的关键词信息
	 */
	public List<Keyword> getKeywords() {
		String queryString  = " from Keyword where isUse =1 ";
		return keywordService.find(queryString);
	}

	/**
	 * 该方法结合爬虫，站点，任务进行分配
	 */
	public Crawler getCrawlerSite(String crawlerCode) {
		Crawler crawler = DataCache.getCrawler(crawlerCode);
		if(crawler!=null){
			List<Task> tasks = CrawlerConfigManager.getCrawlerTask(crawlerCode);
			crawler.setTasks(tasks);
		}
		return crawler;
	}
	/**
	 * 获取爬虫休眠时间
	 */
	public TimeRange[] getWaitTimeRange() {
		List<TimeRange> result = new ArrayList<TimeRange>();
		String waitTime = AppConf.get().get("crawler.wait.time", "");
		String[] terms = waitTime.split(",");
		if (terms != null) {
			TimeRange range = null;
			for (int i = 0; i < terms.length; i++) {
				if (terms[i].trim().length() > 0) {
					String[] timeTerm = terms[i].split("-");
					range = new TimeRange();
					range.setStart(timeTerm[0].trim());
					range.setEnd(timeTerm[1].trim());
					result.add(range);
				}
			}
		}
		return result.toArray(new TimeRange[result.size()]);
	}

	public Integer getCrawlerConnectionStatusManage() {
		return CrawlerConnectionStatusManage.crawlerConnectionStatus;
	}

	public void initCrawlerInfo(String code) {
		crawlerService.getDao().getTemplate().bulkUpdate("update Crawler set crawlerStatus =1,isDeath = 1,autoConnects = 0 where code = '"+code+"'");
		//通知爬虫重新分配任务
		//CrawlerConnectionStatusManage.crawlerConnectionStatus = 0;
	}
}
