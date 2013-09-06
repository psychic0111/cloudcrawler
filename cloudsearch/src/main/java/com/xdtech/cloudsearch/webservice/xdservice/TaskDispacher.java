package com.xdtech.cloudsearch.webservice.xdservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;
import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerService;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteService;
import com.xdtech.cloudsearch.module.task.bean.Task;
import com.xdtech.cloudsearch.module.task.service.TaskService;
import com.xdtech.cloudsearch.util.AppContextUtils;

public class TaskDispacher {
	private static CrawlerService crawlerService = AppContextUtils.getBean("crawlerService");
	private static SiteService siteService = AppContextUtils.getBean("siteService");
	private static TaskService taskService = AppContextUtils.getBean("taskService");
	private static TaskDispacher taskDispacher = new TaskDispacher();
	private List<Crawler> crawlerList = new ArrayList<Crawler>();
	private Set<String> crawlerCodeSet = new HashSet<String>();
	private Set<String> siteIdSet = new HashSet<String>();
	private Set<String> taskIdSet = new HashSet<String>();
	private List<Task> taskList = new ArrayList<Task>();
	private List<Site> siteList = new ArrayList<Site>();
	private Map<String,List<SiteTask>> crawlerWorkMap = new HashMap<String,List<SiteTask>>();
	private TaskDispacher(){
		
	}
	
	public synchronized static TaskDispacher getInstance(){
		return taskDispacher;
	}
	
	/**
	 * 添加爬虫
	 * @param crawlerList
	 */
	public synchronized void addCrawlerList(List<Crawler> crawlerList){
		boolean change = innerAddCrawlerList(crawlerList);
		if(change){
			reassignTasks();
		}
	}
	
	/**
	 * 移除爬虫
	 * @param crawler
	 */
	public  synchronized void removeCrawlerList(List<Crawler> crawlerList){
		boolean change = false;
		if(!crawlerList.isEmpty()){
			for(Crawler crawler : crawlerList){
				for(Crawler c : this.crawlerList){
					if(c.getCode().equals(crawler.getCode())){
						crawlerList.remove(c);
						crawlerCodeSet.remove(c.getCode());
						change = true;
					}
				}
			}
		}
		if(change){
			reassignTasks();
		}
	}
	
	private  boolean  innerAddCrawlerList(List<Crawler> crawlerList){
		boolean change = false;
		if(crawlerList!=null){
			for(Crawler crawler : crawlerList){
				if(crawler.getCrawlerType().indexOf("网页爬虫")!=-1 && !crawlerCodeSet.contains(crawler.getCode())){
					this.crawlerList.add(crawler);
					crawlerCodeSet.add(crawler.getCode());
					change = true;
				}
			}
		}
		return change;
	}
	
	private  boolean  innerAddTaskList(List<Task> taskList){
		boolean change = false;
		if(taskList!=null){
			for(Task task:taskList){
				if(!taskIdSet.contains(task.getId())){
					this.taskList.add(task);
					taskIdSet.add(task.getId());
					change = true;
				}
			}
		}
		return change;
	}
	
	private boolean innerAddSiteList(List<Site> allCrawlerSiteList) {
		boolean change = false;
		if(allCrawlerSiteList!=null){
			for(Site site : allCrawlerSiteList){
				if(!siteIdSet.contains(site.getId())){
					siteList.add(site);
					siteIdSet.add(site.getId());
					change = true;
				}
			}
		}
		return change;
	}
	
	/**
	 * 分配任务
	 * @param crawlerList	爬虫列表
	 * @param taskList	执行的任务列表
	 * @param allCrawlerSiteList 所有可抓取站点列表
	 */
	private void assignTasks(){
		crawlerWorkMap.clear();
		Map<String,List<Task>> siteTaskMap = getSiteTaskMap();
		List<SiteTask> siteTaskList =  matchTaskAndSite(siteTaskMap);
		for(Crawler crawler :crawlerList){
			crawlerWorkMap.put(crawler.getCode(), new ArrayList<SiteTask>());
		}
		System.out.println("=========="+crawlerWorkMap.keySet());
		if(!crawlerWorkMap.isEmpty()){
			for(SiteTask st : siteTaskList){
				String code = chooseCrawler();
				st.setCrawlerCode(code);
				crawlerWorkMap.get(code).add(st);
			}
		}
		System.out.println("=========="+crawlerWorkMap);
	}
	
	private void clearCache(){
		crawlerList.clear();
		crawlerCodeSet.clear();
		siteList.clear();
		siteIdSet.clear();
		taskList.clear();
		taskIdSet.clear();
	}
	
	private void cacheData(List<Crawler> crawlerList,List<Task> taskList,List<Site> allCrawlerSiteList){
		innerAddCrawlerList(crawlerList);
		innerAddTaskList(taskList);
		innerAddSiteList(allCrawlerSiteList);
	}
	

	/**
	 * 重新分配任务
	 */
	public synchronized void reassignTasks(){
		//获取所有要执行的任务
		List<Task> tasks = taskService.find(" from Task where isUse = 1 ");
		List<Site> sites = siteService.find("from Site where statue =1 ");
		List<Crawler> crawlers = crawlerService.find("from Crawler where status = 1 and isDeath = 1");
		clearCache();
		cacheData(crawlers,tasks,sites);
		assignTasks();
	}
	
