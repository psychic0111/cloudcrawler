package com.xdtech.platform.webcrawler.task;





import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.nbean.NEngine;
import com.xdtech.platform.crawler.parser.PageParser;
import com.xdtech.platform.crawler.parser.ParserUtil;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.ProtocolOutput;
import com.xdtech.platform.util.RegularSelectHelper;
import com.xdtech.platform.webcrawler.CrawlerStatus;
import com.xdtech.platform.webcrawler.ControlStatus;
import com.xdtech.platform.webcrawler.protocol.HtmlFetcher;
import com.xdtech.platform.webcrawler.ws.webdb.UrlInfoCrawler;
import com.xdtech.platform.crawler.task.TaskHelper;
import com.xdtech.platform.crawler.ws.ndispatcher.Crawler;
import com.xdtech.platform.crawler.ws.ndispatcher.Keyword;

/**
 * 全网监控爬虫-下载模块
 * 
 * @author WangWei
 * 2013-07-02
 */
public class XDWebCrawlerUrlGenerateTask extends Thread{
	/** 线程名称前缀 */
	private static final String THREAD_GROUP_NAME = "XDWebCrawlerUrlGenerateTask-";
	/** 线程组 */
	private ThreadGroup group = new ThreadGroup(THREAD_GROUP_NAME);
	
	/** 爬虫配置 */
	public Crawler crawler = null;
	/** 搜索引擎列表 */
	public List<NEngine> nEngines = null;
	/** 搜索引擎列表-临时数据 */
	public List<NEngine> tempNEngines = new ArrayList<NEngine>();
	/**
	 * 更新搜索引擎的更新时间
	 */
	private Map<String, NEngine> nEngineMap = new HashMap<String, NEngine>();
	/** 关键词列表 */
	public List<Keyword> keywords = null;
	private Integer currentEngine = 0;
	/**
	 * 获取一个搜索引擎配置,并且将该搜索引擎配置移动到临时移动搜索引擎列表，
	 * 当搜索引擎列表都完成一轮地址分析以后，在将临时列表中的搜索引擎放入搜索引擎列表中
	 * @return
	 */
	public synchronized NEngine getNEngine(){
		NEngine engine = null;
		if(nEngines.size() > 0){
			engine = nEngines.remove(0);
			tempNEngines.add(engine);
		}
		if(null == engine){
			resetEngines();
			engine = nEngines.remove(0);
			tempNEngines.add(engine);
		}
		return engine;
	}
	/**
	 * 当完成一轮地址分析以后，将搜索引擎重置一下
	 */
	public synchronized void resetEngines(){
		nEngines.addAll(tempNEngines);
		tempNEngines.clear();
	}
	
