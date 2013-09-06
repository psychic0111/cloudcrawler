package com.xdtech.platform.webcrawler.webservice.bean;

import java.util.List;

/**
 * 全网监控爬虫-下载模块
 * 
 * @author WangWei
 * 2013-07-03
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
