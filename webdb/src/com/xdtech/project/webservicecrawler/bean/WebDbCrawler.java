package com.xdtech.project.webservicecrawler.bean;

import java.util.Date;

/**
 * 地址库数据
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class WebDbCrawler {
	/** 主键 */
	private String id;
	/** url */
	private String url;
	/** 状态 0：未抓取 1：抓去成功 2：失败次数小于6 3：抓取失败 */
	private int status;
	/** 抓取次数 */
	private int fcount;
	/** 入库时间 */
	private Date intime;
	/** 采集时间 */
	private Date ftime;
	/** 站点ID */
	private String siteid;
	/** 下次抓取时间 */
	private Date nftime;
	/** url的级别 */
	private int urllevel;
	/** MD5值 */
	private String md5;
	/** 数据表名称 */
	private String dbname;
	/** URL类型 */
	private int urlType;
	/** 来源URL */
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFcount() {
		return fcount;
	}

	public void setFcount(int fcount) {
		this.fcount = fcount;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public Date getFtime() {
		return ftime;
	}

	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public Date getNftime() {
		return nftime;
	}

	public void setNftime(Date nftime) {
		this.nftime = nftime;
	}

	public int getUrllevel() {
		return urllevel;
	}

	public void setUrllevel(int urllevel) {
		this.urllevel = urllevel;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public int getUrlType() {
		return urlType;
	}

	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}

	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}
}
