package com.xdtech.project.webservice.handler;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * 自定义Handler拦截器，可在拦截器中实现权限验证、IP验证等。
 * 
 * @author Administrator
 */
public class TestHandler extends AbstractPhaseInterceptor<Message> {

	/**
	 * 构造方法
	 */
	public TestHandler() {
		super(Phase.RECEIVE);
	}

	/**
	 * 拦截器实现类
	 */
	public void handleMessage(Message message) throws Fault {
		System.out.println("测试用拦截器的实现......");
	}

}
