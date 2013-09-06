package com.xdtech.platform.crawler.webservice.bean;

import java.util.List;

/**
 * 测试解析的结果
 * 
 * @author zhangjianbing@msn.com
 */
public class ParseResult {
	/** 基本解析结果 */
	private WebServiceProperties base;
	/** 块规则（关联规则）解析结果 */
	private List<WebServiceProperties> blocks;

	public WebServiceProperties getBase() {
		return base;
	}

	public void setBase(WebServiceProperties base) {
		this.base = base;
	}

	public List<WebServiceProperties> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<WebServiceProperties> blocks) {
		this.blocks = blocks;
	}

}
