package com.xdtech.platform.crawler.parser;

/**
 * 外部链接，从一个页面中产生的链接
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class Outlink implements Cloneable {
	/** URL */
	private String url;
	/** 来源URL */
	private String fromUrl;
	/** URL等级 */
	private int level;
	/** URL类型 */
	private int type;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Outlink shallowClone() {
		try {
			return (Outlink) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return this;
	}
}
