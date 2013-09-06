package com.xdtech.platform.webcrawler.protocol;

import java.util.List;
import com.xdtech.platform.webcrawler.dbcash.WebCrawlerUrlManager;
import com.xdtech.platform.webcrawler.ws.webdb.UrlInfoCrawler;

public class HtmlFetcher {
	/**
	 * 新生成的URL入库
	 */
	public void addOutLinks(List<UrlInfoCrawler> urlInfos){
		try {
			if (null != urlInfos && !urlInfos.isEmpty()) {
				WebCrawlerUrlManager.addWebDb(urlInfos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
