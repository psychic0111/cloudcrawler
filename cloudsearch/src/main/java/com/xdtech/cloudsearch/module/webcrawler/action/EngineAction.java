package com.xdtech.cloudsearch.module.webcrawler.action;

import java.io.File;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteProxyService;
import com.xdtech.cloudsearch.module.webcrawler.bean.Engine;
import com.xdtech.cloudsearch.module.webcrawler.service.EngineService;
import com.xdtech.cloudsearch.util.AppConf;
/**
 * 搜索引擎-Action
 * 
 * @author WangWei
 * 2013-06-24
 */
@Controller
@RequestMapping("/engine")
public class EngineAction extends BaseAction {
	@Autowired(required = true)
	private EngineService engineService;
	@Autowired(required = true)
	private SiteProxyService siteProxyService;
	/**
	 * 搜索引擎列表页
	 * @param Engine
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/engine.do")
	public  ModelAndView index(Engine engine,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = engineService.findByPage(engine, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/webcrawler/engine/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 搜索引擎列表页
	 * @param Engine
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/engineList.do")
	public  ModelAndView list(Engine engine,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = engineService.findByPage(engine, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/webcrawler/engine/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 跳转到搜索引擎添加页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/add.do")
	public ModelAndView add(HttpServletRequest request) {
		ModelMap mmap = new ModelMap();
		Engine engine = new Engine();
		mmap.put("engine", engine);
		mmap.put("action", request.getContextPath() + "/engine" + "/doAdd.do");
		mmap.put("proxys", siteProxyService.findAll());
		mmap.put("mediaTypes",AppConf.get().get("system.media.type").split("\\|"));
		ModelAndView mv = new ModelAndView("/module/webcrawler/engine/add.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	
	/**
	 * 搜索引擎添加
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody String doAdd(Engine engine) {
		engine.setOperateTime(new Date());
		engine.setOperateUser(currentUser.getUsername());
		engineService.save(engine);
		return "true";
	}
	
	/**
	 *查看搜索引擎是否存在
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/checkEngine")
	public @ResponseBody String checkEngine(Engine engine) {
		return engineService.checkEngine(engine);
	}
	/**
	 * 搜索引擎删除
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String delete(String id[]) {
		engineService.deleteAll(Engine.class,id );
		return "true";
	}
	/**
	 * 跳转到搜索引擎修改页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(String id,HttpServletRequest request){
		ModelMap mmap = new ModelMap(); 
		Engine engine = engineService.findById(Engine.class, id);
		/*if(null != engine){
			if(engine.getAuthorRule()!=null)engine.setAuthorRule(engine.getAuthorRule().replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\""));
			if(engine.getListBlock()!=null)engine.setListBlock(engine.getListBlock().replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\""));
			if(engine.getItemBlock()!=null)engine.setItemBlock(engine.getItemBlock().replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\""));
			if(engine.getUrlRule()!=null)engine.setUrlRule(engine.getUrlRule().replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\""));
			if(engine.getTitleRule()!=null)engine.setTitleRule(engine.getTitleRule().replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\""));
			if(engine.getPublishTimeRule()!=null)engine.setPublishTimeRule(engine.getPublishTimeRule().replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\""));
			if(engine.getSummary()!=null)engine.setSummary(engine.getSummary().replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\""));
			if(engine.getDurationRule()!=null)engine.setDurationRule(engine.getDurationRule().replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\""));
		}*/
		mmap.put("engine", engine);
		mmap.put("action", request.getContextPath() + "/engine" + "/editDo.do");
		mmap.put("proxys", siteProxyService.findAll());
		mmap.put("mediaTypes",AppConf.get().get("system.media.type").split("\\|"));
		ModelAndView mv = new ModelAndView("/module/webcrawler/engine/add.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
    
	/**
	 *搜索引擎修改
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/editDo")
	public @ResponseBody String editDo(Engine engine) {
		engine.setOperateTime(new Date());
		engine.setOperateUser(currentUser.getUsername());
		System.out.println(engine.getUrlRule());
		engineService.update(engine);
		return "true";
	}
	/**
	 * 搜索引擎启用
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/use.do")
	public @ResponseBody String use(String id[]) {
		engineService.updateAll(Engine.class,id,"isUse",1 );
		return "true";
	}
	/**
	 * 搜索引擎启用
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/noUse.do")
	public @ResponseBody String noUse(String id[]) {
		engineService.updateAll(Engine.class,id,"isUse",0 );
		return "true";
	}
	
	/**
	 * 测试引擎
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/testEngine.do")
	public ModelAndView testEngine(String id,HttpServletRequest request){
		ModelMap mmap = new ModelMap(); 
		Engine engine = engineService.findById(Engine.class, id);
		mmap.put("engine", engine);
		mmap.put("action", request.getContextPath() + "/engine" + "/editDo.do");
		mmap.put("proxys", siteProxyService.findAll());
		mmap.put("mediaTypes",AppConf.get().get("system.media.type").split("\\|"));
		ModelAndView mv = new ModelAndView("/module/webcrawler/engine/add.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
}
