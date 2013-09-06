package com.xdtech.cloudsearch.module.webcrawler.action;

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
import com.xdtech.cloudsearch.module.webcrawler.bean.Keyword;
import com.xdtech.cloudsearch.module.webcrawler.service.KeywordService;

/**
 * 全网监控词-Action
 * 
 * @author WangWei
 * 2013-06-04
 */
@Controller
@RequestMapping("/keyword")
public class KeywordAction extends BaseAction{
	@Autowired(required = true)
	private KeywordService keywordService;
	
	/**
	 * 全网监控词管理列表页
	 * @param keyword
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/keyword.do")
	public  ModelAndView index(Keyword keyword,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = keywordService.findByPage(keyword, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/webcrawler/keyword/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 全网监控词管理列表页
	 * @param keyword
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/keywordList.do")
	public  ModelAndView list(Keyword keyword,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = keywordService.findByPage(keyword, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/webcrawler/keyword/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 跳转到全网监控词添加页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/add.do")
	public String add() {
		return "/module/webcrawler/keyword/add.jsp";
	}
	
	/**
	 * 全网监控词添加
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody String doAdd(Keyword keyword) {
		keyword.setCreateTime(new Date());
		keyword.setIsUse(1);
		keyword.setUserid(currentUser.getId());
		keyword.setUsername(currentUser.getUsername());
		keywordService.save(keyword);
		return "true";
	}
	
	/**
	 *查看全网监控词是否存在
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/checkKeyword")
	public @ResponseBody String checkKeyword(Keyword keyword) {
		return keywordService.checkKeyword(keyword);
	}
	/**
	 * 全网监控词删除
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String delete(String id[]) {
		keywordService.deleteAll(Keyword.class,id );
		return "true";
	}
	/**
	 * 跳转到全网监控词修改页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(String id) {
		Keyword keyword = keywordService.findById(Keyword.class, id);
		return new ModelAndView("/module/webcrawler/keyword/edit.jsp", "keyword", keyword);
	}
    
	/**
	 *全网监控词修改
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/editDo")
	public @ResponseBody String editDo(Keyword keyword) {
		keyword.setCreateTime(new Date());
		keyword.setUserid(currentUser.getId());
		keyword.setUsername(currentUser.getUsername());
		keywordService.update(keyword);
		return "true";
	}
	
	/**
	 * 全网监控词启用
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/use.do")
	public @ResponseBody String use(String id[]) {
		keywordService.updateAll(Keyword.class,id,"isUse",1 );
		return "true";
	}
	/**
	 * 全网监控词启用
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/noUse.do")
	public @ResponseBody String noUse(String id[]) {
		keywordService.updateAll(Keyword.class,id,"isUse",0 );
		return "true";
	}
}
