package com.xdtech.platform.crawler.control.bean;

import java.util.List;

import com.xdtech.platform.crawler.ws.dispatcher.CrawlerConfig;

/**
 * 爬虫配置
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class CrawlerConf {
	/** 名称 name */
	private String name;
	/** 代码 code */
	private String code;
	/** IP地址 */
	private String ip;
	/** 端口 port */
	private int port;
	/** 爬虫所使用的地址库 */
	private String[] webdbs;
	/** 采集线程 crawlerThread */
	private int crawlerThread;
	/** 下载线程 downloadThread */
	private int downloadThread;
	/** 单站点线程 siteThread */
	private int siteThread;
	/** 下载文件保存跟目录 filePath */
	private String filePath;
	/** 爬虫使用状态 0:启用 1:禁用*/
	private int useState;
	/** 爬虫采集的地址等级 */
	private int[] urlLevel;

	/**
	 * 默认构造方法
	 */
	public CrawlerConf() {
	}

	/**
	 * 通过配置对象赋值
	 * 
	 * @param config
	 */
	public CrawlerConf(CrawlerConfig config) {
		setName(config.getName());
		setCode(config.getCode());
		setIp(config.getIp());
		setPort(config.getPort());
		setWebdbs(config.getWebdbs() != null ? config.getWebdbs().toArray(new String[0]) : null);
		setCrawlerThread(config.getCrawlerThread());
		setDownloadThread(config.getDownloadThread());
		setSiteThread(config.getSiteThread());
		setFilePath(config.getFilePath());
		setUseState(config.getUseState());
		List<Integer> urlLevelList = config.getUrlLevel();
		int[] urlLevel = null;
		if (urlLevelList != null) {
			urlLevel = new int[urlLevelList.size()];
			for (int i = 0; i < urlLevel.length; i++) {
				urlLevel[i] = urlLevelList.get(i);
			}
		}
		setUrlLevel(urlLevel);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}

	public String getIp() {
		return ip;
	}

	private void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	private void setPort(int port) {
		this.port = port;
	}

	public String[] getWebdbs() {
		return webdbs;
	}

	private void setWebdbs(String[] webdbs) {
		this.webdbs = webdbs;
	}

	public int getCrawlerThread() {
		return crawlerThread;
	}

	private void setCrawlerThread(int crawlerThread) {
		this.crawlerThread = crawlerThread;
	}

	public int getDownloadThread() {
		return downloadThread;
	}

	private void setDownloadThread(int downloadThread) {
		this.downloadThread = downloadThread;
	}

	public int getSiteThread() {
		return siteThread;
	}

	private void setSiteThread(int siteThread) {
		this.siteThread = siteThread;
	}

	public String getFilePath() {
		return filePath;
	}

	private void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getUseState() {
		return useState;
	}

	private void setUseState(int useState) {
		this.useState = useState;
	}

	public int[] getUrlLevel() {
		return urlLevel;
	}

	private void setUrlLevel(int[] urlLevel) {
		this.urlLevel = urlLevel;
	}

}
