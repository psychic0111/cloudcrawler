package com.xdtech.cloudsearch.webservice.bean;

/**
 * 时间段
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class TimeRange {
	/** 休眠开始时间，格式为 HH:mm */
	private String start;
	/** 休眠结束时间，格式为 HH:mm */
	private String end;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
