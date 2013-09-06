package com.xdtech.platform.webcrawler.task;

import java.net.MalformedURLException;



import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.nbean.NEngine;
import com.xdtech.platform.webcrawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.task.TaskHelper;
import com.xdtech.platform.crawler.ws.ndispatcher.Crawler;
import com.xdtech.platform.util.AppConf;
import com.xdtech.platform.webcrawler.CrawlerStatus;
import com.xdtech.platform.webcrawler.dbcash.WebCrawlerUrlManager;
import com.xdtech.platform.webcrawler.ws.webdb.WebDBCrawlerServiceInterface;
import com.xdtech.platform.webcrawler.ws.webdb.WebDBCrawlerServiceService;
import com.xdtech.platform.webcrawler.ws.webdb.WebDbCrawler;


public class XDWebCrawlerDownloadUrlTask extends Thread{
	/** 爬虫配置 */
	public Crawler crawler = null;
	/** 搜索引擎列表 */
	public List<NEngine> nEngines = null;
	/** URLloader线程组 */
	private static ThreadGroup urlManagerThreadGroup = new ThreadGroup("XDDownloadWebCrawlerUrlTaskThread");
	/** 线程名称前缀 */
	private static final String THREAD_GROUP_NAME = "XDWebCrawlerDownloadUrlTask-";
	public void run() {
		try {
			Thread.sleep(1000 * 60 * 1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		this.crawler = NConfigManager.getCrawler();
		this.nEngines = NConfigManager.getNEngines();
		int length = nEngines.size();
		for(int i =0;i<length;i++){
			Url0LevelLoadTask task = new Url0LevelLoadTask(THREAD_GROUP_NAME + i,nEngines.get(i));
			task.start();
		}
		System.out.println("********************************");
		System.out.println("爬虫名称：" + ((crawler == null)?"":crawler.getName()));
		System.out.println("获取爬虫信息为："+crawler);
		System.out.println("获取搜索引擎个数："+nEngines.size());
		System.out.println("********************************");
		
	}
	/**
	 * 一级地址任务
	 * 
	 */
	private static class Url0LevelLoadTask extends Thread {
		NEngine nEngine = null;
		/**
		 * 构造方法
		 */
		public Url0LevelLoadTask(String taskName,NEngine nEngine) {
			super(urlManagerThreadGroup, taskName);
			this.nEngine = nEngine;
			TaskHelper.setNEngineGenerateTime(this.nEngine);
		}
		
		public synchronized void start() {
			synchronized (Url0LevelLoadTask.class) {
				super.start();
			}
		}

		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("当期地址库数量为："+WebCrawlerUrlManager.getSize()+"个！");
				if (com.xdtech.platform.webcrawler.ControlStatus.run) {
					try {
						CrawlerStatus.WebCrawlerDownloadRunStatus = "2";
						if (WebCrawlerUrlManager.getSize()==0) {// 达到执行条件
							List<WebDbCrawler> newDataList = load0Level();
							List<WebDbCrawler> dataList = new ArrayList<WebDbCrawler>();
							if (newDataList != null && !newDataList.isEmpty()) {
								dataList.addAll(newDataList);
							}
							if (!dataList.isEmpty()) {
								FetchEntry entry = null;
								List<FetchEntry> tempList = new ArrayList<FetchEntry>();
								for (WebDbCrawler webDb : dataList) {
									entry = new FetchEntry();
									entry.setLevel(webDb.getUrllevel());
									entry.setMd5(webDb.getMd5());
									entry.setSiteId(webDb.getSiteid());
									entry.setUrl(webDb.getUrl());
									entry.setUrlType(webDb.getUrlType());
									entry.setDataId(webDb.getId());
									entry.setWebdb(webDb.getDbname());
									entry.setRefurl(webDb.getRefurl());
									
									entry.setSearchEngine(webDb.getSearchEngine());
									if(null != webDb.getPublishTime()){
										int year=webDb.getPublishTime().getYear();
										int month=webDb.getPublishTime().getMonth()+1;
										int day=webDb.getPublishTime().getDay();
										int hour = webDb.getPublishTime().getHour();
										int minute = webDb.getPublishTime().getMinute();
										Date date = new Date();
										date.setYear(year);
										date.setMonth(month);
										date.setDate(day);
										date.setHours(hour);
										date.setMonth(minute);
										entry.setPublishTime(date);
									}
									entry.setAuthor(webDb.getAuthor());
									entry.setSource(webDb.getSource());
									entry.setImagePath(webDb.getImagePath());
									entry.setTitle(webDb.getTitle());
									entry.setTimeLong(webDb.getTimeLong());
									tempList.add(entry);
								}
								if (!tempList.isEmpty()) {
									WebCrawlerUrlManager.addOneLevelUrl(tempList);
								}
							}
							nEngine.setUrlGenerateDate(new Date());
						}else{
							try {
								Thread.sleep(30000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		/**
		 * 一级地址加载策略
		 */
		private static List<WebDbCrawler> load0Level() {
			List<WebDbCrawler> result = null;
			String query = "status!=1 AND status!=3";
			URL serviceUrl = null;
			try {
				serviceUrl = new URL(AppConf.get().get("webdbcrawler.webservice.url"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			WebDBCrawlerServiceService webdbService = new WebDBCrawlerServiceService(serviceUrl, new QName("http://www.xd-tech.com", "WebDBCrawlerServiceService"));
			WebDBCrawlerServiceInterface interfaceObj = webdbService.getWebDBCrawlerServicePort();
			int pageNum = 200;
			try {
				result = interfaceObj.findWebDbByQueryFromTable(query, pageNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
	}

}
