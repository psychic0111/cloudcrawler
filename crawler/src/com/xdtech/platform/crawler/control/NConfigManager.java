package com.xdtech.platform.crawler.control;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.control.bean.FetchTemplate;
import com.xdtech.platform.crawler.control.nbean.NDataBase;
import com.xdtech.platform.crawler.control.nbean.NEngine;
import com.xdtech.platform.crawler.control.nbean.NSite;
import com.xdtech.platform.crawler.protocol.httpclient.HttpClient3;
import com.xdtech.platform.crawler.task.TaskHelper;
import com.xdtech.platform.crawler.webservice.CrawlerService;
import com.xdtech.platform.crawler.webservice.bean.CrawlerConnectionStatusManage;
import com.xdtech.platform.crawler.ws.ndispatcher.Crawler;
import com.xdtech.platform.crawler.ws.ndispatcher.DataBase;
import com.xdtech.platform.crawler.ws.ndispatcher.Engine;
import com.xdtech.platform.crawler.ws.ndispatcher.IServerCrawlerService;
import com.xdtech.platform.crawler.ws.ndispatcher.Keyword;
import com.xdtech.platform.crawler.ws.ndispatcher.ServerCrawlerServiceImplService;
import com.xdtech.platform.crawler.ws.ndispatcher.Site;
import com.xdtech.platform.crawler.ws.ndispatcher.SiteProxy;
import com.xdtech.platform.crawler.ws.ndispatcher.Task;
import com.xdtech.platform.crawler.ws.ndispatcher.Template;
import com.xdtech.platform.crawler.ws.ndispatcher.TimeRange;
import com.xdtech.platform.util.AppConf;
import com.xdtech.platform.webcrawler.webservice.CrawlerServiceImpl;

public class NConfigManager extends Thread{
	
	private static NConfigManager instance = null;
	/** 爬虫配置 */
	private static Crawler conf = null;
	/** 爬虫停止抓取的时间段 */
	private  TimeRange[] ranges = new TimeRange[] {};
	/** 爬虫可以爬取的站点集合 */
	public  Set<String> crawlerSites = new java.util.HashSet<String>();
	/** 解析模板 */
	/** 爬虫代码 */
	public final static String CRAWLER_CODE = AppConf.get().get("crawler.code");
	/** 解析模板 */
	private  Map<String, List<FetchTemplate>> siteTemplateMap = new HashMap<String, List<FetchTemplate>>();
	
	private  Map<String, String> siteIdsAndSiteNames = new HashMap<String, String>();
	
	private static NDataBase nDataBase = null;
	/** 搜索引擎列表 */
	public List<Engine> engines = null;
	/** 搜索引擎列表 */
	private static  List<NEngine> nEngines = new ArrayList<NEngine>();
	/** 搜索引擎列表 -map*/
	private static  Map<String,NEngine> mapNEngine = new HashMap<String, NEngine>();
	/** 关键词列表 */
	private static  List<Keyword> keywords = null;
	/** 站点和代理对应关系 -map*/
	private static  Map<String,SiteProxy> mapSiteProxy = new HashMap<String, SiteProxy>();
	
