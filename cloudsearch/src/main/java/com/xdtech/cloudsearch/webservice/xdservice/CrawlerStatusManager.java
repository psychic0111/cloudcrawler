package com.xdtech.cloudsearch.webservice.xdservice;

import java.util.List;

import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerService;
import com.xdtech.cloudsearch.util.AppContextUtils;
import com.xdtech.cloudsearch.ws.client.CrawlerClient;

public class CrawlerStatusManager extends Thread{
	private CrawlerService crawlerService = AppContextUtils.getBean("crawlerService");;
	/**
	 * 当前活动的爬虫个数
	 */
	int activeCrawler = 0;
	/**
	 * 爬虫死掉次数
	 */
	private int maxConnects = 3;
	public CrawlerStatusManager(){
		this.setDaemon(true);
	}
	public void getCrawlerStatus(){
		List<Crawler>  crawlers = DataCache.getActiveCrawlerList();
		List<Crawler>  deathCrawlers = DataCache.getDetahCrawlerList();
		//监控活着的爬虫，并且监控它的状态
		StringBuffer sb = new StringBuffer("");
		StringBuffer normal = new StringBuffer("");
		StringBuffer towNormal = new StringBuffer("");
		CrawlerConnectionStatusManage.crawlerConnectionStatus = 1;
		boolean initCrawler = false;
		if (crawlers != null && !crawlers.isEmpty()) {
			int length = crawlers.size();
			for (int i = 0;i<length;i++) {
				normal.append(1);
				towNormal.append(2);
				Crawler cra = crawlers.get(i);
				try {
					if(cra.getAutoConnects() == maxConnects){
						cra.setIsDeath(0);
						cra.setCrawlerStatus(1);
						cra.setAutoConnects(0);
						crawlerService.update(cra);
						CrawlerConnectionStatusManage.crawlerConnectionStatus = 0;
						initCrawler = true;
					}else{
						Integer connectionStatus = CrawlerClient.getCrawlerServiceInterface(cra).getCrawlerConnectionStatus();
						sb.append(connectionStatus);
					}
					//System.out.println("调度端的状态："+connectionStatus);
				}catch(Exception e){
					Integer connects = cra.getAutoConnects();
					connects = connects + 1;
					System.out.println(cra.getName()+"死掉次数："+connects);
					cra.setAutoConnects(connects);
					crawlerService.update(cra);
					initCrawler = true;
				}
			}
			CrawlerConnectionStatusManage.crawlerConnectionStatus = 1;
			//获取爬虫的正常状态，如果所有爬虫都已经更新完毕，调度端进入正常状态
			if(normal.toString().equals(sb.toString())){
				CrawlerConnectionStatusManage.crawlerConnectionStatus = 1;
			}
			//通知所有的爬虫，系统更新已经完成，修改其运行状态为1
			if(towNormal.toString().equals(sb.toString())){
				CrawlerConnectionStatusManage.crawlerConnectionStatus = 2;
			}
		}
		
		if(null != deathCrawlers && deathCrawlers.size() > 0){
			int length = deathCrawlers.size();
			for (int i = 0;i<length;i++) {
				try{
					Crawler cra = deathCrawlers.get(i);
					Integer connectionStatus = CrawlerClient.getCrawlerServiceInterface(cra).getCrawlerConnectionStatus();
					if(connectionStatus == 1){
						cra.setIsDeath(1);
						cra.setCrawlerStatus(1);
						crawlerService.update(cra);
						crawlers.clear();
						CrawlerConnectionStatusManage.crawlerConnectionStatus = 0;
						initCrawler = true;
					}
				}catch(Exception e){
				}
			}
		}
		if(initCrawler){
			DataCache.initCrawler();
		}
	}

	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try{
				getCrawlerStatus();
			}catch(Exception e){
				e.printStackTrace();
			}
			if(CrawlerConnectionStatusManage.crawlerConnectionStatus == 0){
				CrawlerConfigManager.refreshInit();
			}
		}
	}
}
