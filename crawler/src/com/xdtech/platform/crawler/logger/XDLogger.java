package com.xdtech.platform.crawler.logger;

import org.apache.log4j.Logger;

/**
 * 统一管理的日志类，便于扩展
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class XDLogger {
	private static Logger logger = Logger.getLogger(XDLogger.class);

	/**
	 * 消息级别的日志
	 * 
	 * @param message
	 */
	public static void info(String message) {
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}

	/**
	 * 调试级别的日志
	 * 
	 * @param message
	 */
	public static void debug(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	/**
	 * 错误级别的日志
	 * 
	 * @param message
	 */
	public static void error(String message) {
		logger.error(message);
	}

	/**
	 * 异常级别的日志
	 * @param message
	 */
	public static void exception(String message) {
		logger.error(message,new Exception(message));
	}
}
