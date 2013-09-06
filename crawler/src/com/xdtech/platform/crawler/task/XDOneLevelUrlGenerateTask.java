package com.xdtech.platform.crawler.task;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.nbean.NSite;
import com.xdtech.platform.crawler.control.nbean.NTask;
import com.xdtech.platform.crawler.ws.ndispatcher.Crawler;
import com.xdtech.platform.crawler.ws.ndispatcher.Site;
import com.xdtech.platform.crawler.ws.ndispatcher.Task;
import com.xdtech.platform.crawler.ws.webdb.WebDBServiceClient;
import com.xdtech.platform.util.GenURL;
import com.xdtech.platform.webcrawler.CrawlerStatus;


public class XDOneLevelUrlGenerateTask extends Thread{
	
	private static Crawler conf = NConfigManager.getCrawler();

	private String tableName = "crawler";
	public void run(){
		/*System.out.println("**************************");
		System.out.println("生成二级地址！");
		System.out.println("**************************");*/
		try {
			Thread.sleep(1000 * 60 * 1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		conf = NConfigManager.getCrawler();
		if(null != conf){
			List<Task> tasks = conf.getTasks();
			int length = tasks.size();
			for(int i =0;i<length;i++){
				UrlGenerate task = new UrlGenerate(THREAD_GROUP_NAME + i,tableName,tasks.get(i));
				task.start();
			}
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int n = group.activeCount();
				Thread[] list = new Thread[n];
				group.enumerate(list);
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
	/**
	 * 地址生成器插件接口
	 * @param site
	 */
	public void generateUrlByPlgin(NSite site){
		
	}
	/** 线程名称前缀 */
	private static final String THREAD_GROUP_NAME = "XDOneLevelUrlGenerateTask-";
	/** 线程组 */
	private ThreadGroup group = new ThreadGroup(THREAD_GROUP_NAME);
	
	public class UrlGenerate extends Thread{
		/** 任务 */
		private Task task;
		
		/** 将任务转换成实体任务，主要加了一个任务执行的时间 */
		NTask nt = new NTask();
		/** 该任务中要采集的网站 */
		List<Site> sites = new ArrayList<Site>();
		/**
		 * 要进行生成地址的站点
		 */
		private List<NSite> nsites = new ArrayList<NSite>();
		/** 区别任务是全网监控爬虫，还是站点爬虫 */
		private String tableName;
		public UrlGenerate(String name,String tableName,Task task) {
			super(group, name);
			this.task = task;
			this.tableName = tableName;
		}
		@Override
		public void run() {
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
			while(true){
				//System.out.println("***************生成一级地址***************");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (ControlStatus.run) {
					boolean result = TaskHelper.checked(nt);
					System.out.println("task execType:"+task.getExecType()+"\t"+"task weekDays:"+task.getWeekDays()+"\tresult:"+result);
					if (result) {// 达到执行条件
						if (nsites != null) {
							int length = nsites.size();
							for (int i = 0;i<length;i++) {
								NSite nsite = nsites.get(i);
								try {
									generateUrlByPlgin(nsite);//根据插件生成地址
									generateUrl(tableName,nsite);//普通地址生成
									nt.setUrlGenerateDate(new Date());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
		/**
		 * 地址生成器插件接口
		 * @param site
		 */
		public void generateUrl(String tableName,NSite site){
			String inUrl = site.getInUrl();// 入口地址
			List<String> urls = new ArrayList<String>();
			urls.add(inUrl);
			if (site.getDynamicRule() != null && !site.getDynamicRule().isEmpty()) {
				GenURL genURL = new GenURL();
				/*
				 * 动态地址规则入库
				 */
				String rule = site.getDynamicRule();
				if (rule != null) {
					String[] urlregs = rule.split("\n");
					for (String urlreg : urlregs) {
						String[] results = null;
						try {
							results = genURL.parse(urlreg);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if (results != null) {
							for (String tempUrl : results) {
								if (tempUrl != null && !urls.contains(tempUrl)) {// 这里的去重判断并不能完全去重。这不是一个bug，在地址库那里会再做一次去重。
									urls.add(tempUrl);
								}
							}
						}
					}

				}
			}
			//if (urls.size() >= 500) {
				try {
					WebDBServiceClient.addUrlInWebDb(tableName,urls.toArray(new String[urls.size()]), 0, site.getId());
					NoticeHelper.oneLevelDownloadNotice = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				urls.clear();
			//}
		}
	}
}
