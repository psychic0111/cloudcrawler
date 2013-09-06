package com.xdtech.cloudsearch.module.base.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.xdtech.cloudsearch.module.system.bean.User;

@Controller
public class BaseAction {
	
	protected User currentUser;
	
	/**
	 * 时间格式Editor
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@ModelAttribute
	public void hookUser(HttpSession session){
		Object obj = session.getAttribute("user");
		if(obj!=null){
			currentUser = (User)obj;
		}
	}
	
	@ModelAttribute
	public HttpSession getSession(HttpSession session){
		return session;
	}
	
	
}
