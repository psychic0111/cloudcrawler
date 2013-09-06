package com.xdtech.platform.util;

/**
 * 标示URL的类型
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class URLType {
	public static final int UNKNOWN = -1;
	public static final int HTML = 0;
	public static final int SCRIPT = 1;
	public static final int LINK = 2;
	public static final int IMAGE = 3;
	public static final int EMBED = 4;
	public static final int XML = 5;

	public static String toString(int type) {
		String stype = "";
		switch (type) {
		case UNKNOWN:
			stype = "";
			break;
		case HTML:
			stype = "a";
			break;
		case SCRIPT:
			stype = "script";
			break;
		case LINK:
			stype = "link";
			break;
		case IMAGE:
			stype = "img";
			break;
		case EMBED:
			stype = "embed";
			break;
		case XML:
			stype = "xml";
			break;
		default:
			stype = "";
			break;
		}
		return stype;
	}
}
