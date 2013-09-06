package com.xdtech.cloudsearch.module.task.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteCategory;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteCategoryService;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteService;
import com.xdtech.cloudsearch.module.task.bean.Task;
import com.xdtech.cloudsearch.module.task.service.TaskService;
import com.xdtech.cloudsearch.util.ValidateObject;
import com.xdtech.cloudsearch.webservice.xdservice.CrawlerConfigManager;
import com.xdtech.cloudsearch.webservice.xdservice.CrawlerStatusManager;
/**
 * 任务管理action
 * @author LZF
 *
 */
@Controller
@RequestMapping("/task")
public class TaskAction extends BaseAction{
	@Autowired(required = true)
	private TaskService taskService;
	@Autowired(required = true)
	private SiteCategoryService siteCategoryService;
	@Autowired(required = true)
	private SiteService siteService;
	private String selectSites;
	/**
	 * 任务管理列表页
	 * @param task
	 * @param pageReuslt
	 * @return
	 */
	@RequestMapping("/task.do")
	public  ModelAndView index(Task task,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = taskService.findByPage(task, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/task/task/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 
	 * @param task
	 * @param pageReuslt
	 * @return
	 */
	@RequestMapping("/taskList.do")
	public  ModelAndView list(Task task,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = taskService.findByPage(task, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/task/task/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 跳转到任务修改页面
	 * @return
	 * @author 
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(String id) {
		Task task = taskService.findById(Task.class, id);
		ModelMap mmap = new ModelMap();
		mmap.put("task", task);
		Set<Integer> hours = new HashSet<Integer>();
		for(int i = 0;i<24;i++){
			hours.add(i);
		}
		mmap.put("hours", hours);
		Set<Integer> minutes = new HashSet<Integer>();
		for(int i = 0;i<60;i++){
			minutes.add(i);
		}
		mmap.put("minutes", minutes);
		List<SiteCategory> selectSiteCategoryList = null;
		if(task.getIsAllSites() == 1){
			if(null != task.getSiteCategories()){
				String[] siteCategorys = task.getSiteCategories().split(",");
				StringBuilder queryString = new StringBuilder(" from SiteCategory where 1 = 1 ");
				StringBuilder ids = new StringBuilder("");
				for(String s:siteCategorys){
					if(!ids.toString().equals("")){
						ids.append(",");
					}
					ids.append("'").append(s).append("'");
				}
				if(!ids.toString().equals("")){
					queryString.append(" and id in (");
					queryString.append(ids);
					queryString.append(")");
				}
				selectSiteCategoryList = siteCategoryService.find(queryString.toString());
				mmap.put("selectSiteCategoryList", selectSiteCategoryList);
			}
			if(null != task.getSites()){
				String[] siteCategorys = task.getSites().split(",");
				StringBuilder queryString = new StringBuilder(" from Site where 1 = 1 ");
				StringBuilder ids = new StringBuilder("");
				for(String s:siteCategorys){
					if(!ids.toString().equals("")){
						ids.append(",");
					}
					ids.append("'").append(s).append("'");
				}
				if(!ids.toString().equals("")){
					queryString.append(" and id in (");
					queryString.append(ids);
					queryString.append(")");
				}
				List<Site> siteList = siteService.find(queryString.toString());
				mmap.put("siteList", siteList);
			}
		}
		
		
		List<SiteCategory> siteCategoryList = taskService.findAll(SiteCategory.class);
		if(siteCategoryList != null){
			int length = siteCategoryList.size();
			if(length % 4 != 0){
				int count = length % 4;
				count = 4 - count;
				for(int i = 0;i<count;i++){
					SiteCategory sc = new SiteCategory();
					sc.setId("");
					sc.setName("");
					siteCategoryList.add(sc);
				}
			}
			if(siteCategoryList!=null && selectSiteCategoryList!=null){
				for(SiteCategory category:siteCategoryList){
					for(SiteCategory selectCategory:selectSiteCategoryList){
						if(category.getId().equals(selectCategory.getId())){
							category.setChecked(true);
							break;
						}
					}
				}
			}
			
		}
		mmap.put("siteCategoryList", siteCategoryList);
		
		ModelAndView mv = new ModelAndView("/module/task/task/edit.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 修改任务
	 * @param task
	 * @return
	 */
	@RequestMapping("/editDo")
	public @ResponseBody String editDo(Task task) {
		task.setOperateTime(new Date());
		task.setOperateUser(currentUser.getUsername());
		if(task.getExecType() == 1){
			task.setWeekDays(null);
		}
		taskService.update(task);
		CrawlerConfigManager.refreshInit();
		return "true";
	}
	/**
	 * 转向任务添加页面
	 * @return
	 */
	@RequestMapping("/add.do")
	public ModelAndView add(){
		Task task = new Task();
		ModelAndView mv = new ModelAndView("/module/task/task/add.jsp");
		ModelMap mmap = new ModelMap();
		List<SiteCategory> siteCategoryList = taskService.findAll(SiteCategory.class);
		if(siteCategoryList != null){
			int length = siteCategoryList.size();
			if(length % 4 != 0){
				int count = length % 4;
				count = 4 - count;
				for(int i = 0;i<count;i++){
					SiteCategory sc = new SiteCategory();
					sc.setId("");
					sc.setName("");
					siteCategoryList.add(sc);
				}
			}
		}
		mmap.put("siteCategoryList", siteCategoryList);
		mmap.put("task", task);
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 站点分类添加
	 * @return
	 * @author 
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody String doAdd(Task task) {
		task.setOperateTime(new Date());
		task.setOperateUser(currentUser.getUsername());
		if(task.getExecType() == 1){
			task.setWeekDays(null);
		}
		taskService.save(task);
		//添加完任务以后，要对站点进行从新分配
		CrawlerConfigManager.refreshInit();
		return "true";
	}
	/**
	 * 删除任务
	 * @return
	 * @author 
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String delete(String id[]) {
		taskService.deleteAll(Task.class,id );
		//删除任务以后，要对站点进行从新分配
		CrawlerConfigManager.refreshInit();
		return "true";
	}
	/**
	 * 检查任务名称是否存在
	 * @param task
	 * @return
	 */
	@RequestMapping("/checkTask")
	public @ResponseBody String checkTask(Task task) {
		return taskService.checkTask(task);
	}
	/**
	 * 菜单树形结构展示.提供xml
	 * 
	 * @return
	 */
	@RequestMapping("/treeXml")
	public void treeXml(HttpServletResponse response,Task task) {
		try {
			selectSites = task.getSites();
			StringBuilder strb = new StringBuilder();
			strb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			strb.append("<tree id=\"0\">");
			// 查找根节点根节点
			List<SiteCategory> siteCategorys = siteCategoryService.findAll();
			// 找到所有的一级节点,pid =1
			for(SiteCategory s:siteCategorys){
				strb.append(nodeXml(s));
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
	 * 返回一个节点的xml
	 * 
	 * @param node
	 * @return
	 */
	private String nodeXml(SiteCategory node) {
		StringBuilder strb = new StringBuilder();
		String xml = "<item text=\"" + node.getName() + "\" id=\"" + node.getId() + "\" open=\"0\" ";
		strb.append(xml);
		strb.append(" > ");
		List<Site> childrens = siteService.find(" from Site where siteCategoryId = '"+node.getId()+"'");
		node.setSites(childrens);
		int length = node.getSites() == null || node.getSites().size() <= 0 ? 0 : node.getSites().size();
		for (int i = 0; i < length; i++) {
				strb.append(nodeXml(node.getSites().get(i)));
		}
		strb.append("</item>");
		return strb.toString();
	}
	/**
	 * 拼接站点信息
	 * @param node
	 * @param selectedId
	 * @return
	 */
	private String nodeXml(Site node) {
		StringBuilder strb = new StringBuilder();
		String xml = "<item text=\"" + node.getName() + "\" id=\"" + node.getId() + "\" ";
		if(selectSites!=null && selectSites.indexOf(node.getId())!=-1){
			xml +="  checked=\"1\" ";
		}
		strb.append(xml);
		strb.append(" > ");
		strb.append("</item>");
		return strb.toString();
	}
	/**
	 * 全网监控词启用
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/use.do")
	public @ResponseBody String use(String id[]) {
		taskService.updateAll(Task.class,id,"isUse",1 );
		return "true";
	}
	/**
	 * 全网监控词禁用
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/noUse.do")
	public @ResponseBody String noUse(String id[]) {
		taskService.updateAll(Task.class,id,"isUse",0 );
		return "true";
	}
	
	/**
	 * 查询可运行任务
	 * @return
	 * @author chenlong
	 */
	@RequestMapping("/runningTasks.do")
	public @ResponseBody String runningTasks() {
		PageResult pageResult = new PageResult();
		PageResult result = taskService.findRunningTasks(new Date(), pageResult);
		List tasks = result.getRows();
		String string = "";
		for(int i = 0; i < tasks.size(); i++){
			Task task = (Task) tasks.get(i);
			String siteIds = task.getSites();
			if(ValidateObject.hasValue(siteIds)){
				String[] ids = siteIds.split(",");
				for(String id : ids){
					Site site = siteService.findById(Site.class, id);
					if(site != null && site.getStatue() != null && site.getStatue() == 1){
						string += task.getTaskName() + ",";
						break;
					}
				}
			}
		}
		if(string.length() > 0){
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}
}
