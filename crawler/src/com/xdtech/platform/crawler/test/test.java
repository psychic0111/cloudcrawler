package com.xdtech.platform.crawler.test;

import com.xdtech.platform.crawler.CrawlerStatus;
import com.xdtech.platform.crawler.logger.LoggerWriter;
import com.xdtech.platform.crawler.protocol.HtmlFetcher;

public class test {
	public static void main(String[] args) {
		String url = "http://news.baidu.com/";
		final long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable() {

			public void run() {
				while (true) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					long now = System.currentTimeMillis();
					StringBuilder sbInfo = new StringBuilder();
					sbInfo.append("下载速度每秒：" + ((CrawlerStatus.getTotal() + 0.0D) / (((now - start) / 1000) + 0.0D)) + "页").append('\n');
					sbInfo.append("下载文件总数" + CrawlerStatus.getTotal()).append('\n');
					sbInfo.append("失败数：" + CrawlerStatus.failCount).append('\n');
					sbInfo.append("活动线程：" + CrawlerStatus.getCrawlThreadNum()).append('\n').append('\n');
					LoggerWriter.writeInfo(sbInfo.toString());
					System.out.println(sbInfo);
					System.out.println("==================================================================");
				}
			}
		});
		thread.start();

		Thread threadGC = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(600000 * 10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.gc();
				}
			}
		});
		threadGC.start();
		HtmlFetcher fetcher = new HtmlFetcher();
		fetcher.downLoad();
	}
}
