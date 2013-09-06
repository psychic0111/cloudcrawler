package com.xdtech.platform.crawler.webservice.bean;

/**
 * 用于webservice传输的二维数组，一般是一个Properties转换而来
 * 
 * @author zhangjianbing@msn.com
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
