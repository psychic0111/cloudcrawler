package com.xdtech.platform.crawler.control.nbean;

import java.util.Date;

import com.xdtech.platform.crawler.ws.dispatcher.FetchTemplateBean;
import com.xdtech.platform.crawler.ws.ndispatcher.Template;

public class NTemplate {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 分类ID
	 */
	private String categoryId;
	/**
	 * 地址匹配
	 */
	private String urlRule;
	/**
	 * 标题过滤
	 */
	private String titleFilterChars;
	/**
	 * 普通连接
	 */
	private String commonLink;
	/**
	 * 标题
	 */
	private String titleRules;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 发布时间
	 */
	private String publishTime;
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
	 * 内容
	 */
	private String contentRules;
	/**
	 * 分页规则
	 */
	private String pageRules;
	/**
	 * 关联规则
	 */
	private String relationRule;
	/**
	 * 标题包含
	 */
	private String titleInclude;
	/**
	 * 操作时间
	 */
	private Date operateTime;
	/**
	 * 操作人
	 */
	private String operateUser;
	/**
	 * 相关类容规则
	 */
	private String retiveContentRule;
	/**
	 * 分类名称
	 */
	private String categoryName;
	/**
	 * 是否是根目录，用于分类检索时使用
	 */
	private Integer isRoot;
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
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
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
	public String getRelationRule() {
		return relationRule;
	}
	public void setRelationRule(String relationRule) {
		this.relationRule = relationRule;
	}
	public String getTitleInclude() {
		return titleInclude;
	}
	public void setTitleInclude(String titleInclude) {
		this.titleInclude = titleInclude;
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
	public String getRetiveContentRule() {
		return retiveContentRule;
	}
	public void setRetiveContentRule(String retiveContentRule) {
		this.retiveContentRule = retiveContentRule;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(Integer isRoot) {
		this.isRoot = isRoot;
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
	}
}
