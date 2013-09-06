package com.xdtech.cloudsearch.module.sitecrawler.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteProxy;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteProxyService;
/**
 * 插件参数-Action
 * 
 * @author WangWei
 * 2013-06-05
 */
@Controller
@RequestMapping("/siteProxy")
public class SiteProxyAction extends BaseAction{
	@Autowired(required = true)
	private SiteProxyService siteProxyService;
	
	/**
	 * 代理管理列表页
	 * @param siteProxy
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/siteProxy.do")
	public  ModelAndView index(SiteProxy siteProxy,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = siteProxyService.findByPage(siteProxy, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/pluginproxy/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 代理管理列表页
	 * @param siteProxy
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/siteProxyList.do")
	public  ModelAndView list(SiteProxy siteProxy,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = siteProxyService.findByPage(siteProxy, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/pluginproxy/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 跳转到代理添加页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/add.do")
	public String add() {
		return "/module/sitecrawler/pluginproxy/add.jsp";
	}
	
	/**
	 * 代理添加
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody String doAdd(SiteProxy siteProxy) {
		siteProxy.setOperateTime(new Date());
		siteProxy.setProxyType("http");
		siteProxy.setOperateUser(currentUser.getUsername());
		siteProxyService.save(siteProxy);
		return "true";
	}
	
	/**
	 *查看代理是否存在
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/checkSiteProxy")
	public @ResponseBody String checkSiteProxy(SiteProxy siteProxy) {
		return siteProxyService.checkSiteProxy(siteProxy);
	}
	/**
	 * 代理删除
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String delete(String id[]) {
		siteProxyService.deleteAll(SiteProxy.class,id );
		return "true";
	}
	/**
	 * 跳转到代理修改页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(String id) {
		SiteProxy siteProxy = siteProxyService.findById(SiteProxy.class, id);
		return new ModelAndView("/module/sitecrawler/pluginproxy/edit.jsp", "siteProxy", siteProxy);
	}
    
	/**
	 *代理修改
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/editDo")
	public @ResponseBody String editDo(SiteProxy siteProxy) {
		siteProxy.setOperateTime(new Date());
		siteProxyService.update(siteProxy);
		return "true";
	}
}
