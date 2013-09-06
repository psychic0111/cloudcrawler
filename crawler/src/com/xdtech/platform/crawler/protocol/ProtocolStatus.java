package com.xdtech.platform.crawler.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 采集状态
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class ProtocolStatus {
	/** 抓取成功 */
	public static final int SUCCESS = 1;
	/** 抓取失败 */
	public static final int FAILED = 2;
	/** 不支持的抓取协议 */
	public static final int PROTO_NOT_FOUND = 10;
	/** 资源移动 */
	public static final int MOVED = 12;
	/** 资源没有找到 */
	public static final int NOTFOUND = 14;
	/** 发生异常 */
	public static final int EXCEPTION = 16;
	/** 证书错误 */
	public static final int ACCESS_DENIED = 17;
	/** 源资没有发生修改 */
	public static final int NOTMODIFIED = 21;
	public static final ProtocolStatus STATUS_SUCCESS = new ProtocolStatus(SUCCESS);
	public static final ProtocolStatus STATUS_FAILED = new ProtocolStatus(FAILED);
	public static final ProtocolStatus STATUS_NOTFOUND = new ProtocolStatus(NOTFOUND);
	public static final ProtocolStatus STATUS_NOTMODIFIED = new ProtocolStatus(NOTMODIFIED);
	/** 状态代码 */
	private int code;
	/** 资源最后修改时间 */
	private long lastModified;
	/** 资源跳转之后的新URL */
	private String newUrl;
	/** 资源发生异常之后的异常信息 */
	private List<String> message;
	private static HashMap<Integer, String> codeToName = new HashMap<Integer, String>();
	static {
		codeToName.put(new Integer(SUCCESS), "抓取成功");
		codeToName.put(new Integer(FAILED), "抓取失败 ");
		codeToName.put(new Integer(PROTO_NOT_FOUND), "不支持的抓取协议");
		codeToName.put(new Integer(MOVED), "资源移动");
		codeToName.put(new Integer(NOTFOUND), "资源没有找到");
		codeToName.put(new Integer(EXCEPTION), "发生异常");
		codeToName.put(new Integer(NOTMODIFIED), "资源没有发生修改");
	}

	public ProtocolStatus(int code) {
		this.code = code;
	}

	public ProtocolStatus(int code, String message) {
		this.code = code;
		if (message != null) {
			if (this.message == null) {
				this.message = new ArrayList<String>();
			}
			this.message.add(message);
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public String getNewUrl() {
		return newUrl;
	}

	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

}
