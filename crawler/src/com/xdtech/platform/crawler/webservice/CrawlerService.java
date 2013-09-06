package com.xdtech.platform.crawler.webservice;

import java.util.ArrayList;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.jws.WebService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.xdtech.platform.crawler.CrawlerStatus;
import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.StartCrawler;
import com.xdtech.platform.crawler.control.StopCrawler;
import com.xdtech.platform.crawler.control.bean.FetchTemplate;
import com.xdtech.platform.crawler.parser.Outlink;
import com.xdtech.platform.crawler.parser.Parse;
import com.xdtech.platform.crawler.parser.Parser;
import com.xdtech.platform.crawler.parser.ParserFactory;
import com.xdtech.platform.crawler.parser.PropertiesParser;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.HtmlFetcher;
import com.xdtech.platform.crawler.protocol.ProtocolOutput;
import com.xdtech.platform.crawler.webservice.bean.CrawlerConnectionStatusManage;
import com.xdtech.platform.crawler.webservice.bean.ParseResult;
import com.xdtech.platform.crawler.webservice.bean.WebServiceProperties;

@WebService(endpointInterface = "com.xdtech.platform.crawler.webservice.CrawlerServiceInterface", targetNamespace = "http://www.xd-tech.com")
public class CrawlerService implements CrawlerServiceInterface {
	private String tableName = "crawler";
	/**
	 * 启动
	 */
	public void start() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				StartCrawler.runCrawler(tableName);
			}
		});
		t.start();
	}

	/**
	 * 停止爬虫
	 */
	public void stop() {
		StopCrawler.stopCrawler();
	}

	/**
	 * 爬虫状态
	 * 
	 * @return 爬虫状态 1:停止 2：运行 3:停止中
	 */
	public String findRunStatus() {
		return com.xdtech.platform.crawler.CrawlerStatus.RUNSTATUS;
	}

	/**
	 * 解析一个URL
	 * 
	 * @param url
	 * @return
	 */
	public ParseResult parser(String tableName,String url, FetchTemplate fetchTemplate) {
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		List<WebServiceProperties> blocks = new ArrayList<WebServiceProperties>();
		HtmlFetcher fetcher = new HtmlFetcher();
		FetchEntry fle = new FetchEntry();
		fle.setOldUrl(url);
		fle.setUrl(url);
		fle.setLevel(1);
		ProtocolOutput output = fetcher.request(url, fle, 0);
		if (output != null && output.getContent() != null && output.getContent().getContent() != null && output.getContent().getContent().length > 0) {
			Parser parser = ParserFactory.getParser(output, fle);
			Parse parse = parser.getParse(tableName,output.getContent(), fle);
			if (parse.getData() != null) {
				String content = parse.getText();
				Document doc = Jsoup.parse(content);
				List<FetchTemplate> fetchTemplateList = new ArrayList<FetchTemplate>();
				fetchTemplateList.add(fetchTemplate);
				Properties properties = PropertiesParser.parse(content, fle, doc, fetchTemplateList);// 解析基本规则
				//获取分页信息
				Outlink[] outlinks = PropertiesParser.findPageUrl(content, fle, doc, fetchTemplateList,1);// 找到分页
				StringBuilder sbUrls = new StringBuilder();
				if (outlinks != null) {
					for (Outlink outlink : outlinks) {
						if (sbUrls.length() > 0) {
							sbUrls.append("\n");
						}
						sbUrls.append(outlink.getUrl());
					}
				}
				//获取关联信息
				Outlink[] retiveOutlinks = PropertiesParser.findPageUrl(content, fle, doc, fetchTemplateList,2);// 找到分页
				StringBuilder retiveSbUrls = new StringBuilder();
				if (retiveOutlinks != null) {
					for (Outlink outlink : retiveOutlinks) {
						if (retiveSbUrls.length() > 0) {
							retiveSbUrls.append("\n");
						}
						retiveSbUrls.append(outlink.getUrl());
					}
				}
				List<Properties> blockVals = PropertiesParser.parseRelationRule(content, fle, doc, fetchTemplateList);// 解析关联规则
				if (blockVals != null && !blockVals.isEmpty()) {
					for (Properties blockPro : blockVals) {
						if (blockPro != null && !blockPro.isEmpty()) {
							List<String> blockKeys = new ArrayList<String>();
							List<String> blockValues = new ArrayList<String>();
							Set<Object> keyset = blockPro.keySet();
							for (Object obj : keyset) {
								if (obj != null) {
									blockKeys.add(obj.toString());
									blockValues.add(blockPro.getProperty(obj.toString(), ""));
								}
							}
							WebServiceProperties blockProperties = new WebServiceProperties(blockKeys.toArray(new String[0]), blockValues.toArray(new String[0]));
							blocks.add(blockProperties);
						}
					}
				}
				if (properties != null) {
					properties.setProperty("xdpageurls", sbUrls.toString());
					properties.setProperty("xdretiveurls", retiveSbUrls.toString());
					Set<Object> keyset = properties.keySet();
					for (Object obj : keyset) {
						if (obj != null) {
							keys.add(obj.toString());
							values.add(properties.getProperty(obj.toString(), ""));
						}
					}
				}

			}
		}
		String[][] result = new String[2][];
		result[0] = keys.toArray(new String[keys.size()]);
		result[1] = values.toArray(new String[values.size()]);
		WebServiceProperties base = new WebServiceProperties(result[0], result[1]);
		ParseResult parseResult = new ParseResult();
		parseResult.setBase(base);
		parseResult.setBlocks(blocks);
		return parseResult;
	}

	/**
	 * 获取当前爬虫的所有即时状态
	 * 
	 * @return
	 */
	public String[][] findAllCrawlerStatus() {
		long total = CrawlerStatus.getTotal();// 抓取页面数量
		int threadCount = CrawlerStatus.getCrawlThreadNum();// 当前正在运行的线程数
		String flow = CrawlerStatus.getFlow().toString();// 当前流量
		String[][] result = new String[2][];
		result[0] = new String[] {"total", "threadCount", "flow"};
		result[1] = new String[] {String.valueOf(total), String.valueOf(threadCount), flow};
		return result;
	}

	public Integer getCrawlerConnectionStatus() {
		return CrawlerConnectionStatusManage.crawlerConnectionStatus;
	}

	public void setCrawlerConfig() {
		// TODO Auto-generated method stub
		NConfigManager.setCrawlerNull();
		NConfigManager.getInstance().initCrawlerConfig();
	}
}
