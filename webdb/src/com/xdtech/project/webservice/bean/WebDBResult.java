package com.xdtech.project.webservice.bean;

import java.util.List;

public class WebDBResult {
	private int total;
	private List<WebDb> dataList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<WebDb> getDataList() {
		return dataList;
	}

	public void setDataList(List<WebDb> dataList) {
		this.dataList = dataList;
	}
}
