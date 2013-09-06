package com.xdtech.platform.crawler.webservice.bean;

public class Status {
	/** 抓取的页面的总数 */
	private long totalCount = 0L;
	/** HTML下载活动的线程数 */
	private int threadNum;
	/** 失败数 */
	public int failNum = 0;
	/** 爬虫状态 */
	public String state = "Not Running";

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public int getFailNum() {
		return failNum;
	}

	public void setFailNum(int failNum) {
		this.failNum = failNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
