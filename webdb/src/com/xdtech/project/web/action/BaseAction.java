package com.xdtech.project.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * action基类
 * 
 * @author Administrator
 */
public class BaseAction implements ServletHandler {
	/**
	 * http request对象
	 */
	protected HttpServletRequest request;
	/**
	 * http response object
	 */
	protected HttpServletResponse response;
	/**
	 * json错误信息,为使用dwz
	 */
	protected String errorMessage = "{\"statusCode\":300,\"message\":\"操作失败\"}";
	/**
	 * 操作内容，如果想记录日志则对actionMessage赋值
	 */
	protected String actionMessage;
	/**
	 * 总页码
	 */
	protected long pageCount = 1;
	/**
	 * 页尺寸
	 */
	protected int numPerPage = 10;
	/**
	 * 总数
	 */
	protected long total = 0;
	/**
	 * 当前页码
	 */
	protected int pageNum = 1;
	/**
	 * 分页代码
	 */
	protected String pageString;

	private String _rad;

	/**
	 * 设置HttpServletRequest
	 * 
	 * @param request
	 *            request
	 */
	public void setServletRequest(HttpServletRequest request) throws Exception {
		this.request = request;
	}

	/**
	 * 设置HttpServletResponse
	 * 
	 * @param response
	 *            response
	 */
	public void setServletResponse(HttpServletResponse response)
			throws Exception {
		this.response = response;
	}


	/**
	 * 计算页数
	 */
	public void countPageCount() {
		pageCount = (total % numPerPage == 0 ? total / numPerPage : total
				/ numPerPage + 1);
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getActionMessage() {
		return actionMessage != null ? actionMessage : "";
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public String get_rad() {
		return _rad;
	}

	public void set_rad(String rad) {
		_rad = rad;
	}

}
