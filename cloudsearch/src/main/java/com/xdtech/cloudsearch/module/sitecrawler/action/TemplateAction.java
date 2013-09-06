package com.xdtech.cloudsearch.module.sitecrawler.action;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.ParserRsult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Template;
import com.xdtech.cloudsearch.module.sitecrawler.bean.TemplateCategory;
import com.xdtech.cloudsearch.module.sitecrawler.service.TemplateCategoryService;
import com.xdtech.cloudsearch.module.sitecrawler.service.TemplateService;
import com.xdtech.cloudsearch.webservice.xdservice.DataCache;

/**
 * 模板-Action
 * 
 * @author WangWei
 * 2013-06-08
 */
@Controller
@RequestMapping("/template")
public class TemplateAction  extends BaseAction{
	@Autowired(required = true)
	private TemplateService templateService;
	@Autowired(required = true)
	private TemplateCategoryService templateCategoryService;
	
	/**
	 * 模板管理列表页
	 * @param template
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/template.do")
	public  ModelAndView index(Template template,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		List<TemplateCategory> allTemplateCategorys = new ArrayList<TemplateCategory>();
		ModelMap mmap = new ModelMap();
		List<TemplateCategory> fristCategory = templateCategoryService.getAllFristLevel();
		for(TemplateCategory tc:fristCategory){
			allTemplateCategorys.add(tc);
			tc.setChildList(templateCategoryService.getAllSecondLevel(tc.getId()));
		}
		mmap.put("fristCategory",allTemplateCategorys);
		PageResult pageResult = templateService.findByPage(template, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/template/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 模板管理列表页
	 * @param template
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/templateList.do")
	public  ModelAndView list(Template template,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		List<TemplateCategory> allTemplateCategorys = new ArrayList<TemplateCategory>();
		List<TemplateCategory> fristCategory = templateCategoryService.getAllFristLevel();
		for(TemplateCategory tc:fristCategory){
			allTemplateCategorys.add(tc);
			tc.setChildList(templateCategoryService.getAllSecondLevel(tc.getId()));
		}
		mmap.put("fristCategory",allTemplateCategorys);
		PageResult pageResult = templateService.findByPage(template, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/template/treeAndList.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 模板管理列表页
	 * @param template
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/templateContentList.do")
	public  ModelAndView contentlist(Template template,@ModelAttribute PageResult pageReuslt) {
		template.setCategoryId(template.getCategoryId().replace("_0", "").replace("_1", ""));
		ModelMap mmap = new ModelMap();
		List<TemplateCategory> allTemplateCategorys = new ArrayList<TemplateCategory>();
		List<TemplateCategory> fristCategory = templateCategoryService.getAllFristLevel();
		for(TemplateCategory tc:fristCategory){
			allTemplateCategorys.add(tc);
			tc.setChildList(templateCategoryService.getAllSecondLevel(tc.getId()));
		}
		mmap.put("fristCategory",allTemplateCategorys);
		PageResult pageResult = templateService.findByPageC(template, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/template/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 跳转到模板添加页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/add.do")
	public ModelAndView add() {
		ModelMap mmap = new ModelMap();
		List<TemplateCategory> allTemplateCategorys = new ArrayList<TemplateCategory>();
		List<TemplateCategory> fristCategory = templateCategoryService.getAllFristLevel();
		for(TemplateCategory tc:fristCategory){
			allTemplateCategorys.add(tc);
			tc.setChildList(templateCategoryService.getAllSecondLevel(tc.getId()));
		}
		mmap.put("fristCategory",allTemplateCategorys);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/template/add.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	
	/**
	 * 模板添加
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody String doAdd(Template template) {
		template.setOperateTime(new Date());
		template.setOperateUser(currentUser.getUsername());
		templateService.save(template);
		DataCache.initTemplate();
		return "true";
	}
	/**
	 * 模板添加(站点)
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAddForSite.do")
	public @ResponseBody void doAddForSite(HttpServletResponse response,Template template) {
		template.setOperateTime(new Date());
		template.setOperateUser(currentUser.getUsername());
		String categoryId = "";
		if(template.getCategoryId().length() > 32){
			categoryId = template.getCategoryId().split(",")[1];
		}
		template.setCategoryId(categoryId);
		templateService.save(template);
		JSONObject jo = JSONObject.fromObject(template);
		try {
			response.reset();
			response.setContentType("text/json; charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			out.print(jo.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *查看模板是否存在
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/checkTemplate")
	public @ResponseBody String checkTemplate(Template template) {
		return templateService.checkTemplate(template);
	}
	/**
	 * 模板删除
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String delete(String id[]) {
		templateService.deleteAll(Template.class,id );
		DataCache.initTemplate();
		return "true";
	}
	/**
	 * 跳转到模板修改页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(String id) {
		ModelMap mmap = new ModelMap();
		Template template = templateService.findById(Template.class, id);
		List<TemplateCategory> allTemplateCategorys = new ArrayList<TemplateCategory>();
		List<TemplateCategory> fristCategory = templateCategoryService.getAllFristLevel();
		for(TemplateCategory tc:fristCategory){
			allTemplateCategorys.add(tc);
			tc.setChildList(templateCategoryService.getAllSecondLevel(tc.getId()));
		}
		mmap.put("fristCategory",allTemplateCategorys);
		mmap.put("template", template);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/template/edit.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
    
	/**
	 *模板修改
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/editDo")
	public @ResponseBody String editDo(Template template) {
		template.setOperateTime(new Date());
		templateService.update(template);
		DataCache.initTemplate();
		return "true";
	}
	
	/**
	 * 根据一级类型，找到二级类型
	 * @param response 以json格式返回数据
	 * @param parentId 父ID
	 */
	@RequestMapping("/getSecondLevel")
	public void getSecondLevel(HttpServletResponse response,String parentId){
		List<TemplateCategory> tcs = templateCategoryService.getAllSecondLevel(parentId);
		JSONArray jsonArray = JSONArray.fromObject(tcs);
		try {
			response.setContentType("text/json; charset=UTF-8"); 
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据一级类型，找到二级类型
	 * @param response 以json格式返回数据
	 * @param parentId 父ID
	 */
	@RequestMapping("/getTemplateByTCId")
	public void getTemplateByTCId(HttpServletResponse response,String tcid){
		List<Template> tcs = templateService.findTemplateByTemplateCategoryId(tcid);
		JSONArray jsonArray = JSONArray.fromObject(tcs);
		try {
			response.setContentType("text/json; charset=UTF-8"); 
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试配置
	 */
	@RequestMapping("/test")
	public void test(HttpServletResponse response,String testUrl,Template template) {
		ParserRsult parserRsult = templateService.test(testUrl, template);
		JSONObject jsonObject = JSONObject.fromObject(parserRsult);
		try {
			response.setContentType("text/json; charset=UTF-8");
			System.out.println(jsonObject.toString());
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
