package com.xdtech.cloudsearch.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.ws.sitecrawler.CrawlerServiceInterface;
import com.xdtech.cloudsearch.ws.sitecrawler.CrawlerServiceService;
import com.xdtech.cloudsearch.ws.webcrawler.CrawlerServiceImplService;
import com.xdtech.cloudsearch.ws.webcrawler.ICrawlerService;

public class CrawlerClient {
	public static Map<String,CrawlerServiceInterface> serviceMap = new HashMap<String,CrawlerServiceInterface>();
	public static CrawlerServiceInterface getCrawlerServiceInterface(Crawler crawler) throws MalformedURLException{
		if(serviceMap.get(crawler.getCode())==null){
			URL serviceUrl;
			serviceUrl = new URL(crawler.getCrawlerAddress()+"/service/crawler?wsdl");
			CrawlerServiceService service = new CrawlerServiceService(serviceUrl, new QName("http://www.xd-tech.com", "CrawlerServiceService"));
			CrawlerServiceInterface serverCrawler = service.getCrawlerServicePort();
			serviceMap.put(crawler.getCode(), serverCrawler);
		}
		return serviceMap.get(crawler.getCode());
	}
}
