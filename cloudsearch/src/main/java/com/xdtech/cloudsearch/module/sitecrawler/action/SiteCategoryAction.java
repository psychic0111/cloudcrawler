package com.xdtech.cloudsearch.module.sitecrawler.action;

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
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteCategory;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteCategoryService;
import com.xdtech.cloudsearch.webservice.xdservice.DataCache;
/**
 * 站点分类-Action
 * 
 * @author WangWei
 * 2013-06-04
 */
@Controller
@RequestMapping("/siteCategory")
public class SiteCategoryAction extends BaseAction{
	@Autowired(required = true)
	private SiteCategoryService siteCategoryService;
	
	/**
	 * 站点分类管理列表页
	 * @param siteCategory
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/siteCategory.do")
	public  ModelAndView index(SiteCategory siteCategory,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = siteCategoryService.findByPage(siteCategory, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/category/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 站点分类管理列表页
	 * @param siteCategory
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/siteCategoryList.do")
	public  ModelAndView list(SiteCategory siteCategory,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = siteCategoryService.findByPage(siteCategory, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/category/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 跳转到站点分类添加页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/add.do")
	public String add() {
		return "/module/sitecrawler/category/add.jsp";
	}
	
	/**
	 * 站点分类添加
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody String doAdd(SiteCategory siteCategory) {
		siteCategory.setOperateTime(new Date());
		siteCategory.setCountSite(0);
		siteCategory.setOperateUser(currentUser.getUsername());
		siteCategoryService.save(siteCategory);
		DataCache.initSiteCategory();
		return "true";
	}
	
	/**
	 *查看站点分类是否存在
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/checkSiteCategory")
	public @ResponseBody String checkSiteCategory(SiteCategory siteCategory) {
		return siteCategoryService.checkSiteCategory(siteCategory);
	}
	/**
	 * 站点分类删除
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String delete(String id[]) {
		siteCategoryService.deleteAll(SiteCategory.class,id );
		DataCache.initSiteCategory();
		return "true";
	}
	/**
	 * 跳转到站点分类修改页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(String id) {
		SiteCategory siteCategory = siteCategoryService.findById(SiteCategory.class, id);
		return new ModelAndView("/module/sitecrawler/category/edit.jsp", "siteCategory", siteCategory);
	}
    
	/**
	 *站点分类修改
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/editDo")
	public @ResponseBody String editDo(SiteCategory siteCategory) {
		siteCategory.setOperateTime(new Date());
		siteCategoryService.update(siteCategory);
		DataCache.initSiteCategory();
		return "true";
	}
   
}
