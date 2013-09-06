package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 爬虫
 * 
 * @author WangWei
 * 2013-08-07
 */
@Entity
@Table(name = "xd_crawler_log")
public class CrawlerLog {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 爬虫代码
	 */
	private String crawlerCode;
	/**
	 * 站点ID
	 */
	private String siteId;
	/**
	 * 站点名称
	 */
	private String siteName;
	/**
	 * 地址
	 */
	private String url;
	/**
	 * 状态
	 */
	private Integer status; 
	/**
	 * 爬取时间
	 */
	private Date crawlerTime;
	/**
	 * 爬取次数
	 */
	private Integer count;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	@Id
	@Column(length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(columnDefinition="varchar(32)")
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	@Column(columnDefinition="varchar(50)")
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	@Column(columnDefinition="varchar(500)")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCrawlerTime() {
		return crawlerTime;
	}
	public void setCrawlerTime(Date crawlerTime) {
		this.crawlerTime = crawlerTime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Column(columnDefinition="varchar(50)")
	public String getCrawlerCode() {
		return crawlerCode;
	}
	public void setCrawlerCode(String crawlerCode) {
		this.crawlerCode = crawlerCode;
	}
	@Transient
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Transient
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