	/**
	 * 获取关键词列表
	 * @return
	 */
	public static List<Keyword> getKeywords(){
		return keywords;
	}
	/**
	 * 根据搜索引擎编码获取搜索引擎配置信息
	 * @param code
	 * @return
	 */
	public static NEngine getNEngine(String code){
		return mapNEngine.get(code);
	}
	/**
	 * 获取搜索引擎列表
	 * @return
	 */
	public static List<NEngine> getNEngines(){
		List<NEngine> list = new ArrayList<NEngine>(nEngines.size());
		list.addAll(nEngines);
		return list;
	}
	/**
	 * 私有构造方法
	 */
	public NConfigManager() {
	}
	/**
	 * 获取爬虫信息
	 * @return
	 */
	public static Crawler getCrawler(){
		return conf;
	}
	/**
	 * 将爬虫信息重置
	 * @return
	 */
	public static void setCrawlerNull(){
		conf = null;
	}
	/**
	 * 获取数据库连接信息
	 * @return
	 */
	public static NDataBase getNDataBase(){
		return nDataBase;
	}
	IServerCrawlerService interfaceObj = null;
	@Override
	public void run() {
		while(true){
			try {
				//System.out.println("心跳机制客户端！");
				//每个一秒钟检查一次连接状态，是否需要从新分配任务
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/*if(null != conf){
				System.out.println("心跳机制客户端！");
				System.out.println("爬虫编码："+conf.getCode());
				System.out.println("爬虫名称："+conf.getName());
				System.out.println("************************************");
				for(Task t:conf.getTasks()){
					System.out.println("任务名称："+t.getTaskName()+"\t站点个数："+t.getSiteList().size());
					System.out.println("**********************************");
					for(Site s:t.getSiteList()){
						System.out.println(s.getName());
					}
				}
			}*/
			URL serviceUrl = null;
			try {
				serviceUrl = new URL(AppConf.get().get("serverCrawler.webservice.url"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			if(null == interfaceObj){
				ServerCrawlerServiceImplService service = new ServerCrawlerServiceImplService(serviceUrl, new QName("http://www.xd-tech.com", "ServerCrawlerServiceImplService"));
				interfaceObj = service.getServerCrawlerServiceImplPort();
			}
			Integer connectionStatus = interfaceObj.getCrawlerConnectionStatusManage();
			//System.out.println("调度端的状态：" + connectionStatus);
			//爬虫更新完配置以后，将其通知消息转换成已完成配置更新
			if ( connectionStatus == 0 && ControlStatus.run){
				conf = null;
				initCrawlerConfig();
				CrawlerConnectionStatusManage.crawlerConnectionStatus = 2;
			}
			//接收到调度端的配置更新已完成以后，将爬虫的状态修改为正常
			if ( connectionStatus == 2 && ControlStatus.run){
				CrawlerConnectionStatusManage.crawlerConnectionStatus = 1;
			}
			
			
			
			
		}
	}
	/**
	 * 根据站点ID获取站点名称
	 * @param siteId
	 * @return
	 */
	public  String getSiteNameBySiteId(String siteId){
		return siteIdsAndSiteNames.get(siteId);
	}
	/**
	 * 获取单一实例
	 * 
	 * @return
	 */
	public static synchronized NConfigManager getInstance() {
		if (instance == null) {
			instance = new NConfigManager();
		}
		return instance;
	}
	/** 站点配置 */
	private static Map<String, NSite> siteMap = new HashMap<String, NSite>();
	/**
	 * 根据站点ID获取站点信息
	 * @param id
	 * @return
	 */
	public static NSite getNSiteById(String id){
		return siteMap.get(id);
	}
	/**
	 * 站点获取代理
	 * @param siteId
	 * @return
	 */
	public static SiteProxy getSiteProxy(String siteId){
		return mapSiteProxy.get(siteId);
	}
	/**
	 * 根据ID获取站点的配置
	 * @param id
	 * @return
	 */
	public synchronized Crawler getConf() {
		if(null == conf){
			initCrawlerConfig();
		}
		return conf;
	}
	/**
	 * 加载爬虫配置
	 */
	public synchronized void initCrawlerConfig() {
		URL serviceUrl = null;
		if(null == conf){
			try {
				serviceUrl = new URL(AppConf.get().get("serverCrawler.webservice.url"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			/*System.out.println("**************************************");
			System.out.println(serviceUrl);
			System.out.println(CRAWLER_CODE);
			System.out.println("a"+conf+"b");
			System.out.println("***************************************");*/
			ServerCrawlerServiceImplService service = new ServerCrawlerServiceImplService(serviceUrl, new QName("http://www.xd-tech.com", "ServerCrawlerServiceImplService"));
			IServerCrawlerService interfaceObj = service.getServerCrawlerServiceImplPort();
			conf = interfaceObj.getCrawlerSite(CRAWLER_CODE);
			DataBase dataBase = conf.getDataBase();
			if(null != dataBase){
				nDataBase = new NDataBase();
				nDataBase.fix(dataBase);
			}
			//初始化模板和站点对应关系
			List<Task> tasks = conf.getTasks();
			int length = tasks.size();
			for(int i =0;i<length;i++){
				List<Site> sites = tasks.get(i).getSiteList();
				for(Site site:sites){
					siteIdsAndSiteNames.put(site.getId(), site.getName());
					crawlerSites.add(site.getId());
					List<Template> templates = site.getTemplates();
					List<FetchTemplate> nts = new ArrayList<FetchTemplate>();
					for(Template t:templates){
						FetchTemplate nt = new FetchTemplate();
						nt.fix(t);
						nts.add(nt);
					}
					siteTemplateMap.put(site.getId(), nts);
					NSite nsite = new NSite();
					nsite.fix(site);
					siteMap.put(site.getId(), nsite);
					if(null != site.getSiteProxy()){
						mapSiteProxy.put(site.getId(), site.getSiteProxy());
					}
				}
			}
			//将搜索引擎编码放入站点ID中
			List<Engine> engines = interfaceObj.getEngines();
			nEngines.clear();
			for(Engine engine:engines){
				crawlerSites.add(engine.getEnginCode());
				NEngine nengine = new NEngine();
				nengine.fix(engine);
				//重置地址生成时间，保证启动搜索引擎以后，立刻就可以开始生成地址
				TaskHelper.setNEngineGenerateTime(nengine);
				nEngines.add(nengine);
				mapNEngine.put(nengine.getEnginCode(), nengine);
			}
			keywords = interfaceObj.getKeywords();
			if(conf.getRun()==1){
				new CrawlerService().start();//启动站点采集
			}
			if(conf.getWebRun()==1){
				new CrawlerServiceImpl().start();//启动全网采集
			}
		}
		System.out.println("*************开始初始化数据**************");
		System.out.println("**************************************");
		System.out.println("初始化搜索引擎个数："+nEngines.size()+"个！");
		System.out.println("初始化关键词个数："+keywords.size()+"个！");
		System.out.println("**************************************");
		System.out.println("*************结束初始化数据**************");
		//加载httpclient配置属性
		HttpClient3.configureClient();
	}
	/**
	 * 根据站点ID获取模板解析配置
	 * 
	 * @param siteId
	 * @return
	 */
	public List<FetchTemplate> findFetchTemplateBeanBySiteId(String siteId) {
		return siteTemplateMap.get(siteId);
	}
	/**
	 * 根据ID获取站点的配置
	 * @param id
	 * @return
	 */
	public NSite getSiteById(String siteId) {
		NSite nsite = siteMap.get(siteId);
		return nsite;
	}
	

	/**
	 * 初始化等待时间配置
	 */
	public synchronized void initWaitRange() {
		URL serviceUrl = null;
		try {
			serviceUrl = new URL(AppConf.get().get("dispatcher.webservice.url"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		ServerCrawlerServiceImplService service = new ServerCrawlerServiceImplService(serviceUrl, new QName("http://www.xd-tech.com", "ServerCrawlerServiceImplService"));
		IServerCrawlerService interfaceObj = service.getServerCrawlerServiceImplPort();
		List<TimeRange> ranges = interfaceObj.getWaitTimeRange();
		if (ranges != null && !ranges.isEmpty()) {
			this.ranges = ranges.toArray(new TimeRange[ranges.size()]);
		} else {
			this.ranges = new TimeRange[] {};
		}
	}
	/**
	 * 休眠时间段
	 * 
	 * @return
	 */
	public TimeRange[] getWaitRange() {
		return this.ranges;
	}
	/**
	 * 判断采集的地址是否在可以采集的站点中
	 * @param siteId
	 * @return
	 */
	public boolean checkCrawlerSites(String siteId){
		return crawlerSites.contains(siteId);
	}

}
