package com.xdtech.cloudsearch.module.system.action;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.system.bean.User;
import com.xdtech.cloudsearch.module.system.service.UserService;
import com.xdtech.cloudsearch.util.ValidateObject;

/**
 * 登陆Action
 * 
 * @author yuhao
 */
@Controller
@RequestMapping("/user")
public class LoginAction extends BaseAction {
	
	@Autowired(required = true)
	private UserService userService;
	
	/**
	 * 用户登陆
	 * @param user
	 * @return 
	 * @author yuhao
	 */
	@RequestMapping("/login.do")
	public  ModelAndView login(User user, @RequestParam(value = "rememberUser", required = false, defaultValue = "0") int rememberUser, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv=null;
		ModelMap mmap = new ModelMap();
		User u= userService.login(user);
		if(u!=null){
			mv = new ModelAndView("redirect:/user/toindex.do");
			session.setAttribute("user", u);
			
			String cookieUserName = "username" + "_" + user.getUsername();
			if(rememberUser == 1){
				Cookie[] cs = request.getCookies();
				Cookie cookie = null;
				for(Cookie c : cs){
					if(c.getName().equals(cookieUserName) && c.getPath().equals("/")){
						cookie = c;
						break;
					}
				}
				if(cookie == null){
					cookie = new Cookie(cookieUserName, user.getPassword());
				}else{
					cookie.setValue(user.getPassword());
				}
				cookie.setMaxAge(60 * 60 * 24 * 7);   //cookie生命周期7天
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}else{
			String cookieUserName = "username" + "_" + user.getUsername();
			Cookie[] cs = request.getCookies();
			for(Cookie c : cs){
				if(c.getName().equals(cookieUserName) && c.getPath() != null && c.getPath().equals("/")){
					if(ValidateObject.hasValue(c.getValue()) && c.getValue().equals(user.getPassword())){
						c.setValue(null);
						c.setMaxAge(0);
						response.addCookie(c);
					}
					break;
				}
			}
			
			mv = new ModelAndView("/login.jsp");
			mmap.put("message", "账号或者密码错误");
			mmap.put("type", 2);
		}
		mv.addAllObjects(mmap);
		return mv;
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session){
		session.removeAttribute("user");
		ModelAndView mv = new ModelAndView("/login.jsp");
		mv.addObject("type", 1);
		return mv;
	}
	
	@RequestMapping("/toindex.do")
	public  ModelAndView toindex() {
		ModelAndView mv = new ModelAndView("/xdindex.jsp");
		return mv;
	}
}
