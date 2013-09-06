package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 模板分类
 * 
 * @author WangWei
 * 2013-06-05
 */
@Entity
@Table(name = "xd_crawler_template_category")
public class TemplateCategory {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 分类父ID
	 */
	private String parentId;
	/**
	 * 分类级别
	 */
	private Integer categoryLevel;
	/**
	 * 操作时间
	 */
	private Date operateTime;
	/**
	 * 操作人
	 */
	private String operateUser;
	/**
	 * 子节点列表
	 */
	private List<TemplateCategory> childList;
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
	@Column(columnDefinition="varchar(150)")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition="varchar(32)")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getCategoryLevel() {
		return categoryLevel;
	}
	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
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
	@Transient
	public List<TemplateCategory> getChildList() {
		return childList;
	}

	public void setChildList(List<TemplateCategory> childList) {
		this.childList = childList;
	}
}
