package com.xdtech.project.webservicecrawler.bean;

import java.util.List;

public class WebDBCrawlerResult {
	private int total;
	private List<WebDbCrawler> dataList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<WebDbCrawler> getDataList() {
		return dataList;
	}

	public void setDataList(List<WebDbCrawler> dataList) {
		this.dataList = dataList;
	}
}
