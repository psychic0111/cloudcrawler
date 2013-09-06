package com.xdtech.platform.crawler.ws.webdb;

import java.net.MalformedURLException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import com.xdtech.platform.util.AppConf;

/**
 * 地址库服务的客户端
 * 
 * @author zhangjianbing@msn.com
 */
public class WebDBServiceClient {
	private static WebDBServiceInterface interfaceProxy = null;

	/**
	 * 获取地址库webservice代理
	 * 
	 * @return
	 */
	public static WebDBServiceInterface getInterface() {
		if (interfaceProxy == null) {
			URL url;
			try {
				url = new URL(AppConf.get().get("webdb.webservice.url"));
				QName qName = new QName("http://www.xd-tech.com", "WebDBServiceService");
				WebDBServiceService service = new WebDBServiceService(url, qName);
				interfaceProxy = service.getWebDBServicePort();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return interfaceProxy;
	}

	/**
	 * 向地址库中添加地址
	 * 
	 * @param urls
	 * @param level
	 */
	public static void addUrlInWebDb(String tableName, String[] urls, int level, String siteId) {
		WebDBServiceInterface service = getInterface();
		if (urls != null && urls.length > 0) {
			if (service != null) {
				UrlInfo urlInfo = null;
				if (urls != null) {
					List<UrlInfo> urlList = new ArrayList<UrlInfo>(128);
					for (String url : urls) {
						urlInfo = new UrlInfo();
						urlInfo.setLevel(level);
						urlInfo.setSiteId(siteId);
						urlInfo.setUrl(url);
						urlList.add(urlInfo);
						if (urlList.size() >= 128) {
							try {
								if (level == 0) {
									service.addOrUpdateURLs(tableName, urlList);
								} else {
									service.addUrl(tableName, urlList);
								}
							} catch (Exception_Exception e) {
								e.printStackTrace();
							}
							urlList.clear();
						}
					}
					if (urlList.size() > 0) {
						try {
							if (level == 0) {
								service.addOrUpdateURLs(tableName, urlList);
							} else {
								service.addUrl(tableName, urlList);
							}
						} catch (Exception_Exception e) {
							e.printStackTrace();
						}
						urlList.clear();
					}
				}
			}
		}
	}
}
