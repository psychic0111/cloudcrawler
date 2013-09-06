package com.xdtech.platform.crawler.protocol;

/**
 * 抓取协议异常
 * 
 * @author Administrator
 * 
 */
public class ProtocolException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProtocolException() {
		super();
	}

	public ProtocolException(String message) {
		super(message);
	}

	public ProtocolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProtocolException(Throwable cause) {
		super(cause);
	}

}
