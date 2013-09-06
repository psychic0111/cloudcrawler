package com.xdtech.project.util.string;

/**
 * 常用数字类
 * 
 * @author cuixy
 */
public class ConstanData {
	/**
	 * 数据删除的标识
	 */
	public static final int DATADELETED = 1;
	/**
	 * 数据有效的标识
	 */
	public static final int DATAVALID = 0;

	/**
	 * 启用
	 */
	public static final int USED = 1;

	/**
	 * 禁用
	 */
	public static final int UNUSED = 0;

	/**
	 * 操作成功标识
	 */
	public static final int SUCCESSCODE = 200;
	/**
	 * 操作失败标识
	 */
	public static final int FAILURECODE = 300;
	/**
	 * 回话超时标识
	 */
	public static final int TIMEOUTCODE = 301;
	/**
	 * 模版文件路径
	 */
	public static final String templeBase = "/WEB-INF/templefile/";
	/**
	 * 图片保存路径
	 */
	public static String imgBaseUrl = "/WEB-INF/upload/";
}
