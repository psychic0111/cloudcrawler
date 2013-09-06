package com.xdtech.project.webservice.bean;

/**
 * 系统信息类
 * 
 * @author zhangjianbing@msn.com
 */
public class SystemInfo {
	/**
	 * jre路径
	 */
	private String jreHome;
	/**
	 * jre版本
	 */
	private String version;
	/**
	 * 剩余空间
	 */
	private long freeMemory;

	public String getJreHome() {
		return jreHome;
	}

	public void setJreHome(String jreHome) {
		this.jreHome = jreHome;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

}
