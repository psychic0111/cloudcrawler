package com.xdtech.cloudsearch.module.base.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 调试拦截器
 * @author Chang Fei
 *
 */
@Component
public class DebugInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(DebugInterceptor.class);
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Set<String> noPrints = new HashSet<String>();
		noPrints.add("/ServerCrawler/crawler/ajaxList.do");
		noPrints.add("/ServerCrawler/task/runningTasks.do");
		noPrints.add("/ServerCrawler/engineControl/ajaxList.do");
		if(!noPrints.contains(request.getRequestURI())){
			System.out.println("************************************");
			System.out.println("path: http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getRequestURI());
			System.out.println("parameters: "+request.getQueryString());
			System.out.println("action: "+handler.getClass().getSimpleName());
			System.out.println("request encoding: "+request.getCharacterEncoding());
			System.out.println("************************************");
		}
		return super.preHandle(request, response, handler);
	}
	
	
	
	/**
	 * 进入view前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView!=null){
			System.out.println("view: "+modelAndView.getViewName());
		}
		System.out.println("response encoding: "+response.getCharacterEncoding());
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
