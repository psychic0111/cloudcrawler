package com.xdtech.cloudsearch.module.webcrawler.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 搜索引擎监控
 * 
 * @author WangWei
 * 2013-06-26
 */
public class EngineControl {
	/**
	 * 监控的搜索引擎
	 */
	private List<Engine> engines = null;
	/**
	 * 当前活动的线程
	 */
	private Integer liveThread = 10;
	/**
	 * 当前的运行状态
	 */
	private boolean start = false;
	public List<Engine> getEngines() {
		return engines;
	}
	public void setEngines(List<Engine> engines) {
		this.engines = engines;
	}
	public Integer getLiveThread() {
		return liveThread;
	}
	public void setLiveThread(Integer liveThread) {
		this.liveThread = liveThread;
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	

}
