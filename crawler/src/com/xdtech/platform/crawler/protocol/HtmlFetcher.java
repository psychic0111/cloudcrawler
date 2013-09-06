package com.xdtech.platform.crawler.protocol;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.CrawlerStatus;
import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.StartCrawler;
import com.xdtech.platform.crawler.control.nbean.NSite;
import com.xdtech.platform.crawler.dbcach.CrawlerLog;
import com.xdtech.platform.crawler.dbcach.DBService;
import com.xdtech.platform.crawler.dbcach.OneLevelUrlManager;
import com.xdtech.platform.crawler.dbcach.TwoLevelUrlManager;
import com.xdtech.platform.crawler.dbcach.TwoLevelUrlManager;
import com.xdtech.platform.crawler.logs.CrawlerLogs;
import com.xdtech.platform.crawler.parser.Outlink;
import com.xdtech.platform.crawler.parser.Parse;
import com.xdtech.platform.crawler.parser.ParseData;
import com.xdtech.platform.crawler.parser.Parser;
import com.xdtech.platform.crawler.parser.ParserFactory;
import com.xdtech.platform.crawler.process.ProcessSelector;
import com.xdtech.platform.crawler.task.NoticeHelper;
import com.xdtech.platform.crawler.ws.ndispatcher.TimeRange;
import com.xdtech.platform.crawler.ws.webdb.UrlInfo;
import com.xdtech.platform.util.AppConf;
import com.xdtech.platform.util.CrawlerUtil;
import com.xdtech.platform.webcrawler.dbcash.WebCrawlerUrlManager;
import com.xdtech.platform.webcrawler.ws.webdb.UrlInfoCrawler;

/**
 * 抓取类
 * 
 * @author zhangjianbing@msn.com
 */
public class HtmlFetcher{
	/** 线程名称前缀 */
	private static final String THREAD_GROUP_NAME = "HTMLFETCHER";
	/** 线程组 */
	private ThreadGroup group = new ThreadGroup(THREAD_GROUP_NAME);
	/** 线程数 */
	private static int fetcherThreadCount = 1;
	/** 资源跳转次数 */
	private static int REDIRCOUNT = AppConf.get().getInt("crawler.redirect.maxcount", 3);
	/** 时间格式化工具（HH:mm） */
	private static final DateFormat sdf = new SimpleDateFormat("HH:mm");
	/** 过滤的URL */
	static {

	}
	
	public HtmlFetcher() {
		if (NConfigManager.getInstance().getConf() != null) {
			fetcherThreadCount = NConfigManager.getInstance().getConf().getCrawlerThread();
		} else {
			fetcherThreadCount = 10;
		}
	}

	/**
	 * 用于添加同时下载个数
	 * 
	 * @param fle
	 */
	private void block(FetchEntry fle) {

	}

	/**
	 * 减少同时下载个数
	 * 
	 * @param fle
	 */
	private void unblock(FetchEntry fle) {

	}

	public void downLoad() {
		for (int i = 0; i < 20; i++) {
			FetcherThread thread = new FetcherThread(THREAD_GROUP_NAME + i);
			thread.start();
		}
		CrawlerStatus.RUNSTATUS = "2";
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
		CrawlerStatus.RUNSTATUS = "1";
	}

