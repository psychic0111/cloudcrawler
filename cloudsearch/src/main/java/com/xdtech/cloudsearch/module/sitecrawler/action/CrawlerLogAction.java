package com.xdtech.cloudsearch.module.sitecrawler.action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.bean.CrawlerLog;
import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerLogService;
import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerService;

/**
 * 日志管理-Service
 * 
 * @author WangWei
 * 2013-08-08
 */
@Controller
@RequestMapping("/crawlerlog")
public class CrawlerLogAction extends BaseAction {
	@Autowired(required = true)
	private CrawlerLogService crawlerlogService;
	@Autowired(required = true)
	private CrawlerService crawlerService;
	
	/**
	 * 日志管理管理列表页
	 * @param crawlerlog
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/crawlerlog.do")
	public  ModelAndView index(CrawlerLog crawlerlog,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = crawlerlogService.findByPage(crawlerlog, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		List<Crawler> crawlers = crawlerService.getAllCrawler();
		mmap.put("crawlers", crawlers);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/logs/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 日志管理管理列表页
	 * @param CrawlerLog
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/crawlerlogList.do")
	public  ModelAndView list(CrawlerLog crawlerlog,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = crawlerlogService.findByPage(crawlerlog, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/logs/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	
}
