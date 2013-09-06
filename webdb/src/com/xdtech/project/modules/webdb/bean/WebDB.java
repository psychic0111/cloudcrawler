package com.xdtech.project.modules.webdb.bean;

import java.util.Date;
import java.util.Properties;

import com.xdtech.project.util.MD5Util;

/**
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class WebDB {
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
	/** URL岁代表的资源类型 */
	private int urlType;
	/**来源URL*/
	private String refurl;
	
	//搜索引擎属性
	/**搜索引擎编码*/
	private String engine;
	/** 发布时间 */
	private Date publishDate;
	/** 标题 */
	private String title;
	/** 作者 */
	private String author;
	/** 来源 */
	private String source;
	/** 时长 */
	private String timeLong;
	/** 图片地址 */
	private String imageAddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url == null ? "" : url;
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
		return siteid == null ? "" : siteid;
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

	public String getMd5() {
		if (md5 == null) {
			md5 = MD5Util.MD5(getUrl());
		}
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public int getUrllevel() {
		return urllevel;
	}

	public void setUrllevel(int urllevel) {
		this.urllevel = urllevel;
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
	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}
	/**
	 * 转换成要保存到BDB中的数据类型
	 * 
	 * @return
	 */
	public Properties toProperties(String tablename) {
		Properties properties = new Properties();
		properties.setProperty("id", this.getId());
		properties.setProperty("urllevel", getUrllevel() + "");
		properties.setProperty("tablename", tablename);
		properties.setProperty("key", getMd5());
		return properties;
	}
}
