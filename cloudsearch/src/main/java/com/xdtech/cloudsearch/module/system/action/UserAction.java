package com.xdtech.cloudsearch.module.system.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.system.bean.User;
import com.xdtech.cloudsearch.module.system.service.UserService;

/**
 * 用户管理Action
 * 
 * @author yuhao
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {

	@Autowired(required = true)
	private UserService userService;

	/**
	 * 用户管理列表页
	 * 
	 * @param user
	 * @return
	 * @author yuhao
	 */
	@RequestMapping("/setting.do")
	public ModelAndView index(User user, @ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = userService.findByPage(user, pageReuslt);
		mmap.put("list", pageResult.getRows());
		ModelAndView mv = new ModelAndView("/module/system/user/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}

	/**
	 * 用户管理列表ajax
	 * 
	 * @param user
	 * @return
	 * @author yuhao
	 */
	@RequestMapping("/list.do")
	public ModelAndView list(User user, @ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = userService.findByPage(user, pageReuslt);
		mmap.put("list", pageResult.getRows());
		ModelAndView mv = new ModelAndView("/module/system/user/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}

	/**
	 * 跳转到用户添加页面
	 * 
	 * @return
	 * @author yuhao
	 */
	@RequestMapping("/add.do")
	public String add() {
		return "/module/system/user/add.jsp";
	}

	/**
	 * 用户添加
	 * 
	 * @return
	 * @author yuhao
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody
	String doAdd(User user) {
		user.setLastUpdateUser(currentUser.getUsername());
		user.setLastUpdateTime(new Date());
		userService.save(user);
		return "true";
	}

	/**
	 * 用户删除
	 * 
	 * @return
	 * @author yuhao
	 */
	@RequestMapping("/del.do")
	public @ResponseBody
	String delete(String id[]) {
		userService.deleteAll(User.class, id);
		return "true";
	}

	/**
	 * 跳转到用户修改页面
	 * 
	 * @return
	 * @author yuhao
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(Integer id) {
		User user = userService.findById(User.class, id);
		return new ModelAndView("/module/system/user/edit.jsp", "user", user);
	}

	/**
	 *用户修改
	 * 
	 * @return
	 * @author yuhao
	 */
	@RequestMapping("/editDo")
	public @ResponseBody
	String editDo(User user) {
		User u = userService.findById(user.getClass(), user.getId());
		u.setDescription(user.getDescription());
		u.setPassword(user.getPassword());
		u.setLastUpdateTime(new Date());
		u.setUsername(user.getUsername());
		user.setLastUpdateUser(currentUser.getUsername());
		userService.update(u);
		return "true";
	}

	/**
	 *查看用户是否存在
	 * 
	 * @return
	 * @author yuhao
	 */
	@RequestMapping("/checkuser")
	public @ResponseBody
	String checkUser(User user) {
		return userService.checkUser(user);
	}

	/**
	 * 跳转到修改密码页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/pwdedit.do")
	public ModelAndView pwdedit() {
		User user = currentUser;
		return new ModelAndView("/module/system/user/pwdedit.jsp", "user", user);
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/editPwd")
	public @ResponseBody
	String editPwd(User user) {
		try {
			User u = userService.findById(User.class, user.getId());
			u.setPassword(user.getPassword());
			u.setLastUpdateTime(new Date());
			u.setLastUpdateUser(currentUser.getUsername());
			userService.update(u);
		} catch (Exception e) {
			return "false";
		}
		return "true";
	}

}
