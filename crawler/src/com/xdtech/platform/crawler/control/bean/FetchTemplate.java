package com.xdtech.platform.crawler.control.bean;

import com.xdtech.platform.crawler.ws.dispatcher.FetchTemplateBean;
import com.xdtech.platform.crawler.ws.ndispatcher.Template;

/**
 * 解析模板bean
 * 
 * @author zhangjianbing@msn.com
 */
public class FetchTemplate {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 模板名称
	 */
	private String name;

	/**
	 * 模板类别
	 */
	private String categories;

	/**
	 * 地址匹配
	 */
	private String urlRule;

	/**
	 * 标题过滤字
	 */
	private String titleFilterChars;

	/**
	 * 普通链接
	 */
	private String commonLink;

	/***
	 * 相关链接
	 */
	private String relatedLink;

	/**
	 * 标题
	 */
	private String titleRules;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 数据日期
	 */
	private String datatime;

	/**
	 * 来源站点
	 */
	private String srcSites;

	/**
	 * 评论数
	 */
	private String comments;

	/**
	 * 浏览数
	 */
	private String views;

	/**
	 * 内容匹配
	 */
	private String contentRules;

	/**
	 * 分页规则
	 */
	private String pageRules;
	/** 使用这个模板的站点的ID */
	private String siteIds;
	/** 关联规则 */
	private String relationRule;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getUrlRule() {
		return urlRule;
	}

	public void setUrlRule(String urlRule) {
		this.urlRule = urlRule;
	}

	public String getTitleFilterChars() {
		return titleFilterChars;
	}

	public void setTitleFilterChars(String titleFilterChars) {
		this.titleFilterChars = titleFilterChars;
	}

	public String getCommonLink() {
		return commonLink;
	}

	public void setCommonLink(String commonLink) {
		this.commonLink = commonLink;
	}

	public String getRelatedLink() {
		return relatedLink;
	}

	public void setRelatedLink(String relatedLink) {
		this.relatedLink = relatedLink;
	}

	public String getTitleRules() {
		return titleRules;
	}

	public void setTitleRules(String titleRules) {
		this.titleRules = titleRules;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDatatime() {
		return datatime;
	}

	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}

	public String getSrcSites() {
		return srcSites;
	}

	public void setSrcSites(String srcSites) {
		this.srcSites = srcSites;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getContentRules() {
		return contentRules;
	}

	public void setContentRules(String contentRules) {
		this.contentRules = contentRules;
	}

	public String getPageRules() {
		return pageRules;
	}

	public void setPageRules(String pageRules) {
		this.pageRules = pageRules;
	}

	public FetchTemplate() {
		super();
	}

	public String getSiteIds() {
		return siteIds;
	}

	public void setSiteIds(String siteIds) {
		this.siteIds = siteIds;
	}

	public String getRelationRule() {
		return relationRule;
	}

	public void setRelationRule(String relationRule) {
		this.relationRule = relationRule;
	}

	public void fix(Template fetchTemplate) {
		this.id = fetchTemplate.getId();
		this.name = fetchTemplate.getName();
		this.urlRule = fetchTemplate.getUrlRule();
		this.titleFilterChars = fetchTemplate.getTitleFilterChars();
		this.commonLink = fetchTemplate.getCommonLink();
		this.titleRules = fetchTemplate.getTitleRules();
		this.author = fetchTemplate.getAuthor();
		this.srcSites = fetchTemplate.getSrcSites();
		this.comments = fetchTemplate.getComments();
		this.views = fetchTemplate.getViews();
		this.contentRules = fetchTemplate.getContentRules();
		this.pageRules = fetchTemplate.getPageRules();
		this.relationRule = fetchTemplate.getRelationRule();
		this.datatime = fetchTemplate.getPublishTime();
	}
}
