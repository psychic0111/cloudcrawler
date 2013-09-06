package com.xdtech.platform.webcrawler.protocol;

import java.util.Date;

public class FetchEntry extends com.xdtech.platform.crawler.protocol.FetchEntry implements Cloneable {
	/** 发布时间 */
	private Date publishTime;
	/** 作者 */
	private String author;
	/** 来源 */
	private String source;
	/** 时长 */
	private String timeLong;
	/** 图片地址 */
	private String imagePath;
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTimeLong() {
		return timeLong;
	}

	public void setTimeLong(String timeLong) {
		this.timeLong = timeLong;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
