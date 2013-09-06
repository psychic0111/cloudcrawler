package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.List;

/**
 * 块规则（关联规则）解析结果
 * 
 * @author zhangjianbing@msn.com
 */
public class BlockResult {
	private List<String> keys;
	private List<String> values;

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