	public void run() {
		try {
			Thread.sleep(1000 * 60 * 1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		this.crawler = NConfigManager.getCrawler();
		this.nEngines = NConfigManager.getNEngines();
		this.keywords = NConfigManager.getKeywords();
		for(NEngine nEngine:nEngines){
			nEngineMap.put(nEngine.getEnginCode(), nEngine);
		}
		int length = nEngines.size();
		for(int i =0;i<length;i++){
			FetcherThread thread = new FetcherThread(THREAD_GROUP_NAME + i);
			thread.start();
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
	/**
	 * 根据搜索引擎的模板，解析出入口地址
	 * @author Administrator
	 *
	 */
	private class FetcherThread extends Thread {
		/**
		 * 开始采集时间
		 */
		public FetcherThread(String name) {
			super(group, name);
		}
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(ControlStatus.run){
					NEngine engine = getNEngine();
					if(engine==null){
						continue;
					}
					CrawlerStatus.WebCrawlerGenerateRunStatus = "2";
					if( null != engine && TaskHelper.checked(engine)){
						engine.setUrlGenerateDate(new Date());
						engine = nEngineMap.get(engine.getEnginCode());
						//生成入口地址
						Map<NEngine,List<String>> map  = generateEnterAddress(engine);
						//根据入口地址和搜索引擎配置规则生成详细地址,并保存到地址库
						generateDetailAddress(map);
						//修改搜索引擎的更新时间
						nEngineMap.put(engine.getEnginCode(), engine);
					}
				}
			}
		}
		/**
		 * 根据搜索引擎配置信息获取入口地址
		 * @param engines
		 */
		public Map<NEngine,List<String>> generateEnterAddress(NEngine engine){
			Map<NEngine,List<String>> map = new HashMap<NEngine, List<String>>();
			List<String> enterAddresses = new ArrayList<String>();
			if(null != engine){
				for (int j = 0; j < keywords.size(); j++) {
					Keyword keyword = keywords.get(j);
					// 由于多个关键词之间用空格分开，需要使用空格将关键词分成多个，组成新的url
					String[] words = keyword.getWords().split(" ");
					for (String word : words) {
						int page = engine.getPages();
						String pageurl = null;
						for (int p = 1;p<= page; p++) {
							pageurl = engine.getPageUrl().replace("{keyword}", word);
							pageurl = pageurl.replace("{page}", p+ "");
							if(null != engine.getPerPageCount()){
								pageurl = pageurl.replace("{pageSize}", engine.getPerPageCount()+ "");
								pageurl = pageurl.replace("{(page-1)*pageSize}", ((p-1)*engine.getPerPageCount()) + "");
							}
							if(null != pageurl){
								enterAddresses.add(pageurl);
							}
						}
					}
				}
				/*System.out.println("入口地址名称："+engine.getEnginName()+"\t入口地址规则为："+engine.getPageUrl()+"\t共解析地址："+enterAddresses.size()+"个！");
				System.out.println("**********************************");
				for(String s:enterAddresses){
					System.out.println(s);
				}*/
				currentEngine = currentEngine + 1;
			}
			map.put(engine, enterAddresses);
			return map;
		}
		/**
		 * 根据入口地址生成详细地址
		 * @param enterAddresses
		 */
		public void generateDetailAddress(Map<NEngine,List<String>> map){
			HtmlFetcher webCrawlerhtmlFetcher = new HtmlFetcher();
			for(Entry<NEngine,List<String>> entry:map.entrySet()){
				//获取搜索引擎配置和入口地址
				NEngine engine = entry.getKey();
				List<String> enterAddress = entry.getValue();
				//根据搜索引擎配置和入口地址生成详细地址
				for(String s:enterAddress){
					List<UrlInfoCrawler> allUrls = new ArrayList<UrlInfoCrawler>();
					com.xdtech.platform.crawler.protocol.HtmlFetcher htmlFetcher = new com.xdtech.platform.crawler.protocol.HtmlFetcher();
					FetchEntry fle = new FetchEntry();
					fle.setUrl(s);
					ProtocolOutput outPut = htmlFetcher.request(s, fle, 0);
					String html = "";
					try {
						String encode = PageParser.findcharset(outPut.getContent(), fle);
						html = new String(outPut.getContent().getContent(),encode);
					}catch (Exception e) {
						e.printStackTrace();
					}
					//如果下载的页面不为空，则根据搜索引擎规则进行集成生成详细地址
					if(!"".equals(html)){
						Document doc = Jsoup.parse(html);
						List<UrlInfoCrawler> oneUrls = new ArrayList<UrlInfoCrawler>();
						String fetchHtml = html;
						//根据列表规则找到地址
						if(null != engine.getListBlock() && !"".equals(engine.getListBlock())){
							fetchHtml = RegularSelectHelper.getValue(html, doc, engine.getItemBlock(),false);
						}
						oneUrls = findBlock(engine, doc, fetchHtml, fle, s);
						//如果块规则为空，则取列表规则中的A标签
						if(oneUrls.isEmpty()){
							if(null != engine.getListBlock() && !"".equals(engine.getListBlock())){
								oneUrls = findByOthers(engine, doc, fetchHtml, fle, s);
							}
						}
						allUrls.addAll(oneUrls);
						webCrawlerhtmlFetcher.addOutLinks(allUrls);
					}
				}
			}
		}
		/**
		 * 如果没有找到列表规则和块最早，则自动获取url
		 * @param engine
		 * @param doc
		 * @param html
		 * @param fle
		 * @param s
		 * @return
		 */
		private List<UrlInfoCrawler> findByOthers(NEngine engine,Document doc,String html,FetchEntry fle,String s){
			List<UrlInfoCrawler> allUrls = new ArrayList<UrlInfoCrawler>();
			String blockValue = RegularSelectHelper.getValue(html, doc, engine.getListBlock(),false);
			doc = Jsoup.parseBodyFragment(blockValue);
			Set<String> urls = ParserUtil.findUrlFromA(doc, fle,null);
			for(String sa:urls){
				UrlInfoCrawler urlInfo = getUrlInfoCrawler(1,sa,s,engine.getEnginCode(),engine.getEnginCode());
				allUrls.add(urlInfo);
			}
			return allUrls;
		}
		/***
		 * 给全网监控地址初始化地址
		 * @param level
		 * @param url
		 * @param refurl
		 * @param siteId
		 * @param searchEngine
		 * @return
		 */
		private UrlInfoCrawler getUrlInfoCrawler(int level,String url,String refurl,String siteId,String searchEngine){
			UrlInfoCrawler urlInfo = new UrlInfoCrawler();
			urlInfo.setLevel(level);
			urlInfo.setUrl(url);
			urlInfo.setRefurl(refurl);
			urlInfo.setSiteId(siteId);
			urlInfo.setSearchEngine(searchEngine);
			return urlInfo;
		}
		/**
		 * 关键块规则找到地址
		 * @param engine
		 * @param doc
		 * @param html
		 * @param fle
		 * @param s
		 * @return
		 */
		public List<UrlInfoCrawler> findBlock(NEngine engine,Document doc,String html,FetchEntry fle,String s){
			List<UrlInfoCrawler> allUrls = new ArrayList<UrlInfoCrawler>();
			if(null != engine.getItemBlock() && !"".equals(engine.getItemBlock())){
				//根据快规则，找到对应的块
				Set<String> blocks = RegularSelectHelper.getValues(html, doc, engine.getItemBlock(),false);
				if(null != blocks){
					for(String block:blocks){
						doc = Jsoup.parseBodyFragment(block);
						Set<String> urls = ParserUtil.findUrlFromAFirst(doc, fle,null);
						UrlInfoCrawler urlInfo = getUrlInfoCrawler(1,urls.iterator().next(),s,engine.getEnginCode(),engine.getEnginCode());
						if( null != engine.getAuthorRule() && !engine.getAuthorRule().equals("")){
							String author = RegularSelectHelper.getValue(block, doc, engine.getAuthorRule(), true);
							urlInfo.setAuthor(author);
						}
						if( null != engine.getDurationRule() && !engine.getDurationRule().equals("")){
							String durationRule = RegularSelectHelper.getValue(block, doc, engine.getDurationRule(), true);
							urlInfo.setTimeLong(durationRule);
						}
						if( null != engine.getPublishTimeRule() && !engine.getPublishTimeRule().equals("")){
							String publishTimeString = RegularSelectHelper.getValue(block, doc, engine.getPublishTimeRule(), true);
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							try {
								Date publishDate = format.parse(publishTimeString);
								com.xdtech.platform.crawler.protocol.HtmlFetcher.initDate(urlInfo, publishDate);
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						allUrls.add(urlInfo);
					}
				}
			}
			return allUrls;
		}
		/**
		 * 将解析的详细地址，通过地址库的webservice接口，保存到地址库中
		 * @param detailAddresses
		 */
		public void keepDetailAddressToWebDb(List<String> detailAddresses){
			
		}
	}
}
