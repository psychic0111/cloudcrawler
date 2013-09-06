package com.xdtech.platform.webcrawler.control;

import com.xdtech.platform.webcrawler.ControlStatus;
import com.xdtech.platform.webcrawler.CrawlerStatus;

/**
 * 停止全网监控爬虫
 * 
 * @author WangWei
 * 2013-07-01
 */
public class StopCrawler {
	public synchronized static void stopCrawler() {
		ControlStatus.run = false;
		CrawlerStatus.RUNSTATUS = "1";
	}
}
