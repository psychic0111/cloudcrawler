package com.xdtech.cloudsearch.module.sitecrawler.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 网络参数配置
 * 
 * @author LZF
 * 
 */
@Entity
@Table(name = "xd_crawler_netparam")
public class NetParam {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 连接次数
	 */
	private Integer connetCount;
	/**
	 * 超时时间
	 */
	private Integer timeoutSecond;
	/**
	 * 网络连接等待次数
	 */
	private Integer waiCount;
	/**
	 * 跳转次数
	 */
	private Integer referCount;

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

	@Column(length = 20)
	public Integer getConnetCount() {
		return connetCount;
	}

	public void setConnetCount(Integer connetCount) {
		this.connetCount = connetCount;
	}
	@Column(length = 20)
	public Integer getTimeoutSecond() {
		return timeoutSecond;
	}

	public void setTimeoutSecond(Integer timeoutSecond) {
		this.timeoutSecond = timeoutSecond;
	}
	@Column(length = 20)
	public Integer getWaiCount() {
		return waiCount;
	}

	public void setWaiCount(Integer waiCount) {
		this.waiCount = waiCount;
	}
	@Column(length = 20)
	public Integer getReferCount() {
		return referCount;
	}

	public void setReferCount(Integer referCount) {
		this.referCount = referCount;
	}
}
