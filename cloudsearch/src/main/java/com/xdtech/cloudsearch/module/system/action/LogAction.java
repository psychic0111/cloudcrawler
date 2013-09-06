package com.xdtech.cloudsearch.module.system.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.system.bean.Log;
import com.xdtech.cloudsearch.module.system.service.LogService;

/**
 * 日志管理Action
 * 
 * @author yuhao
 */

@Controller
@RequestMapping("/log")
public class LogAction {
	@Autowired(required = true)
	private LogService logService;
	
	/**
	 * 日志管理列表页
	 * @param user
	 * @return 
	 * @author wangwenxiang
	 */
	@RequestMapping("/index.do")
	public ModelAndView fsetting(Log log,PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = logService.findByPage(null,null,null,pageReuslt);;
		mmap.put("list", pageResult.getRows());
		mmap.put("log", log);
		ModelAndView mv = new ModelAndView("/module/system/log/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	
	/**
	 * 日志列表
	 * @return
	 */
	@RequestMapping("/list.do")
	public ModelAndView list(String actionUser,String startTime ,String endTime, @ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = logService.findByPage(actionUser,startTime,endTime,pageReuslt);;
		mmap.put("list", pageResult.getRows());
		ModelAndView mv = new ModelAndView("/module/system/log/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 日志列表
	 * @return
	 */
	@RequestMapping("/clearLog.do")
	public @ResponseBody String delete(String actionUser,String startTime ,String endTime) {
		logService.clearLog(actionUser,startTime,endTime);
		return "true";
	}
}
