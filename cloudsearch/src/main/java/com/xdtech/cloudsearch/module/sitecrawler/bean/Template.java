package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.xdtech.cloudsearch.ws.sitecrawler.FetchTemplate;

/**
 * 模板分类
 * 
 * @author WangWei
 * 2013-06-08
 */
@Entity
@Table(name = "xd_crawler_template")
public class Template {
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
	/**
	 * 该模板正在使用的站点个数
	 */
	private Integer sites;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition="varchar(200)")
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	@Column(columnDefinition="varchar(600)")
	public String getUrlRule() {
		return urlRule;
	}
	public void setUrlRule(String urlRule) {
		this.urlRule = urlRule;
	}
	@Column(columnDefinition="varchar(100)")
	public String getTitleFilterChars() {
		return titleFilterChars;
	}
	public void setTitleFilterChars(String titleFilterChars) {
		this.titleFilterChars = titleFilterChars;
	}
	@Column(columnDefinition="varchar(600)")
	public String getCommonLink() {
		return commonLink;
	}
	public void setCommonLink(String commonLink) {
		this.commonLink = commonLink;
	}
	@Column(columnDefinition="varchar(600)")
	public String getTitleRules() {
		return titleRules;
	}
	public void setTitleRules(String titleRules) {
		this.titleRules = titleRules;
	}
	@Column(columnDefinition="varchar(600)")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(columnDefinition="varchar(600)")
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	@Column(columnDefinition="varchar(600)")
	public String getSrcSites() {
		return srcSites;
	}
	public void setSrcSites(String srcSites) {
		this.srcSites = srcSites;
	}
	@Column(columnDefinition="varchar(600)")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Column(columnDefinition="varchar(600)")
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	@Column(columnDefinition="varchar(600)")
	public String getContentRules() {
		return contentRules;
	}
	public void setContentRules(String contentRules) {
		this.contentRules = contentRules;
	}
	@Column(columnDefinition="varchar(600)")
	public String getPageRules() {
		return pageRules;
	}
	public void setPageRules(String pageRules) {
		this.pageRules = pageRules;
	}
	@Column(columnDefinition="varchar(600)")
	public String getRelationRule() {
		return relationRule;
	}
	public void setRelationRule(String relationRule) {
		this.relationRule = relationRule;
	}
	@Column(columnDefinition="varchar(600)")
	public String getTitleInclude() {
		return titleInclude;
	}
	public void setTitleInclude(String titleInclude) {
		this.titleInclude = titleInclude;
	}
	@Column(columnDefinition="varchar(600)")
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	@Column(columnDefinition="varchar(600)")
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	@Column(columnDefinition="varchar(600)")
	public String getRetiveContentRule() {
		return retiveContentRule;
	}
	public void setRetiveContentRule(String retiveContentRule) {
		this.retiveContentRule = retiveContentRule;
	}
	@Transient
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Transient
	public Integer getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(Integer isRoot) {
		this.isRoot = isRoot;
	}
	@Transient
	public Integer getSites() {
		return sites;
	}
	public void setSites(Integer sites) {
		this.sites = sites;
	}
	public FetchTemplate fix(Template fetchTemplate) {
		FetchTemplate ft = new FetchTemplate();
		ft.setId(fetchTemplate.getId());
		ft.setName(fetchTemplate.getName());
		ft.setUrlRule(fetchTemplate.getUrlRule());
		ft.setTitleFilterChars(fetchTemplate.getTitleFilterChars());
		ft.setCommonLink(fetchTemplate.getCommonLink());
		ft.setTitleRules(fetchTemplate.getTitleRules());
		ft.setAuthor(fetchTemplate.getAuthor());
		ft.setSrcSites(fetchTemplate.getSrcSites());
		ft.setComments(fetchTemplate.getComments());
		ft.setViews(fetchTemplate.getViews());
		ft.setContentRules(fetchTemplate.getContentRules());
		ft.setPageRules(fetchTemplate.getPageRules());
		ft.setRelationRule(fetchTemplate.getRelationRule());
		ft.setDatatime(fetchTemplate.getPublishTime());
		return ft;
	}
}