	/**
	 * 选择爬虫
	 * @return
	 */
	private String chooseCrawler(){
		int min = -1;
		String code = "";
		for(String key : crawlerWorkMap.keySet()){
			if(min== -1 || crawlerWorkMap.get(key).size()<min){
				min = crawlerWorkMap.get(key).size();
				code = key;
			}
		}
		return code;
	}
	
	
	public void addTasks(List<Task> taskList) {
		boolean change = this.innerAddTaskList(taskList);
		if(change){
			assignTasks();
		}
	}

	public void removeTasks(List<Task> taskList) {
		boolean change = false;
		for(Task task:taskList){
			for(Task cacheTask:this.taskList){
				if(task.getId().equals(cacheTask.getId())){
					this.taskList.remove(cacheTask);
					taskIdSet.remove(cacheTask.getId());
					change = true;
					break;
				}
			}
		}
		if(change){
			assignTasks();
		}
	}

	public synchronized void addSites(List<Site> siteList) {
		boolean change = this.innerAddSiteList(siteList);
		if(change){
			assignTasks();
		}
	}

	public void removeSies(List<Site> siteList) {
		boolean change = false;
		for(Site site:siteList){
			for(Site cacheSite:this.siteList){
				if(site.getId().equals(cacheSite.getId())){
					this.siteList.remove(cacheSite);
					siteIdSet.remove(cacheSite.getId());
					change = true;
					break;
				}
			}
		}
		if(change){
			assignTasks();
		}
	}
	
	
	/**
	 * 把每一个任务和站点对应
	 * @param taskList
	 * @param allCrawlerSiteList
	 * @return
	 */
	private Map<String,List<Task>>  getSiteTaskMap(){
		Map<String,List<Task>> siteTaskMap = new HashMap<String,List<Task>>();
		for(Site site:siteList){
			siteTaskMap.put(site.getId(), new ArrayList<Task>());
		}
		
		for(Task task:taskList){
			if(task.getIsAllSites()==0){
				for(Map.Entry<String,List<Task>> entry : siteTaskMap.entrySet()){
					entry.getValue().add(task);
				}
			}
			if(task.getSiteCategories()!=null && task.getSiteCategories().length()>0){
				for(String categoryId : task.getSiteCategories().split(",")){
					for(Site site:siteList){
						if(site.getSiteCategoryId().equals(categoryId)){
							siteTaskMap.get(site.getId()).add(task);
						}
					}
				}
				
			}
			if(task.getSites()!=null && task.getSites().length()>0){
				for(String siteId : task.getSites().split(",")){
					siteTaskMap.get(siteId).add(task);
				}
			}
		}
		return siteTaskMap;
	}
	
	/**
	 * 为每一个站点选择任务,选择执行频率高的任务
	 * @param siteTaskMap
	 * @return
	 */
	private List<SiteTask> matchTaskAndSite(Map<String, List<Task>> siteTaskMap) {
		List<SiteTask> siteTaskList = new ArrayList<SiteTask>(siteTaskMap.size());
		for(Map.Entry<String,List<Task>> entry : siteTaskMap.entrySet()){
			Task selectTask = chooseTask(entry.getKey(),entry.getValue());
			if(selectTask!=null){
				SiteTask siteTask = new SiteTask();
				siteTask.setSiteId(entry.getKey());
				siteTask.setTaskId(selectTask.getId());
				siteTaskList.add(siteTask);
			}
		}
		return siteTaskList;
	}
	
	/**
	 * 为一个站点选择一个任务,第二次精选
	 * @param siteId
	 * @param taskList
	 * @return
	 */
	private Task chooseTask(String siteId,List<Task> taskList){
		Task selectTask = null;
		if(taskList.size()==1){
			selectTask = taskList.get(0);
		}else if(taskList.size()>1){
			int min = Integer.MAX_VALUE;
			for(Task task : taskList){
				int minutes = 0;
				if(task.getClickType()==1){
					minutes = task.getClick()*60;
				}else{
					minutes = task.getClick();
				}
				if(min==-1 || minutes<min){
					min = minutes;
					selectTask = task;
				}
			}
		}
		return selectTask;
	}
	
	public List<Task> getCrawlerTask(String crawlerCode){
		List<Task> list = new ArrayList<Task>();
		List<SiteTask> stlist = crawlerWorkMap.get(crawlerCode);
		if(stlist!=null){
			for(SiteTask st:stlist){
				list.add(findTask(st.getTaskId()));
			}
			for(Task task:list){
				List<Site> siteList = new ArrayList<Site>();
				for(SiteTask st:stlist){
					if(st.getTaskId().equals(task.getId())){
						siteList.add(findSite(st.getSiteId()));
					}
				}
				task.setSiteList(siteList);
			}
		}
		return list;
	}

	private Task findTask(String taskId) {
		for(Task task:taskList){
			if(taskId.equals(task.getId())){
				return task;
			}
		}
		return null;
	}
	private Site findSite(String siteId) {
		for(Site site:siteList){
			if(siteId.equals(site.getId())){
				return site;
			}
		}
		return null;
	}
}
