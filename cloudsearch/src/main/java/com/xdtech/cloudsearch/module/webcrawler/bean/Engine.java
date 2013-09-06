package com.xdtech.cloudsearch.module.webcrawler.bean;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 搜索引擎
 * 
 * @author WangWei
 * 2013-06-20
 */
@Entity
@Table(name = "xd_crawler_engine")
public class Engine {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 搜索引擎编码
	 */
	private String enginCode;
	/**
	 * 搜索引擎名称
	 */
	private String enginName;
	/**
	 * 媒体类型
	 */
	private String mediaType;
	/**
	 * 内容类型
	 */
	private String contentType;
	/**
	 * 内容类型描述
	 */
	private String contentTypeDesc;
	/**
	 * 访问地址
	 */
	private String pageUrl;
	/**
	 * 列表区域
	 */
	private String listBlock;
	/**
	 * 块规则
	 */
	private String itemBlock;
	/**
	 * 原文地址抽取规则
	 */
	private String urlRule;
	/**
	 * 标题抽取规则
	 */
	private String titleRule;
	/**
	 * 作者抽取规则
	 */
	private String authorRule;
	/**
	 * 日期抽取规则
	 */
	private String publishTimeRule;
	/**
	 * 摘要抽取规则
	 */
	private String summary;
	/**
	 * 图片地址
	 */
	private String imagePath;
	/**
	 * 原文地址
	 */
	private String hostUrl;
	/**
	 * 采集页数
	 */
	private Integer pages;
	/**
	 * 间隔时间
	 */
	private Integer timeInterval;
	/**
	 * 是否启用 1 启用 0 禁用
	 */
	private Integer isUse;
	/**
	 * 是否访问详细页面 1 访问 0 不访问
	 */
	private Integer isVisiteUrl;
	/**
	 * 代理主键
	 */
	private String proxyId;
	/**
	 * 优先级
	 */
	private String crawlerLevel;
	/**
	 * 操作时间
	 */
	private Date operateTime;
	/**
	 * 操作人
	 */
	private String operateUser;
	/**
	 * 每页条数
	 */
	private Integer perPageCount;
	/**
	 * 时长（抓取视频是使用）
	 */
	private String durationRule;
	/**
	 * 执行频率方式 1 按照小时计算 2 按照分钟计算
	 */
	private Integer timeIntervalType;
	
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
	@Column(columnDefinition="varchar(50)")
	public String getEnginCode() {
		return enginCode;
	}
	public void setEnginCode(String enginCode) {
		this.enginCode = enginCode;
	}
	@Column(columnDefinition="varchar(50)")
	public String getEnginName() {
		return enginName;
	}
	public void setEnginName(String enginName) {
		this.enginName = enginName;
	}
	@Column(columnDefinition="varchar(50)")
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	@Column(columnDefinition="varchar(50)")
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	@Column(columnDefinition="varchar(2000)")
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	@Column(columnDefinition="varchar(500)")
	public String getListBlock() {
		return listBlock;
	}
	public void setListBlock(String listBlock) {
		this.listBlock = listBlock;
	}
	@Column(columnDefinition="varchar(500)")
	public String getItemBlock() {
		return itemBlock;
	}
	public void setItemBlock(String itemBlock) {
		this.itemBlock = itemBlock;
	}
	@Column(columnDefinition="varchar(1000)")
	public String getUrlRule() {
		return urlRule;
	}
	public void setUrlRule(String urlRule) {
		this.urlRule = urlRule;
	}
	@Column(columnDefinition="varchar(500)")
	public String getTitleRule() {
		return titleRule;
	}
	public void setTitleRule(String titleRule) {
		this.titleRule = titleRule;
	}
	@Column(columnDefinition="varchar(500)")
	public String getAuthorRule() {
		return authorRule;
	}
	public void setAuthorRule(String authorRule) {
		this.authorRule = authorRule;
	}
	@Column(columnDefinition="varchar(500)")
	public String getPublishTimeRule() {
		return publishTimeRule;
	}
	public void setPublishTimeRule(String publishTimeRule) {
		this.publishTimeRule = publishTimeRule;
	}
	@Column(columnDefinition="varchar(500)")
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Column(columnDefinition="varchar(500)")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Column(columnDefinition="varchar(500)")
	public String getHostUrl() {
		return hostUrl;
	}
	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public Integer getIsVisiteUrl() {
		return isVisiteUrl;
	}
	public void setIsVisiteUrl(Integer isVisiteUrl) {
		this.isVisiteUrl = isVisiteUrl;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	@Column(columnDefinition="varchar(32)")
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	@Column(columnDefinition="varchar(32)")
	public String getProxyId() {
		return proxyId;
	}
	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}
	@Column(columnDefinition="varchar(10)")
	public String getCrawlerLevel() {
		return crawlerLevel;
	}
	public void setCrawlerLevel(String crawlerLevel) {
		this.crawlerLevel = crawlerLevel;
	}
	public Integer getPerPageCount() {
		return perPageCount;
	}
	public void setPerPageCount(Integer perPageCount) {
		this.perPageCount = perPageCount;
	}
	public String getDurationRule() {
		return durationRule;
	}
	public void setDurationRule(String durationRule) {
		this.durationRule = durationRule;
	}
	public Integer getTimeIntervalType() {
		return timeIntervalType;
	}
	public void setTimeIntervalType(Integer timeIntervalType) {
		this.timeIntervalType = timeIntervalType;
	}
	@Transient
	public String getContentTypeDesc() {
		int index = Integer.parseInt(contentType);
		return ContentType.values()[index].toString();
	}
	public void setContentTypeDesc(String contentTypeDesc) {
		this.contentTypeDesc = contentTypeDesc;
	}
}
