package com.xdtech.platform.crawler.parser;

import java.util.Date;

public class Data {
	/** 标题 */
	private String title;
	/** 发布时间 */
	private String issueDate;
	/** 抓取时间 */
	private String crawlDate;
	/** 新闻、博客、论坛、微博、qq-msn、RSS、其它 */
	private String ctype;
	/** 表现类型（html video picture other） */
	private String metaType;
	/** 作者 */
	private String author;
	/** 点击率 */
	private String hitcount;
	/** 评论数 */
	private String commentcount;
	/** 转发数 */
	private String replaycount;
	/** 源地址 */
	private String sourceurl;
	/** 站点分类 */
	private String sitecategory;
	/** 站点ID */
	private String siteid;
	/** 站点名称 */
	private String siteName;
	/** 媒体域名 */
	private String domain;
	/** 媒体类型（新闻、博客、论坛、微博、其他） */
	private String mediaType;
	/** 来源媒体区域类型 */
	private String mediaAreaType;
	/** 采集引擎（百度新闻、搜狐新闻...） */
	private String searchEngine;
	/** 国家 */
	private String country;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 区 */
	private String area;
	/** 行业 */
	private String bussiness;
	/** 1 正面 0 中性 -1 为负面 */
	private String pn;
	/** 人名 */
	private String pname;
	/** updatetime */
	private Date updatetime;
	/** 上级地址 */
	private String referer;
	/** weiboid */
	private String weiboid;
	/** userid */
	private String userid;
	/** useraccount */
	private String useraccount;
	/** nickname */
	private String nickname;
	/** toolname */
	private String toolname;
	/** receiveraccount */
	private String receiveraccount;
	/** receiveruserid */
	private String receiveruserid;
	/** receivernickname */
	private String receivernickname;
	/** receiverid */
	private String receiverid;
	/** copyfrom */
	private String copyfrom;
	/** summary */
	private String summary;
	/** keywords */
	private String keywords;
	/** 文章内容 */
	private String mainContent;
	/** 快照 */
	private String snapshot;
	/** 是否处理 */
	private int process;
	/** 进数据库的时间 */
	private Date intime;
	/**URL的MD5值*/
	private String urlmd5;
	/**xdaddtime*/
	private String xdaddtime;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCtype() {
		return this.ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getMetaType() {
		return this.metaType;
	}

	public void setMetaType(String metaType) {
		this.metaType = metaType;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSourceurl() {
		return this.sourceurl;
	}

	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}

	public String getSitecategory() {
		return this.sitecategory;
	}

	public void setSitecategory(String sitecategory) {
		this.sitecategory = sitecategory;
	}

	public String getSiteid() {
		return this.siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaAreaType() {
		return this.mediaAreaType;
	}

	public void setMediaAreaType(String mediaAreaType) {
		this.mediaAreaType = mediaAreaType;
	}

	public String getSearchEngine() {
		return this.searchEngine;
	}

	public void setSearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBussiness() {
		return this.bussiness;
	}

	public void setBussiness(String bussiness) {
		this.bussiness = bussiness;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getReferer() {
		return this.referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getWeiboid() {
		return this.weiboid;
	}

	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUseraccount() {
		return this.useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getToolname() {
		return this.toolname;
	}

	public void setToolname(String toolname) {
		this.toolname = toolname;
	}

	public String getReceiveraccount() {
		return this.receiveraccount;
	}

	public void setReceiveraccount(String receiveraccount) {
		this.receiveraccount = receiveraccount;
	}

	public String getReceiveruserid() {
		return this.receiveruserid;
	}

	public void setReceiveruserid(String receiveruserid) {
		this.receiveruserid = receiveruserid;
	}

	public String getReceivernickname() {
		return this.receivernickname;
	}

	public void setReceivernickname(String receivernickname) {
		this.receivernickname = receivernickname;
	}

	public String getReceiverid() {
		return this.receiverid;
	}

	public void setReceiverid(String receiverid) {
		this.receiverid = receiverid;
	}

	public String getCopyfrom() {
		return this.copyfrom;
	}

	public void setCopyfrom(String copyfrom) {
		this.copyfrom = copyfrom;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getCrawlDate() {
		return crawlDate;
	}

	public void setCrawlDate(String crawlDate) {
		this.crawlDate = crawlDate;
	}

	public String getMainContent() {
		return mainContent;
	}

	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	public String getHitcount() {
		return hitcount;
	}

	public void setHitcount(String hitcount) {
		this.hitcount = hitcount;
	}

	public String getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(String commentcount) {
		this.commentcount = commentcount;
	}

	public String getReplaycount() {
		return replaycount;
	}

	public void setReplaycount(String replaycount) {
		this.replaycount = replaycount;
	}

	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public String getUrlmd5() {
		return urlmd5;
	}

	public void setUrlmd5(String urlmd5) {
		this.urlmd5 = urlmd5;
	}
	public String getXdaddtime() {
		return xdaddtime;
	}

	public void setXdaddtime(String xdaddtime) {
		this.xdaddtime = xdaddtime;
	}

}
