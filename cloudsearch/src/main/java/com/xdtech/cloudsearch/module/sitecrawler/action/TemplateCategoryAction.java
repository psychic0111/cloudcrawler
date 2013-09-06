package com.xdtech.cloudsearch.module.sitecrawler.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Template;
import com.xdtech.cloudsearch.module.sitecrawler.bean.TemplateCategory;
import com.xdtech.cloudsearch.module.sitecrawler.service.TemplateCategoryService;
import com.xdtech.cloudsearch.module.sitecrawler.service.TemplateService;

/**
 * 站点分类-Action
 * 
 * @author WangWei
 * 2013-06-04
 */
@Controller
@RequestMapping("/templateCategory")
public class TemplateCategoryAction extends BaseAction{
	@Autowired(required = true)
	private TemplateCategoryService templateCategoryService;
	
	/**
	 * 站点分类管理列表页
	 * @param templateCategory
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/templateCategory.do")
	public  ModelAndView index(TemplateCategory templateCategory,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = templateCategoryService.findByPage(templateCategory, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/templateCategory/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 站点分类管理列表页
	 * @param templateCategory
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/templateCategoryList.do")
	public  ModelAndView list(TemplateCategory templateCategory,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = templateCategoryService.findByPage(templateCategory, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/templateCategory/treeAndList.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	
	/**
	 * 站点分类内容列表页面
	 * @param templateCategory
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/templateCategoryContentList.do")
	public  ModelAndView contentList(TemplateCategory templateCategory,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = templateCategoryService.findByPage(templateCategory, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/templateCategory/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 站点分类管理列表页
	 * @param templateCategory
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/templateCategoryListTree.do")
	public  ModelAndView listTree(TemplateCategory templateCategory,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = templateCategoryService.findByPageTree(templateCategory, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/templateCategory/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 跳转到站点分类添加页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/add.do")
	public ModelAndView add(TemplateCategory templateCategory) {
		ModelMap mmap = new ModelMap();
		List<TemplateCategory> allTemplateCategorys = new ArrayList<TemplateCategory>();
		List<TemplateCategory> fristCategory = templateCategoryService.findChildrenByParentId("0");
		for(TemplateCategory tc:fristCategory){
			allTemplateCategorys.add(tc);
			tc.setChildList(templateCategoryService.getAllSecondLevel(tc.getId()));
		}
		mmap.put("fristCategory",allTemplateCategorys);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/templateCategory/add.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	
	/**
	 * 站点分类添加
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody String doAdd(TemplateCategory templateCategory) {
		templateCategory.setOperateTime(new Date());
		templateCategory.setOperateUser(currentUser.getUsername());
		if(null==templateCategory.getParentId()||templateCategory.getParentId().equals("")){
			templateCategory.setParentId("0");
		}
		if(templateCategory.getParentId().equals("0")){
			templateCategory.setCategoryLevel(0);
		}else{
			TemplateCategory tc = templateCategoryService.getDao().findById(TemplateCategory.class, templateCategory.getParentId());
			templateCategory.setCategoryLevel(tc.getCategoryLevel() +1);
		}
		templateCategoryService.save(templateCategory);
		return "true";
	}
	
	/**
	 *查看站点分类是否存在
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/checkTemplateCategory")
	public @ResponseBody String checkTemplateCategory(TemplateCategory templateCategory) {
		return templateCategoryService.checkTemplateCategory(templateCategory);
	}
	/**
	 * 站点分类删除
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String delete(String id[]) {
		templateCategoryService.deleteAll(TemplateCategory.class,id);
		return "true";
	}
	/**
	 * 跳转到站点分类修改页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(String id) {
		ModelMap mmap = new ModelMap();
		TemplateCategory templateCategory = templateCategoryService.findById(TemplateCategory.class, id);
		List<TemplateCategory> allTemplateCategorys = new ArrayList<TemplateCategory>();
		List<TemplateCategory> fristCategory = templateCategoryService.getAllSecondLevel("0");
		for(TemplateCategory tc:fristCategory){
			allTemplateCategorys.add(tc);
			tc.setChildList(templateCategoryService.getAllSecondLevel(tc.getId()));
		}
		mmap.put("fristCategory",allTemplateCategorys);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/templateCategory/edit.jsp", "templateCategory", templateCategory);
		mv.addAllObjects(mmap);
		return mv;
	}
    
	/**
	 *站点分类修改
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/editDo")
	public @ResponseBody String editDo(TemplateCategory templateCategory) {
		templateCategory.setOperateTime(new Date());
		templateCategory.setOperateUser(currentUser.getUsername());
		templateCategoryService.update(templateCategory);
		return "true";
	}
	/**
	 * 菜单树形结构展示.提供xml
	 * 
	 * @return
	 */
	@RequestMapping("/treeXml")
	public void treeXml(HttpServletResponse response,TemplateCategory templateCategory,@ModelAttribute PageResult pageReuslt,String selectedId) {
		Map<String, Integer> categoryAndNubers = templateCategoryService.getNumberAndCategoryId();
		Map<String,TemplateCategory> map = templateCategoryService.getTemplateCategoryMap();
		try {
			StringBuilder strb = new StringBuilder();
			strb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			strb.append("<tree id=\"0\" radio=\"1\">");
			// 查找根节点根节点
			templateCategory.setParentId("0");
			List<TemplateCategory> templateCategorys = templateCategoryService.findChildrenByParentId(templateCategory.getParentId());
			// 找到所有的一级节点,pid =1
			if(null != templateCategorys && templateCategorys.size() > 0){
				TemplateCategory root = templateCategorys.get(0);
				templateCategoryService.initRootChild(root,map);
				strb.append(templateCategoryService.nodeXml(root,selectedId,categoryAndNubers));
			}
			strb.append("</tree>");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");
			response.getWriter().println(strb.toString());
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 菜单树形结构展示.提供xml
	 * 
	 * @return
	 */
	@RequestMapping("/treeXmlCategory")
	public void treeXmlCategory(HttpServletResponse response, TemplateCategory templateCategory, @ModelAttribute PageResult pageReuslt, String selectedId) {
		Map<String, Integer> categoryAndNubers =templateCategoryService.getNumberAndCategoryId();
		Map<String, TemplateCategory> map =templateCategoryService.getTemplateCategoryMap();
		try {
			StringBuilder strb = new StringBuilder();
			strb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			strb.append("<tree id=\"0\" radio=\"1\">");
			// 查找根节点根节点
			templateCategory.setParentId("0");
			List<TemplateCategory> templateCategorys =templateCategoryService.findChildrenByParentId(templateCategory.getParentId());
			// 找到所有的一级节点,pid =1
			if (null != templateCategorys && templateCategorys.size() > 0) {
				TemplateCategory root = templateCategorys.get(0);
				templateCategoryService.initRootChild(root, map);
				strb.append(templateCategoryService.nodeXmlCategory(root, selectedId, categoryAndNubers));
			}
			strb.append("</tree>");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");
			response.getWriter().println(strb.toString());
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
