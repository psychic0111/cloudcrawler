package com.xdtech.platform.crawler.protocol;

import java.util.Date;

/**
 * 抓取的结果对象
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class ProtocolOutput {
	/** 抓取的内容 */
	private Content content;
	/** 抓取的状态 */
	private ProtocolStatus status;
	/**
	 * 爬取次数
	 */
	private Integer count;
	/**
	 * 爬取时间
	 */
	private Date crawlerDate;
	/** 是否是媒体类型 */
	private boolean media;

	public ProtocolOutput(Content content, ProtocolStatus status,Integer count,String title,Date crawlerTime) {
		this.content = content;
		this.status = status;
		this.count = count;
		this.crawlerDate = crawlerTime;
	}

	public ProtocolOutput(Content content,Integer count,String title,Date crawlerTime) {
		this.content = content;
		this.status = ProtocolStatus.STATUS_SUCCESS;
		this.count = count;
		this.crawlerDate = crawlerTime;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public ProtocolStatus getStatus() {
		return status;
	}

	public void setStatus(ProtocolStatus status) {
		this.status = status;
	}

	public boolean isMedia() {
		return media;
	}

	public void setMedia(boolean media) {
		this.media = media;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Date getCrawlerDate() {
		return crawlerDate;
	}
	public void setCrawlerDate(Date crawlerDate) {
		this.crawlerDate = crawlerDate;
	}
}
