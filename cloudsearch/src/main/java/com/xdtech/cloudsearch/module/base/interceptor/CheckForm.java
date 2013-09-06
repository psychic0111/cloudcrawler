package com.xdtech.cloudsearch.module.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <pre>
 * 检查表单是否重复提交。
 * 如果需表单需要检测重复提交,需要在你的表单的中添加"_token"标记。示例如下：
 * <code><input type="hidden" name="_token" value="${token}"/></code>
 * </pre>
 * @author Chang Fei
 *
 */
public class CheckForm extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object token = request.getSession().getAttribute("token");
		String _tokne=request.getParameter("_token");
		
		//如果令牌为空，放置令牌
		if(token==null){
			request.getSession().setAttribute("token", System.currentTimeMillis());
			return true;
		}
		//客户端没有放置令牌,则说明不需要令牌验证
		if(_tokne==null){
			return true;
		}
		//如果客户端有令牌，检查令牌是否有效
		if(token.toString().equals(_tokne)){
			request.getSession().removeAttribute("token");
			request.getSession().setAttribute("token", System.currentTimeMillis());
			return true;
		}else{
			//如果是无效令牌直接返回
			return false;
		}
	}
}
