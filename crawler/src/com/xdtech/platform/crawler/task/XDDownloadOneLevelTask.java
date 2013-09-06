package com.xdtech.platform.crawler.task;

import java.net.MalformedURLException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.nbean.NTask;
import com.xdtech.platform.crawler.dbcach.OneLevelUrlManager;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.ws.ndispatcher.Crawler;
import com.xdtech.platform.crawler.ws.ndispatcher.Task;
import com.xdtech.platform.crawler.ws.webdb.Exception_Exception;
import com.xdtech.platform.crawler.ws.webdb.WebDBServiceInterface;
import com.xdtech.platform.crawler.ws.webdb.WebDBServiceService;
import com.xdtech.platform.crawler.ws.webdb.WebDb;
import com.xdtech.platform.util.AppConf;
import com.xdtech.platform.webcrawler.CrawlerStatus;


public class XDDownloadOneLevelTask extends Thread{
	/** 爬虫配置 */
	private static Crawler conf = NConfigManager.getCrawler();
	/** URLloader线程组 */
	private static ThreadGroup urlManagerThreadGroup = new ThreadGroup("XDDownloadOneLevelTask-");
	/** 线程名称前缀 */
	private static final String THREAD_GROUP_NAME = "XDDownloadOneLevelTask-";
	public void run() {
		try {
			Thread.sleep(1000 * 60 * 1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		conf = NConfigManager.getCrawler();
		if(null != conf){
			List<Task> tasks = conf.getTasks();
			List<NTask> ntasks = new ArrayList<NTask>(tasks.size());
			for(int i=0;i<tasks.size();i++){
				NTask nt = new NTask();
				nt.fix(tasks.get(i));
				ntasks.add(nt);
			}
			int length = ntasks.size();
			for(int i =0;i<length;i++){
				Url0LevelLoadTask task = new Url0LevelLoadTask(THREAD_GROUP_NAME + i,ntasks.get(i));
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
		NTask task = null;
		/**
		 * 构造方法
		 */
		public Url0LevelLoadTask(String taskName,NTask task) {
			super(urlManagerThreadGroup, taskName);
			this.task = task;
			this.task.setUrlGenerateDate(new Date());
			TaskHelper.setTaskGenerateTime(this.task);
		}
		
		public synchronized void start() {
			synchronized (Url0LevelLoadTask.class) {
				super.start();
			}
		}

		public void run() {
			while (true) {
				//System.out.println("加载一级地址！一级地址库的数量为：" + OneLevelUrlManager.getSize());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (ControlStatus.run) {
					try {
						if (OneLevelUrlManager.getSize()==0) {// 达到执行条件
							List<WebDb> newDataList = load0Level();
							List<WebDb> dataList = new ArrayList<WebDb>();
							if (newDataList != null && !newDataList.isEmpty()) {
								dataList.addAll(newDataList);
							}
							if (!dataList.isEmpty()) {
								FetchEntry entry = null;
								List<FetchEntry> tempList = new ArrayList<FetchEntry>();
								for (WebDb webDb : dataList) {
									entry = new FetchEntry();
									entry.setLevel(0);
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
									OneLevelUrlManager.addOneLevelUrl(tempList);
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
		private static List<WebDb> load0Level() {
			List<WebDb> result = null;
			String query = "status!=1 AND status!=3";
			URL serviceUrl = null;
			try {
				serviceUrl = new URL(AppConf.get().get("webdb.webservice.url"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			WebDBServiceService webdbService = new WebDBServiceService(serviceUrl, new QName("http://www.xd-tech.com", "WebDBServiceService"));
			WebDBServiceInterface interfaceObj = webdbService.getWebDBServicePort();
			String tableName = AppConf.get().get("webdb.url0level.tablename", "webdb_l1");
			int pageNum = 200;
			try {
				List<String> tableNames = new ArrayList<String>();
				tableNames.add(tableName);
				result = interfaceObj.findWebDbByQueryFromTable(tableNames, query, pageNum);
			} catch (Exception_Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	}

}
