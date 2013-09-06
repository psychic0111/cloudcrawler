package com.xdtech.platform.crawler.control.nbean;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xdtech.platform.crawler.ws.ndispatcher.Engine;
import com.xdtech.platform.crawler.ws.ndispatcher.Task;

public class NEngine {
	
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
	/**
	 * 最后一次执行任务的时间
	 */
	private Date urlGenerateDate; 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEnginCode() {
		return enginCode;
	}
	public void setEnginCode(String enginCode) {
		this.enginCode = enginCode;
	}
	public String getEnginName() {
		return enginName;
	}
	public void setEnginName(String enginName) {
		this.enginName = enginName;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getListBlock() {
		return listBlock;
	}
	public void setListBlock(String listBlock) {
		this.listBlock = listBlock;
	}
	public String getItemBlock() {
		return itemBlock;
	}
	public void setItemBlock(String itemBlock) {
		this.itemBlock = itemBlock;
	}
	public String getUrlRule() {
		return urlRule;
	}
	public void setUrlRule(String urlRule) {
		this.urlRule = urlRule;
	}
	public String getTitleRule() {
		return titleRule;
	}
	public void setTitleRule(String titleRule) {
		this.titleRule = titleRule;
	}
	public String getAuthorRule() {
		return authorRule;
	}
	public void setAuthorRule(String authorRule) {
		this.authorRule = authorRule;
	}
	public String getPublishTimeRule() {
		return publishTimeRule;
	}
	public void setPublishTimeRule(String publishTimeRule) {
		this.publishTimeRule = publishTimeRule;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
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
	public String getProxyId() {
		return proxyId;
	}
	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}
	public String getCrawlerLevel() {
		return crawlerLevel;
	}
	public void setCrawlerLevel(String crawlerLevel) {
		this.crawlerLevel = crawlerLevel;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
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
	public Date getUrlGenerateDate() {
		return urlGenerateDate;
	}
	public void setUrlGenerateDate(Date urlGenerateDate) {
		this.urlGenerateDate = urlGenerateDate;
	}
	/**
	 * 站点赋值
	 * 
	 * @param site
	 */
	public void fix(Engine engineBean) {
		if (engineBean != null) {
			Field[] beanFields = Engine.class.getDeclaredFields();
			Field[] siteFields = NEngine.class.getDeclaredFields();
			for (Field siteField : siteFields) {
				for (Field beanField : beanFields) {
					if (beanField.getName().equals(siteField.getName())) {
						beanField.setAccessible(true);
						try {
							if("operateTime".equals(beanField.getName())){
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = beanField.get(engineBean).toString().replace("T", " ").substring(0,19);
								try {
									siteField.set(this, format.parse(date));
								} catch (ParseException e) {
									siteField.set(this,new Date());
								}
							}else{
								siteField.set(this, beanField.get(engineBean));
							}
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
			this.setUrlGenerateDate(new Date());
		}
	}

}
