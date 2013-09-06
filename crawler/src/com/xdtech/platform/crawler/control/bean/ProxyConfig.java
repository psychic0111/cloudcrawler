package com.xdtech.platform.crawler.control.bean;

import java.lang.reflect.Field;

import com.xdtech.platform.crawler.ws.dispatcher.ProxyConfigBean;
import com.xdtech.platform.crawler.ws.dispatcher.SiteBean;

public class ProxyConfig {
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
	 * 端口号
	 */
	private String proxyPort;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String proxypwd;
	/**
	 * 代理类型
	 */
	private String proxytype;
	/**
	 * 测试地址
	 */
	private String url;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public String getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProxypwd() {
		return proxypwd;
	}

	public void setProxypwd(String proxypwd) {
		this.proxypwd = proxypwd;
	}

	public String getProxytype() {
		return proxytype;
	}

	public void setProxytype(String proxytype) {
		this.proxytype = proxytype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void fix(ProxyConfigBean proxyConfigBean) {
		if (proxyConfigBean != null) {
			Field[] beanFields = ProxyConfigBean.class.getDeclaredFields();
			Field[] siteFields = ProxyConfig.class.getDeclaredFields();
			for (Field siteField : siteFields) {
				for (Field beanField : beanFields) {
					if (beanField.getName().equals(siteField.getName())) {
						beanField.setAccessible(true);
						try {
							siteField.set(this, beanField.get(proxyConfigBean));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
