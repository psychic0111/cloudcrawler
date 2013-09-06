package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


/**
 * 区域
 * 
 * @author WangWei
 * 2013-06-06
 */
@Entity
@Table(name = "xd_crawler_region")
public class Region {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 地区
	 */
	private String areaName;
	/**
	 * 父节点主键
	 */
	private String parentId;
	/**
	 * 排序
	 */
	private Integer sortNo;
	/**
	 * 是否删除 1 未删除 0 已删除
	 */
	private Integer deleted;
	/**
	 * 子节点列表
	 */
	private List<Region> childList;
	/**
	 * 父节点名称 
	 */
	private String parentName;
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
	@Column(columnDefinition="varchar(200)")
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	@Column(columnDefinition="varchar(32)")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	@Transient
	public List<Region> getChildList() {
		return childList;
	}

	public void setChildList(List<Region> childList) {
		this.childList = childList;
	}
	@Column(length=200)
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
