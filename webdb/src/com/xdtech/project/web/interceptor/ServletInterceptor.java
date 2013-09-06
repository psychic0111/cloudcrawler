package com.xdtech.project.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.DefaultSettings;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.config.entities.InterceptorMapping;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.xdtech.project.web.action.ServletHandler;

/**
 * 注入http对象的拦截器
 * 
 * @author Administrator
 */
@SuppressWarnings("serial")
public class ServletInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation ai) throws Exception {
		Object o = ai.getAction();
		if (o instanceof ServletHandler) {
			HttpServletRequest request = ServletActionContext.getRequest();
			((ServletHandler) o).setServletRequest(request);
			((ServletHandler) o).setServletResponse(ServletActionContext.getResponse());
		}
		String ret = ai.invoke();
		if (true) {
			StringBuilder actionString = new StringBuilder();
			ActionProxy proxy = ai.getProxy();
			List<InterceptorMapping> intercepterMapping = proxy.getConfig().getInterceptors();
			if (intercepterMapping != null && !intercepterMapping.isEmpty()) {
				Interceptor interceptor = null;
				for (InterceptorMapping map : intercepterMapping) {
					interceptor = map.getInterceptor();
				}
			}
			String namespace = proxy.getNamespace();
			actionString.append(namespace);
			if (namespace != null && namespace.trim().length() > 0 && !"/".equals(namespace)) {
				actionString.append("/");
			}
			actionString.append(proxy.getActionName()).append(".").append(DefaultSettings.get("struts.action.extension"));

			if (!"/admin/indexCollectData.dhtml".equals(actionString.toString()) && !"/system/control.dhtml".equals(actionString.toString()) && !"/database/getCrawlId.dhtml".equals(actionString.toString())
					&& !"/getcrawlid.dhtml".equals(actionString.toString())) {
				System.out.println("\n");
				System.out.println("action:    " + actionString.toString());
				System.out.println("class :    " + proxy.getConfig().getClassName());
				System.out.println("method:    " + proxy.getConfig().getMethodName());
				com.opensymphony.xwork2.config.entities.ResultConfig config = proxy.getConfig().getResults() != null ? proxy.getConfig().getResults().get(ret == null ? "" : ret) : null;
				System.out.println("result   : " + (config == null ? "null" : (config.getParams() == null ? "null" : config.getParams().get("location"))) + "\n");
			}
		}
		return ret;
	}

}
