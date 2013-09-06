package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 代理
 * 
 * @author WangWei
 * 2013-06-05
 */
@Entity
@Table(name = "xd_crawler_site_proxy")
public class SiteProxy {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 代理名称
	 */
	private String name;
	/**
	 * 代理描述
	 */
	private String remark;
	/**
	 * IP地址
	 */
	private String proxyIp;
	/**
	 * 端口
	 */
	private Integer proxyPort; 
	/**
	 * 代理用户名
	 */
	private String userName;
	/**
	 * 代理密码
	 */
	private String proxyPwd;
	/**
	 * 代理类型
	 */
	private String proxyType;
	/**
	 * 操作人
	 */
	private String operateUser;
	/**
	 * 操作时间
	 */
	private Date operateTime;
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
	@Column(columnDefinition="varchar(500)")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(columnDefinition="varchar(20)")
	public String getProxyIp() {
		return proxyIp;
	}
	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}
	public Integer getProxyPort() {
		return proxyPort;
	}
	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}
	@Column(columnDefinition="varchar(200)")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(columnDefinition="varchar(200)")
	public String getProxyPwd() {
		return proxyPwd;
	}
	public void setProxyPwd(String proxyPwd) {
		this.proxyPwd = proxyPwd;
	}
	@Column(columnDefinition="varchar(100)")
	public String getProxyType() {
		return proxyType;
	}
	public void setProxyType(String proxyType) {
		this.proxyType = proxyType;
	}
	@Column(columnDefinition="varchar(32)")
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	

}
