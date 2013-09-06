package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.List;

/**
 * 测试返回的数据
 * 
 * @author zhangjianbing@msn.com
 */
public class ParserRsult {
	/** 测试信息 */
	private String message;
	/** 标题 */
	private String title;
	/** 正文内容 */
	private String content;
	/** 发布时间 */
	private String pubtime;
	/** 作者 */
	private String author;
	/** 来源 */
	private String source;
	/** 来源 */
	private String commentCount;
	/** 查看数 */
	private String viewCount;
	/** 分页 */
	private String[] fenyeUrls;
	/** 关联规则URL */
	private String[] retiveUrls;

	/** 关联规则解析结果 */
	private List<BlockResult> blockResultList;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPubtime() {
		return pubtime;
	}

	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
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

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

	public String[] getFenyeUrls() {
		return fenyeUrls;
	}

	public void setFenyeUrls(String[] fenyeUrls) {
		this.fenyeUrls = fenyeUrls;
	}

	public List<BlockResult> getBlockResultList() {
		return blockResultList;
	}

	public void setBlockResultList(List<BlockResult> blockResultList) {
		this.blockResultList = blockResultList;
	}
	public String[] getRetiveUrls() {
		return retiveUrls;
	}

	public void setRetiveUrls(String[] retiveUrls) {
		this.retiveUrls = retiveUrls;
	}
}
