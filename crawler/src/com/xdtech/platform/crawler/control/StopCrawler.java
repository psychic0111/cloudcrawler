package com.xdtech.platform.crawler.control;

import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.CrawlerStatus;

public class StopCrawler {
	public synchronized static void stopCrawler() {
		ControlStatus.run = false;
		CrawlerStatus.RUNSTATUS = "1";
	}
	public static void main(String[] args) {
		stopCrawler();
		System.out.println("********");
		System.out.println("********");
		System.out.println("********");
		System.out.println("********");
	}
}
