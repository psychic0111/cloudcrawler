package com.xdtech.platform.webcrawler.webservice;

import javax.jws.WebService;

import com.xdtech.platform.crawler.CrawlerStatus;
import com.xdtech.platform.webcrawler.control.StopCrawler;
import com.xdtech.platform.webcrawler.control.StartCrawler;


/**
 * 全网监控爬虫-爬虫控制实现类
 * 
 * @author WangWei
 * 2013-07-03
 */
@WebService(endpointInterface = "com.xdtech.platform.webcrawler.webservice.ICrawlerService", targetNamespace = "http://www.xd-tech.com")
public class CrawlerServiceImpl implements ICrawlerService{

	public String findRunStatus() {
		String crawlerStatus = CrawlerStatus.RUNSTATUS;
		//爬虫未启动，返回信息：爬虫出现故障
		if(!crawlerStatus.equals("2")||!com.xdtech.platform.webcrawler.ControlStatus.run){
			return "2";
		}
		//全网监控地址库加载线程停止运行，返回信息：全网地址库加载线程出现故障
		String downloadStatus = com.xdtech.platform.webcrawler.CrawlerStatus.WebCrawlerDownloadRunStatus;
		if(downloadStatus.equals("1")){
			return "3";
		}
		//全网监控地址库生成线程停止运行，返回信息：全网监控地址库生成线程出现故障
		String generateStatus = com.xdtech.platform.webcrawler.CrawlerStatus.WebCrawlerGenerateRunStatus;
		if(generateStatus.equals("1")){
			return "4";
		}
		//爬虫正在运行，全网地址库加载线程正在运行，全网地址库生成线程正在运行，返回信息：全网正在抓取
		if((crawlerStatus+downloadStatus+generateStatus).equals("222")){
			return "1";
		}
		return "0";
	}
	private String tableName = "crawler";
	/**
	 * 启动
	 */
	public void start() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				StartCrawler.startCrawler();
			}
		});
		t.start();
	}

	public void stop() {
		StopCrawler.stopCrawler();
	}


}
