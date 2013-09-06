package com.xdtech.platform.webcrawler.webservice.bean;

/**
 * 全网监控爬虫-下载模块
 * 
 * @author WangWei
 * 2013-07-03
 */
public class WebServiceProperties {
	/** 键 */
	private String[] keys;
	/** 值 */
	private String[] values;

	public WebServiceProperties() {
	}

	public WebServiceProperties(String[] keys, String[] values) {
		this.keys = keys;
		this.values = values;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

}
