package com.xdtech.cloudsearch.module.webcrawler.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 全网监控词
 * 
 * @author WangWei
 * 2013-06-04
 */
@Entity
@Table(name = "xd_crawler_word")
public class Keyword {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 监控词名称
	 */
	private String name;
	/**
	 * 监控词，多个监控词用空格分开
	 */
	private String words;
	/**
	 * 是否启用  1 启用 0 不启用
	 */
	private Integer isUse;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 操作人
	 */
	private int userid;
	/**
	 * 操作人名称
	 */
	private String username;
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
	@Column(columnDefinition="varchar(600)")
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
