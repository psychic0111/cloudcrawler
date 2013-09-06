package com.xdtech.project.webservice.bean;

/**
 * 返回结果
 * 
 * @author zhangjianbing@msn.com
 */
public class TestResult {
	private String message;
	private int state;
	private SystemInfo info;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public SystemInfo getInfo() {
		return info;
	}

	public void setInfo(SystemInfo info) {
		this.info = info;
	}

}
