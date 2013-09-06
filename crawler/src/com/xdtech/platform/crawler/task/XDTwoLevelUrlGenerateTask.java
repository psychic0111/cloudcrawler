package com.xdtech.platform.crawler.task;

import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.nbean.NSite;
import com.xdtech.platform.crawler.control.nbean.NTask;
import com.xdtech.platform.crawler.dbcach.OneLevelUrlManager;
import com.xdtech.platform.crawler.parser.PageParser;
import com.xdtech.platform.crawler.parser.ParserUtil;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.ProtocolOutput;
import com.xdtech.platform.crawler.ws.ndispatcher.Crawler;
import com.xdtech.platform.crawler.ws.ndispatcher.Site;
import com.xdtech.platform.crawler.ws.ndispatcher.Task;
import com.xdtech.platform.crawler.ws.webdb.UrlInfo;
import com.xdtech.platform.util.RegularSelectHelper;
import com.xdtech.platform.webcrawler.CrawlerStatus;
/**
 * 从一级地址库得到地址，使用http访问一级地址，根据站点配置规则，生成二级地址
 * @author Administrator
 *
 */
public class XDTwoLevelUrlGenerateTask extends Thread{
	/** 爬虫配置 */
	private static Crawler conf = NConfigManager.getCrawler();
	/** 线程名称前缀 */
	private static final String THREAD_GROUP_NAME = "XDTwoLevelUrlGenerateTask-";
	/** 线程组 */
	private ThreadGroup group = new ThreadGroup(THREAD_GROUP_NAME);
	private String tableName = "crawler";
	public void run(){
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
	public class UrlGenerate extends Thread{
		/** 任务 */
		private Task task;
		/** 将任务转换成实体任务，主要加了一个任务执行的时间 */
		NTask nt = new NTask();
		/** 该任务中要采集的网站 */
		List<Site> sites = new ArrayList<Site>();
		/**
		 * 站点ID和站点的映射关系
		 */
		private Map<String, NSite> nsiteMap = new HashMap<String, NSite>();
		/** 区别任务是全网监控爬虫，还是站点爬虫 */
		private String tableName;
		List<UrlInfo> urlInfos = new ArrayList<UrlInfo>();
		public UrlGenerate(String name,String tableName,Task task) {
			super(group, name);
			this.task = task;
			this.tableName = tableName;
		}
		public void run() {
			sites = task.getSiteList();
			if (sites != null && sites.size() > 0) {
				for (Site site : sites) {
					NSite nsite = new NSite();
					nsite.fix(site);
					nsiteMap.put(nsite.getId(), nsite);
				}
			}
			nt.fix(task);
			TaskHelper.setTaskGenerateTime(nt);
			while(true){
				try {
					//System.out.println("生成二级地址！一级地址库的数量为：" + OneLevelUrlManager.getSize());
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try{
					if (ControlStatus.run) {
						if(OneLevelUrlManager.getSize() != 0){
							FetchEntry entry = OneLevelUrlManager.getNext();
							generateUrl(tableName,entry,NConfigManager.getNSiteById(entry.getSiteId()));//普通地址生成
							nt.setUrlGenerateDate(new Date());
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		/**
		 * 地址生成器插件接口
		 * @param site
		 */
		public void generateUrl(String tableName,FetchEntry entry,NSite site){
			com.xdtech.platform.crawler.protocol.HtmlFetcher htmlFetcher = new com.xdtech.platform.crawler.protocol.HtmlFetcher();
			ProtocolOutput outPut = htmlFetcher.request(entry.getBaseUrl(), entry, 0);
			String html = "";
			try {
				String encode = PageParser.findcharset(outPut.getContent(), entry);
				html = new String(outPut.getContent().getContent(),encode);
			} catch (UnsupportedEncodingException e) {
			}
			//如果下载的页面不为空，则根据搜索引擎规则进行集成生成详细地址
			if(!"".equals(html)){
				Document doc = Jsoup.parse(html);
				System.out.println("aaa"+site+"bbb");
				String addressRule = site.getAddressRule();
				Set<String> urls = null;
				//如果地址规则为空，则获取本页内的连接，否则否则根据地址规则获取连接
				if(null == addressRule || "".equals(addressRule)){
					urls = ParserUtil.findUrlFromA(doc, entry,null);
				}else{
					urls = getUrlsByAddressRule(html, doc, entry, addressRule);
				}
				for(String sa:urls){
					if(null != site && RegularSelectHelper.findValueByRegOrInclude(sa, site.getUrlReg())){
						UrlInfo urlInfo = new UrlInfo();
						urlInfo.setLevel(1);
						urlInfo.setUrl(sa);
						urlInfo.setRefurl(site.getInUrl());
						urlInfo.setSiteId(entry.getSiteId());
						urlInfos.add(urlInfo);
					}
				}
				if(!urlInfos.isEmpty()){
					//if (urlInfos.size() >= 500) {
						try {
							htmlFetcher.addOutLinks(tableName,urlInfos);
						} catch (Exception e) {
							e.printStackTrace();
						}
						urls.clear();
					//}
				}
			}
		}
	}
	/**
	 * 根据地址规则获取连接
	 * @param addressRule
	 * @return
	 */
	public static Set<String> getUrlsByAddressRule(String content, Document doc,FetchEntry entry,String addressRule){
		Set<String> urls = new HashSet<String>();
		String[] addressRules = addressRule.split(" ");
		String preContent = "";
		for(String rule:addressRules){
			preContent = RegularSelectHelper.getValue(content, doc, rule, false);
			Document preDocument = Jsoup.parseBodyFragment(preContent);
			Set<String> preUrls = ParserUtil.findUrlFromA(preDocument, entry,null);
			urls.addAll(preUrls);
		}
		return urls;
	}
}
