package com.xdtech.platform.crawler;

public abstract class CeawlerFacade {
	/** 启动爬虫 */
	public abstract void start();

	/** 停止爬虫 */
	public abstract void stop();

	/** 添加地址 */
	public abstract void addURL();

	/** 添加入口地址 */
	public abstract void addSiteURL();

	/** 获取爬虫状态 */
	public abstract void getCrawlerStatus();
}