	private class FetcherThread extends Thread {
		public FetcherThread(String name) {
			super(group, name);
		}
		public void run() {
			while (true) {
				/*System.out.println("爬虫正在采集！");
				System.out.println("网站采集运行状态为：" + ControlStatus.run);
				System.out.println("全网监控采集状态为：" + com.xdtech.platform.webcrawler.ControlStatus.run);*/
				if(ControlStatus.run){
					CrawlerStatus.RUNSTATUS = "2";
					runSite();
				}
				if(com.xdtech.platform.webcrawler.ControlStatus.run){
					runCrawler();
				}
				//当爬虫和全网监控都停止时，线程进入休眠状态
				if(!ControlStatus.run && !com.xdtech.platform.webcrawler.ControlStatus.run){
					try {
						Thread.sleep(1000);
						continue;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (shuoldWait()) {
					try {
						Thread.sleep(20000);
						continue;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		/**
		 * 采集站点
		 */
		public void runSite(){
			String tableName = "crawler";
			try {
				FetchEntry fetchEntry = TwoLevelUrlManager.getNext();
				if (fetchEntry == null || fetchEntry.getUrl() == null || fetchEntry.getUrl().trim().length() == 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					String dataId = fetchEntry.getDataId();
					int level = fetchEntry.getLevel();
					try {
						ProtocolOutput out = request(fetchEntry.getUrl(), fetchEntry, 0);
						if (out != null && out.getContent() != null && out.getContent().getContent() != null) {
							int length = out.getContent().getContent().length;
							CrawlerStatus.addLength(length);
						}
						if(NConfigManager.getInstance().checkCrawlerSites(fetchEntry.getSiteId())){
							handleFetch(tableName,fetchEntry.getOldUrl(), fetchEntry, out);
							changeSiteStatus(out, fetchEntry);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					TwoLevelUrlManager.remove(dataId, level);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * 采集全网
		 */
		public void runCrawler(){
			String tableName = "webdb_webcrawler";
			try {
				com.xdtech.platform.webcrawler.protocol.FetchEntry fetchEntry = WebCrawlerUrlManager.getNext();
				if (fetchEntry == null || fetchEntry.getUrl() == null || fetchEntry.getUrl().trim().length() == 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					String dataId = fetchEntry.getDataId();
					int level = fetchEntry.getLevel();
					try {
						ProtocolOutput out = request(fetchEntry.getUrl(), fetchEntry, 0);
						if (out != null && out.getContent() != null && out.getContent().getContent() != null) {
							int length = out.getContent().getContent().length;
							CrawlerStatus.addLength(length);
						}
						if(NConfigManager.getInstance().checkCrawlerSites(fetchEntry.getSiteId())){
							handleFetch(tableName,fetchEntry.getOldUrl(), fetchEntry, out);
							changeCrawlerStatus(out, fetchEntry);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					WebCrawlerUrlManager.remove(dataId, level);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 是否应该等待采集,true:需要休眠 false：不需要休眠
		 * 
		 * @return
		 */
		public boolean shuoldWait() {
			String curTime = sdf.format(new Date());
			TimeRange[] configs = NConfigManager.getInstance().getWaitRange();
			if (configs == null || configs.length == 0) {
				return false;
			} else {
				String start = null;
				String end = null;
				for (TimeRange range : configs) {
					start = range.getStart();
					end = range.getEnd();
					if (start != null && end != null && start.compareTo(curTime) < 0 && end.compareTo(curTime) > 0) {
						return true;
					}
				}
			}
			return false;
		}

	}

	/**
	 * 暂时过滤一些URL
	 * 
	 * @param fle
	 * @return
	 */
	private boolean fetcherOk(FetchEntry fle) {
		return true;
	}

	/**
	 * 请求数据
	 * 
	 * @param fle
	 */
	public ProtocolOutput request(String oldURL, FetchEntry fle, int fetchCount) {
		String url = fle.getUrl() == null ? "" : fle.getUrl();
		fle.setOldUrl(oldURL);
		fetchUrlLog(url);
		String lurl = url.toLowerCase();
		if (lurl.endsWith(".rar") || lurl.endsWith(".zip") || lurl.endsWith(".iso") || lurl.endsWith(".flv") || lurl.endsWith(".avi") || lurl.endsWith(".rmvb") || lurl.endsWith(".rm") || lurl.endsWith(".asf") || lurl.endsWith(".mpg") || lurl.endsWith(".mpeg")
				|| lurl.endsWith(".mpe") || lurl.endsWith(".wmv") || lurl.endsWith(".mp4") || lurl.endsWith(".mkv") || lurl.endsWith(".vob")) {
			CrawlerStatus.addTotal(1);
			TwoLevelUrlManager.update(fle, 1);
			return null;
		}
		if (!fetcherOk(fle)) {
			CrawlerStatus.addTotal(1);
			TwoLevelUrlManager.update(fle, 1);
			return null;
		}
		Protocol protocol = ClientFactory.findAProtocol(url);
		block(fle);
		ProtocolOutput out = null;
		try {
			String nurl = changeUrl(fle.getUrl());
			fle.setUrl(nurl);
			out = protocol.getProtocolOutput(fle);
			if (!out.isMedia() && out.getStatus().getCode() == ProtocolStatus.MOVED) {
				if (fetchCount < REDIRCOUNT) {
					String newUrl = out.getStatus().getNewUrl();
					if (newUrl != null && newUrl.trim().length() > 0) {
						fle.setUrl(newUrl);
						out = request(oldURL, fle, fetchCount + 1);
					}
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		unblock(fle);
		return out;
	}

	/**
	 * 记录采集的URL的日志
	 * 
	 * @param url
	 */
	private void fetchUrlLog(String url) {
	}

	/**
	 * 对URL进行编码
	 * 
	 * @param url
	 * @param surl
	 * @return
	 */
	public static String changeUrl(String surl) {
		return CrawlerUtil.changeUrl(surl);
	}

	/**
	 * 预处理
	 * 
	 * @param fle
	 * @param output
	 */
	private void handleFetch(String tableName,String oldURL, FetchEntry fle, ProtocolOutput output) {
		if (output != null && output.getContent() != null && output.getContent().getContent() != null && output.getContent().getContent().length > 0) {
			Parser parser = ParserFactory.getParser(output, fle);
			Parse parse = parser.getParse(tableName,output.getContent(), fle);
			outputPage(tableName,oldURL, fle, output, parse);
		}
	}

	/**
	 * 写
	 */
	private void outputPage(String tableName,String oldURL, FetchEntry fle, ProtocolOutput output, Parse parse) {
		com.xdtech.platform.crawler.process.Process processor = ProcessSelector.getProcess(oldURL, fle, output, parse);
		processor.process(tableName,oldURL, fle, output, parse);// 数据处理
		if(tableName.equals("webdb_webcrawler")){
			addCrawlerOutLinks(tableName,fle, output, parse);// URL入库
		}else{
			addOutLinks(tableName,fle, output, parse);// URL入库
		}
		
	}

	/**
	 * 新生成的URL入库
	 */
	private void addOutLinks(String tableName,FetchEntry fle, ProtocolOutput output, Parse parse) {
		if (parse != null && parse.getData() != null) {
			ParseData data = parse.getData();
			Outlink[] internallinks = data.getInternallinks();
			List<UrlInfo> list = new ArrayList<UrlInfo>();
			if (internallinks != null && internallinks.length > 0) {
				for (Outlink outlink : internallinks) {
					UrlInfo info = new UrlInfo();
					info.setUrl(outlink.getUrl());
					info.setLevel(outlink.getLevel());
					info.setSiteId(fle.getSiteId());
					info.setUrlType(outlink.getType());
					info.setRefurl(fle.getUrl());
					list.add(info);
				}
			}
			Outlink[] outlinks = data.getOutlinks();
			if (outlinks != null && outlinks.length > 0) {
				String siteId = fle.getSiteId();
				NSite site = NConfigManager.getInstance().getSiteById(siteId);// 根据ID获取站点配置
				Map<String, Pattern> mapPattern = new HashMap<String, Pattern>();// 规则对应的正则
				List<String> containRule = new ArrayList<String>();// 包含的规则
				List<String> rules = new ArrayList<String>();// 规则
				if (site != null) {
					String regRule = site.getUrlReg();
					if (regRule != null && regRule.trim().length() > 0) {
						String[] regRules = regRule.split("[ ]{1,}");
						if (regRules != null) {
							for (String rule : regRules) {
								if (rule != null && rule.trim().length() > 0) {
									rules.add(rule);
									if (rule.startsWith("~")) {
										try {
											Pattern pattern = Pattern.compile(rule.substring(1), Pattern.CASE_INSENSITIVE);
											mapPattern.put(rule, pattern);
										} catch (Exception e) {
											e.printStackTrace();
										}
									} else {
										containRule.add(rule);
									}
								}
							}
						}
					}
				}
				for (Outlink outlink : outlinks) {
					String url = outlink.getUrl();
					boolean isOk = false;
					if (!rules.isEmpty()) {
						for (String rule : rules) {
							if (!rule.startsWith("~")) {
								if (url.contains(rule)) {
									isOk = true;
									break;
								}
							} else {
								Pattern pattern = mapPattern.get(rule);
								Matcher matcher = pattern.matcher(url);
								isOk = matcher.find();
							}
						}
					} else {
						isOk = true;
					}
					if (isOk) {
						UrlInfo info = new UrlInfo();
						info.setUrl(url);
						info.setLevel(outlink.getLevel());
						info.setSiteId(fle.getSiteId());
						info.setUrlType(outlink.getType());
						info.setRefurl(fle.getUrl());
						list.add(info);
					}
				}
			}
			try {
				if (!list.isEmpty()) {
					TwoLevelUrlManager.addWebDb(tableName,list);
				}
			} catch (Exception e) {
			}
		}
	}
	/**
	 * 新生成的URL入库
	 */
	private void addCrawlerOutLinks(String tableName,FetchEntry sonFle, ProtocolOutput output, Parse parse) {
		com.xdtech.platform.webcrawler.protocol.FetchEntry fle = (com.xdtech.platform.webcrawler.protocol.FetchEntry)sonFle;
		if (parse != null && parse.getData() != null) {
			ParseData data = parse.getData();
			Outlink[] internallinks = data.getInternallinks();
			List<UrlInfoCrawler> list = new ArrayList<UrlInfoCrawler>();
			if (internallinks != null && internallinks.length > 0) {
				for (Outlink outlink : internallinks) {
					//全网抓取的链接，只抓取一级地址
					System.out.println("outlink.getLevel() "+outlink.getLevel());
					if("webdb_webcrawler".equals(tableName)){
						if(outlink.getLevel()>1){
							continue;
						}
					}
					UrlInfoCrawler info = new UrlInfoCrawler();
					info.setUrl(outlink.getUrl());
					info.setLevel(outlink.getLevel());
					info.setSiteId(fle.getSiteId());
					info.setUrlType(outlink.getType());
					info.setRefurl(fle.getUrl());
					
					info.setSearchEngine(fle.getSearchEngine());
					initDate(info,fle.getPublishTime());
					info.setAuthor(fle.getAuthor());
					info.setTimeLong(fle.getTimeLong());
					info.setImagePath(fle.getImagePath());
					info.setSource(fle.getSource());
					info.setTitle(fle.getTitle());
					list.add(info);
				}
			}
			Outlink[] outlinks = data.getOutlinks();
			if (outlinks != null && outlinks.length > 0) {
				String siteId = fle.getSiteId();
				NSite site = NConfigManager.getInstance().getSiteById(siteId);// 根据ID获取站点配置
				Map<String, Pattern> mapPattern = new HashMap<String, Pattern>();// 规则对应的正则
				List<String> containRule = new ArrayList<String>();// 包含的规则
				List<String> rules = new ArrayList<String>();// 规则
				if (site != null) {
					String regRule = site.getUrlReg();
					if (regRule != null && regRule.trim().length() > 0) {
						String[] regRules = regRule.split("[ ]{1,}");
						if (regRules != null) {
							for (String rule : regRules) {
								if (rule != null && rule.trim().length() > 0) {
									rules.add(rule);
									if (rule.startsWith("~")) {
										try {
											Pattern pattern = Pattern.compile(rule.substring(1), Pattern.CASE_INSENSITIVE);
											mapPattern.put(rule, pattern);
										} catch (Exception e) {
											e.printStackTrace();
										}
									} else {
										containRule.add(rule);
									}
								}
							}
						}
					}
				}
				for (Outlink outlink : outlinks) {
					String url = outlink.getUrl();
					boolean isOk = false;
					if (!rules.isEmpty()) {
						for (String rule : rules) {
							if (!rule.startsWith("~")) {
								if (url.contains(rule)) {
									isOk = true;
									break;
								}
							} else {
								Pattern pattern = mapPattern.get(rule);
								Matcher matcher = pattern.matcher(url);
								isOk = matcher.find();
							}
						}
					} else {
						isOk = true;
					}
					if (isOk) {
						UrlInfoCrawler info = new UrlInfoCrawler();
						info.setUrl(url);
						info.setLevel(outlink.getLevel());
						info.setSiteId(fle.getSiteId());
						info.setUrlType(outlink.getType());
						info.setRefurl(fle.getUrl());
						
						
						info.setSearchEngine(fle.getSearchEngine());
						if(null != fle.getPublishTime()){
							initDate(info,fle.getPublishTime());
						}
						info.setAuthor(fle.getAuthor());
						info.setTimeLong(fle.getTimeLong());
						info.setImagePath(fle.getImagePath());
						info.setSource(fle.getSource());
						info.setTitle(fle.getTitle());
						list.add(info);
					}
				}
			}
			try {
				if (!list.isEmpty()) {
					//WebCrawlerUrlManager.addWebDb(list);
				}
			} catch (Exception e) {
			}
		}
	}
	/**
	 * 转换发布时间
	 * @param userInfoCrawler
	 * @param date
	 */
	public static void initDate(UrlInfoCrawler userInfoCrawler,Date date){
		if(null == date){
			Calendar can=Calendar.getInstance();
			int year=can.get(Calendar.YEAR);
			int month=can.get(Calendar.MONTH)+1;
			int day=can.get(Calendar.DAY_OF_MONTH);
			int hour = can.get(Calendar.HOUR_OF_DAY);
			int minute = can.get(Calendar.MINUTE);
			userInfoCrawler.getPublishTime().setYear(year);
			userInfoCrawler.getPublishTime().setMonth(month);
			userInfoCrawler.getPublishTime().setDay(day);
			userInfoCrawler.getPublishTime().setHour(hour);
			userInfoCrawler.getPublishTime().setMinute(minute);
		}else{
			int year=date.getYear();
			int month=date.getMonth()+1;
			int day=date.getDay();
			int hour = date.getHours();
			int minute = date.getMinutes();
			userInfoCrawler.getPublishTime().setYear(year);
			userInfoCrawler.getPublishTime().setMonth(month);
			userInfoCrawler.getPublishTime().setDay(day);
			userInfoCrawler.getPublishTime().setHour(hour);
			userInfoCrawler.getPublishTime().setMinute(minute);
		}
	}
	/**
	 * 新生成的URL入库
	 */
	public void addOutLinks(String tableName,List<UrlInfo> urlInfos){
		try {
			if (!urlInfos.isEmpty()) {
				TwoLevelUrlManager.addWebDb(tableName,urlInfos);
				NoticeHelper.twoLevelDownloadNotice = true;
			}
		} catch (Exception e) {
		}
	}
	/**
	 * 修改状态-站点
	 * 
	 * @param out
	 * @param fetchEntry
	 */
	private void changeSiteStatus(ProtocolOutput out, FetchEntry fetchEntry) {
		
		if (out != null && out.getStatus() != null && out.getStatus().getCode() == ProtocolStatus.SUCCESS) {
			// 抓取成功
			CrawlerStatus.addTotal(1);
			TwoLevelUrlManager.update(fetchEntry, 1);
		} else if (out != null && out.getStatus() != null && out.getStatus().getCode() == ProtocolStatus.NOTFOUND) {
			// 资源未发现
			CrawlerStatus.addTotal(1);
			TwoLevelUrlManager.update(fetchEntry, 1);
		} else {
			// 抓取失败
			TwoLevelUrlManager.update(fetchEntry, 0);
		}
		OneLevelUrlManager.remove(fetchEntry.getDataId(), fetchEntry.getLevel());
		TwoLevelUrlManager.remove(fetchEntry.getDataId(), fetchEntry.getLevel());
		writeLog(out, fetchEntry);
	}
	/**
	 * 修改状态-全网监控
	 * 
	 * @param out
	 * @param fetchEntry
	 */
	private void changeCrawlerStatus(ProtocolOutput out, com.xdtech.platform.webcrawler.protocol.FetchEntry fetchEntry) {
		if (out != null && out.getStatus() != null && out.getStatus().getCode() == ProtocolStatus.SUCCESS) {
			// 抓取成功
			com.xdtech.platform.webcrawler.CrawlerStatus.addTotal(1);
			WebCrawlerUrlManager.update(fetchEntry, 1);
		} else if (out != null && out.getStatus() != null && out.getStatus().getCode() == ProtocolStatus.NOTFOUND) {
			// 资源未发现
			com.xdtech.platform.webcrawler.CrawlerStatus.addTotal(1);
			WebCrawlerUrlManager.update(fetchEntry, 1);
		} else {
			// 抓取失败
			WebCrawlerUrlManager.update(fetchEntry, 0);
		}
		WebCrawlerUrlManager.remove(fetchEntry.getDataId(), fetchEntry.getLevel());
		writeLog(out, fetchEntry);
	}
	/**
	 * 将日志写入数据库
	 * @param out
	 * @param fetchEntry
	 */
	public static void writeLog(ProtocolOutput out, FetchEntry fetchEntry){
		CrawlerLog log = new CrawlerLog();
		log.setId(UUID.randomUUID().toString().replace("-", ""));
		log.setCrawlerCode(NConfigManager.CRAWLER_CODE);
		log.setSiteId(fetchEntry.getSiteId());
		log.setSiteName(NConfigManager.getInstance().getSiteNameBySiteId(fetchEntry.getSiteId()));
		log.setUrl(fetchEntry.getUrl());
		log.setStatus((out != null && null != out.getStatus())?out.getStatus().getCode():-1);
		log.setCount(out.getCount());
		log.setCrawlerTime(out.getCrawlerDate());
		DBService.insertLog(log);
		//将该日志添加到文件日志管理器中
		CrawlerLogs.addCrawlerLog(log);
	}
}
