package com.xdtech.platform.crawler.parser;

/**
 * 解析后的结果
 * 
 * @author Administrator
 * 
 */
public class Parse {
	private String text;
	private ParseData data;

	/**
	 * 文本内容
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * 元数据
	 * 
	 * @return
	 */
	public ParseData getData() {
		return data;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setData(ParseData data) {
		this.data = data;
	}

}
