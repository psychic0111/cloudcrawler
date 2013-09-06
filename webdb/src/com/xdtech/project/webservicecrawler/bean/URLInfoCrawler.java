package com.xdtech.project.webservicecrawler.bean;

import java.util.Date;

import com.xdtech.project.util.MD5Util;

public class URLInfoCrawler {
	private String url;
	private int level;
	private String siteId;
	private int urlType;
	private String refurl;
	//全网监控属性
	
	/** 搜索引擎代码 */
	private String searchEngine;
	/** 发布时间 */
	private Date publishTime;
	/** 标题 */
	private String title;
	/** 作者 */
	private String author;
	/** 来源 */
	private String source;
	/** 时长 */
	private String timeLong;
	/** 图片地址 */
	private String imagePath;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public int getUrlType() {
		return urlType;
	}

	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}

	public String md5String() {
		if (url != null) {
			return MD5Util.MD5(url);
		}
		return null;
	}

	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}
	public String getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTimeLong() {
		return timeLong;
	}

	public void setTimeLong(String timeLong) {
		this.timeLong = timeLong;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
