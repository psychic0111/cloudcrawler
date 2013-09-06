package com.xdtech.project.util;

/**
 * 所有的配置项由此类保存
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class ConfigParam {
	/** bdb的跟文件夹路径 */
	public static final String BerkeleyDBFolderPath = AppConf.get().get("berkeleydb.root.folder.path");
	/** bdb的跟文件夹路径 -全网监控*/
	public static final String BerkeleyDBFolderPathWebCrawler = AppConf.get().get("berkeleydb.root.folder.path.webcrawler");
	/** 一级地址表的名称 */
	public static final String WEBDB_L1 = "WEBDB_L1";
	/** 地址表的名称前缀*/
	public static final String WEBDB_ = "WEBDB_";
}
