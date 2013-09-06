package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

/**
 * 站点分类
 * 
 * @author WangWei
 * 2013-06-04
 */
@Entity
@Table(name = "xd_crawler_site_category")
public class SiteCategory {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 类型名称
	 */
	private String name;
	/**
	 * 操作人
	 */
	private String operateUser;
	/**
	 * 网站个数
	 */
	private Integer countSite = 0;
	/**
	 * 操作时间
	 */
	private Date operateTime;
	/**
	 * 该站点分类下的站点
	 */
	private List<Site> sites;
	/***
	 * 是否选中
	 */
	private boolean checked;
	
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
	@Column(columnDefinition="varchar(300)")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=32)
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	@Transactional
	public Integer getCountSite() {
		return countSite;
	}
	public void setCountSite(Integer countSite) {
		this.countSite = countSite;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	@Transient
	public List<Site> getSites() {
		return sites;
	}
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	@Transient
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
