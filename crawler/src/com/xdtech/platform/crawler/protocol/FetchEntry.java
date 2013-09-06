package com.xdtech.platform.crawler.protocol;

public class FetchEntry implements Cloneable {
	/** 要抓取的URL */
	private String url;
	/** 站点ID */
	private String siteId;
	/** MD5值 */
	private String md5;
	/** URL等级 */
	private int level;
	/** URL類型 */
	private int urlType;
	/** 数据ID */
	private String dataId;
	/** 地址库名称 */
	private String webdb;
	/** 原本的URL */
	private String oldUrl;
	/** 对于页面有base标签的 */
	private String baseUrl;
	/** 上级来源页面URL */
	private String refurl;
	/** 文章标题 */
	private String title;
	/** 搜索引擎编码 */
	private String searchEngine;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUrlType() {
		return urlType;
	}

	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getWebdb() {
		return webdb;
	}

	public void setWebdb(String webdb) {
		this.webdb = webdb;
	}

	public String getOldUrl() {
		return oldUrl;
	}

	public void setOldUrl(String oldUrl) {
		this.oldUrl = oldUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}

	public FetchEntry shallowClone() {
		try {
			return (FetchEntry) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return this;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}
}
