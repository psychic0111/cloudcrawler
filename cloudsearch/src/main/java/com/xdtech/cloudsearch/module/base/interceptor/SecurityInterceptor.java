package com.xdtech.cloudsearch.module.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 安全检查拦截器
 * 
 * @author Chang Fei
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 检查是回话是否有效
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		Object obj = request.getSession().getAttribute("user");
		if (!isAllow(url) & obj == null) {
			response.sendRedirect("/" + request.getContextPath());
			return false;
		}
		return true;
	}

	private boolean isAllow(String url) {
		if (url.contains("login.do")) {
			return true;
		}
		return false;
	}
}
