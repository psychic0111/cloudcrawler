package com.xdtech.project.modules.webdb.exception;

/**
 * 没有获取到数据库连接而抛出的异常
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class NoConnException extends Exception {
	public NoConnException() {
		super("没有获取到数据库连接");
	}
}
