package com.xdtech.platform.crawler.task;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.nbean.NSite;
import com.xdtech.platform.crawler.control.nbean.NTask;
import com.xdtech.platform.crawler.dbcach.TwoLevelUrlManager;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.ws.ndispatcher.Crawler;
import com.xdtech.platform.crawler.ws.ndispatcher.Site;
import com.xdtech.platform.crawler.ws.ndispatcher.Task;
import com.xdtech.platform.crawler.ws.webdb.Exception_Exception;
import com.xdtech.platform.crawler.ws.webdb.WebDBServiceInterface;
import com.xdtech.platform.crawler.ws.webdb.WebDBServiceService;
import com.xdtech.platform.crawler.ws.webdb.WebDb;
import com.xdtech.platform.util.AppConf;
import com.xdtech.platform.webcrawler.CrawlerStatus;
import com.xdtech.platform.webcrawler.ws.webdb.WebDBCrawlerServiceInterface;
import com.xdtech.platform.webcrawler.ws.webdb.WebDBCrawlerServiceService;
import com.xdtech.platform.webcrawler.ws.webdb.WebDbCrawler;

public class XDDownloadTwoLevelTask extends Thread{
	/** 爬虫配置 */
	private static Crawler conf = NConfigManager.getCrawler();
	/** URLloader线程组 */
	private static ThreadGroup urlManagerThreadGroup = new ThreadGroup("XDDownloadTwoLevelTask-");
	/** 线程名称前缀 */
	private static final String THREAD_GROUP_NAME = "XDDownloadTwoLevelTask-";
	@Override
	public void run() {
		try {
			Thread.sleep(1000 * 60 * 1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		conf = NConfigManager.getCrawler();
		if(null != conf){
			List<Task> tasks = conf.getTasks();
			for(int i=0;i<1;i++){
				Url0LevelLoadTask task = new Url0LevelLoadTask(THREAD_GROUP_NAME + i,tasks.get(i));
				task.start();
			}
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int n = urlManagerThreadGroup.activeCount();
				Thread[] list = new Thread[n];
				urlManagerThreadGroup.enumerate(list);
				CrawlerStatus.setCrawlThreadNum(n);
				boolean noMoreFetcherThread = true;
				for (int i = 0; i < n; i++) {
					if (list[i] == null) {
						continue;
					}
					String tname = list[i].getName();
					if (tname.startsWith(THREAD_GROUP_NAME)) {
						noMoreFetcherThread = false;
					}
				}
				if (noMoreFetcherThread) {
					break;
				}
			}
		}
	}
	
	public class UrlGenerate extends Thread{
		
	}
	
	/**
	 * 一级地址任务
	 * 
	 */
	private static class Url0LevelLoadTask extends Thread {
		Task task = null;
		/** 将任务转换成实体任务，主要加了一个任务执行的时间 */
		NTask nt = new NTask();
		/** 爬虫要采集的地址表 */
		private static String[] webdbs = new String[]{"webdb_0","webdb_1","webdb_2","webdb_3","webdb_4","webdb_5","webdb_6","webdb_7","webdb_8","webdb_9","webdb_a","webdb_b","webdb_c","webdb_d","webdb_e","webdb_f"};
		/** 爬虫要采集的地址表 */
		private static String[] webcrawlerdbs = new String[]{"webdb_webcrawler"};
		/** 该任务中要采集的网站 */
		List<Site> sites = new ArrayList<Site>();
		/**
		 * 要进行生成地址的站点
		 */
		private List<NSite> nsites = new ArrayList<NSite>();
		String tableName = "crawler";
		/**
		 * 构造方法
		 */
		public Url0LevelLoadTask(String taskName,Task task) {
			super(urlManagerThreadGroup, taskName);
			this.task = task;
		}
		
		public synchronized void start() {
			synchronized (Url0LevelLoadTask.class) {
				super.start();
			}
		}

		public void run() {
			
			//站点采集二级及二级以上地址监控开始
			sites = task.getSiteList();
			if (sites != null && sites.size() > 0) {
				for (Site site : sites) {
					NSite nsite = new NSite();
					nsite.fix(site);
					nsites.add(nsite);
				}
			}
			nt.fix(task);
			TaskHelper.setTaskGenerateTime(nt);
			while (true) {
				//System.out.println("加载二级地址！二级地址库的数量为：" + TwoLevelUrlManager.getSize());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (ControlStatus.run) {
					try {
						if (TwoLevelUrlManager.getSize()==0) {// 达到执行条件
							if (nsites != null) {
								int length = nsites.size();
								List<WebDb> newDataList = null;
								for (int i = 0;i<length;i++) {
									NSite nsite = nsites.get(i);
									newDataList = load1Level(tableName,nsite);
									if (null != newDataList &&!newDataList.isEmpty()) {
										FetchEntry entry = null;
										List<FetchEntry> tempList = new ArrayList<FetchEntry>();
										for (WebDb webDb : newDataList) {
											entry = new FetchEntry();
											entry.setLevel(webDb.getUrllevel());
											entry.setMd5(webDb.getMd5());
											entry.setSiteId(webDb.getSiteid());
											entry.setUrl(webDb.getUrl());
											entry.setUrlType(webDb.getUrlType());
											entry.setDataId(webDb.getId());
											entry.setWebdb(webDb.getDbname());
											entry.setRefurl(webDb.getRefurl());
											tempList.add(entry);
										}
										if (!tempList.isEmpty()) {
											TwoLevelUrlManager.addOneLevelUrl(tempList);
										}
									}
								}
								
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		/**
		 * 一级地址加载策略
		 */
		private List<WebDb> load1Level(String tableName,NSite site) {
			int[] ulevel = new int[site.getCrawlerDeep()];// 要采集的URL等级
			Set<String> tables = new HashSet<String>();
			boolean crawlerRun = ControlStatus.run;
			if(crawlerRun){
				synchronized (webdbs) {
					for(String s:webdbs){
						tables.add(s);
					}
				}
			}
			for(int i = 1;i<site.getCrawlerDeep();i++){
				ulevel[i-1] =i;
			}
			List<WebDb> result = null;
			boolean fetcherThree = false;
			boolean fetcherSeconde = false;
			boolean fetcherFour = false;
			boolean fetcherFive = false;
			for (int i = 0; i < ulevel.length; i++) {
				if (ulevel[i] == 2) {
					fetcherThree = true;
				}
				if (ulevel[i] == 1) {
					fetcherSeconde = true;
				}
				if(ulevel[i] == 3){
					fetcherFour = true;
				}
				if(ulevel[i] == 4){
					fetcherFive = true;
				}
			}
			String query = "status=0 or status=2";
			if (fetcherFive&&fetcherFour&&fetcherThree&&fetcherSeconde) {// 只采集二级地址
				query = "(" + query + ") and (urllevel>=1 and urllevel <=4)";
			} else if (fetcherFour&&fetcherThree&&fetcherSeconde) {// 只采集三级地址
				query = "(" + query + ") and (urllevel>=1 and urllevel <=3)";
			}  else if (fetcherThree&&fetcherSeconde) {// 只采集三级地址
				query = "(" + query + ") and (urllevel>=1 and urllevel <=2)";
			} else if (fetcherSeconde) {// 只采集三级地址
				query = "(" + query + ") and urllevel=1";
			}else {// 即采集二级地址也采集三级地址
				query = "status=0 or status=2";
			}
			query += " and siteid = '"+site.getId()+"'";
			URL serviceUrl = null;
			try {
				serviceUrl = new URL(AppConf.get().get("webdb.webservice.url"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			WebDBServiceService webdbService = new WebDBServiceService(serviceUrl, new QName("http://www.xd-tech.com", "WebDBServiceService"));
			WebDBServiceInterface interfaceObj = webdbService.getWebDBServicePort();
			List<String> tableList = new ArrayList<String>(tables);
			try {
				List<WebDb> result1 = interfaceObj.findWebDbByQueryFromTable(tableList, query, 200);
				if (result1 != null) {
					if (result == null) {
						result = new ArrayList<WebDb>();
					}
					result.addAll(result1);
				}
			} catch (Exception_Exception e1) {
				e1.printStackTrace();
			}
			return result;
		}
	}
}
