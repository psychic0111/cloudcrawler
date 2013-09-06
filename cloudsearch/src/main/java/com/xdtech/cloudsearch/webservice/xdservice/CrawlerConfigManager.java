package com.xdtech.cloudsearch.webservice.xdservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Template;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteService;
import com.xdtech.cloudsearch.module.task.bean.Task;
import com.xdtech.cloudsearch.module.task.service.TaskService;
import com.xdtech.cloudsearch.util.AppContextUtils;
import com.xdtech.cloudsearch.ws.client.CrawlerClient;

public class CrawlerConfigManager {
	private static SiteService siteService = AppContextUtils.getBean("siteService");
	private static TaskService taskService = AppContextUtils.getBean("taskService");
	/**
	 * 保存站点和任务的对应关系
	 */
	private static Map<String, Task> siteIdsAndTaskIdsMap = new HashMap<String, Task>();
	/**
	 * 爬虫代码和爬虫对应关系集合
	 */
	private static Map<String, Crawler> codeCrawlers = new HashMap<String, Crawler>();
	/**
	 * 爬虫编码和任务列表对应关系
	 */
	private static Map<String, List<Task>> crawlerTaskMap = new HashMap<String, List<Task>>();
	/**
	 * 修改站点时用到的环境变量 1站点ID和爬虫代码对应关系的Map
	 */
	private static Map<String, String> siteIdsAndCrawlerCodeMapEdit = new HashMap<String, String>();
	/**
	 * 修改站点时用到的环境变量 1站点ID和任务ID对应关系的Map
	 */
	private static Map<String, String> siteIdsAndTaskIdsMapEdit = new HashMap<String, String>();
	
	/**
	 * 爬虫代码和站点对应关系
	 */
	private static Map<String, Set<String>> codeSitesMap = new HashMap<String, Set<String>>();
	
	public static void clearAll(){
		siteIdsAndTaskIdsMap.clear();
		codeCrawlers.clear();
		crawlerTaskMap.clear();
		siteIdsAndCrawlerCodeMapEdit.clear();
		siteIdsAndTaskIdsMapEdit.clear();
		codeSitesMap.clear();
	}
	/**
	 * 把每一个任务和站点对应
	 * @param taskList
	 * @param allCrawlerSiteList
	 * @return
	 */
	private static Map<String,List<Task>>  getSiteTaskMap(List<Site> siteList,List<Task> taskList){
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
						if(site.getSiteCategoryId().equals(categoryId) || DataCache.getSiteCategoryMap().get(categoryId).getName().equals(site.getSiteCategoryId())){
							siteTaskMap.get(site.getId()).add(task);
						}
					}
				}
				
			}
			if(task.getSites()!=null && task.getSites().length()>0){
				for(String siteId : task.getSites().split(",")){
					if(siteTaskMap.get(siteId)!=null){
						siteTaskMap.get(siteId).add(task);
					}
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
	private  static void matchTaskAndSite(Map<String, List<Task>> siteTaskMap) {
		for(Map.Entry<String,List<Task>> entry : siteTaskMap.entrySet()){
			if(entry.getValue().size()>0){
				chooseTask(entry.getKey(),entry.getValue());
			}
		}
	}
	
	/**
	 * 为一个站点选择一个任务
	 * @param siteId
	 * @param taskList
	 * @return
	 */
	private static void chooseTask(String siteId,List<Task> taskList){
		for(Task task:taskList){
			compareTaskClick(siteId,task);
		}
	}
	
	/**
	 * 获取可以抓取的站点
	 * @return
	 */
	private static List<Site> getUsedSiteList(){
		List<Site> sites = siteService.find("from Site where statue =1 order by level desc ");
		if(null != sites && sites.size() > 0){
			int length = sites.size();
			for(int i = 0;i < length; i ++){
				Site s = sites.get(i);
				setSiteOtherProperties(s);
			}
		}
		return sites;
	}
	/**
	 * 分配任务
	 */
	private static  void init(){
		System.out.println("分配任务");
		//清缓存
		clearAll();
		//获取所有要执行的任务
		List<Task> tasks = taskService.find(" from Task where isUse = 1 ");
		//获取所有要分配的站点
		List<Site> sites = getUsedSiteList();
		//找到站点和任务之间的关系
		Map<String,List<Task>> siteTaskMap = getSiteTaskMap(sites,tasks);
		matchTaskAndSite(siteTaskMap);
		//获取所有的可以启动的爬虫
		List<Crawler> crawlers = DataCache.getActiveWebCrawlerList();
		//将爬虫代码和爬虫进行一一对应
		for(Crawler c:crawlers){
			codeCrawlers.put(c.getCode(), c);
			codeSitesMap.put(c.getCode(),new HashSet<String>());
		}
		if(tasks!=null && !tasks.isEmpty() && sites!=null && !sites.isEmpty() && crawlers!=null && !crawlers.isEmpty()){
			//分配任务
			assignTask(sites,tasks,crawlers);
			//通知爬虫
			notifyCrawler(crawlers);
		}
	}
	/***
	 * 通知爬虫获取新状态
	 * @param crawlers
	 */
	private static void notifyCrawler(List<Crawler> crawlers) {
		for(Crawler crawler : crawlers){
			setConfigInfo(crawler);
		}
	}
	/**
	 * 真正的任务分配
	 * @param sites
	 * @param tasks
	 * @param crawlers
	 */
	private static void assignTask(List<Site> sites,List<Task> tasks,List<Crawler> crawlers){
		int i = 0;
		while (!sites.isEmpty()) {
			Site site = sites.remove(0);
			if(siteIdsAndTaskIdsMap.get(site.getId())==null){
				continue;
			}
			assignCrawler(site,crawlers.get(i).getCode(),siteIdsAndTaskIdsMap.get(site.getId()));
			i++;
			if (i == crawlers.size()) {
				i = 0;
			}
		}
	}
	
	/**
	 * 设置对应关系
	 * @param site 站点
	 * @param crawlerCode 选择的爬虫
	 * @param task 选择分配的任务
	 */
	private static void assignCrawler(Site site,String crawlerCode,Task task){
		//将爬虫代码和站点进行对应
		if(codeSitesMap.get(crawlerCode)==null){
			codeSitesMap.put(crawlerCode,new HashSet<String>());
		}
		site.setSiteProxy(DataCache.getSiteProxy(site.getProxyId()));
		codeSitesMap.get(crawlerCode).add(site.getId());
		//保存站点ID和爬虫编码的对应关系
		siteIdsAndCrawlerCodeMapEdit.put(site.getId(), crawlerCode);
		//保存站点ID和任务ID的对应关系
		siteIdsAndTaskIdsMapEdit.put(site.getId(), task.getId());
		//将爬虫和任务进行对应
		if(crawlerTaskMap.get(crawlerCode)==null){
			crawlerTaskMap.put(crawlerCode,new ArrayList<Task>());
		}
		List<Task> taskList = crawlerTaskMap.get(crawlerCode);
		boolean addTask = true;
		int length = taskList.size();
		//判断如果新加的任务在爬虫中存在，就把该站点添加到该任务中
		for(int j = 0;j<length;j++){
			if(taskList.get(j).getId().equals(task.getId())){
				List<Site> siteListInTask = taskList.get(j).getSiteList();
				removeSite(site,siteListInTask);
				siteListInTask.add(site);
				addTask = false;
				break;
			}
		}
		if(addTask){
			Task newtask = task.simpleCloneNewObj();
			removeSite(site,newtask.getSiteList());
			newtask.getSiteList().add(site);
			taskList.add(newtask);
		}
	}
	/**
	 * 移除列表中的站点
	 * @param site
	 * @param siteList
	 */
	private static void removeSite(Site site,List<Site> siteList){
		if(siteList!=null){
			for(int i=0;i<siteList.size();i++){
				Site siteInList = siteList.get(i);
				if(siteInList.getId().equals(site.getId())){
					siteList.remove(i);
				}
			}
		}
	}
	/**
	 * 给站点的其他属性赋值
	 * @param s
	 * @param siteCategoryMap
	 * @param regionMaps
	 * @param templateMaps
	 */
	public static void setSiteOtherProperties(Site s){
		s.setSiteCategoryId(DataCache.getSiteCategoryMap().get(s.getSiteCategoryId()).getName());
		s.setContryId((s.getContryId() == null||s.getContryId().equals(""))?"":DataCache.getRegionMaps().get(s.getContryId()).getAreaName());
		s.setProvinceId((s.getProvinceId() == null||s.getProvinceId().equals(""))?"":DataCache.getRegionMaps().get(s.getProvinceId()).getAreaName());
		s.setCityId((s.getCityId() == null||s.getCityId().equals(""))?"":DataCache.getRegionMaps().get(s.getCityId()).getAreaName());
		s.setAreaId((s.getAreaId() == null||s.getAreaId().equals(""))?"":DataCache.getRegionMaps().get(s.getAreaId()).getAreaName());
		s.setSiteProxy((s.getProxyId() == null||s.getProxyId().equals(""))?null:DataCache.getSiteProxy(s.getProxyId()));
		setTemplateForSite(s);
	}
	/**
	 * 给站点设置模板
	 * @param site
	 */
	public static void setTemplateForSite(Site site){
		if(null != site && null != site.getTemplateIds() && !"".equals(site.getTemplateIds())){
			String[] sids = site.getTemplateIds().split(",");
			List<Template> templates = new ArrayList<Template>();
			for(String sid:sids){
				if(DataCache.getTemplateMaps().containsKey(sid)){
					templates.add(DataCache.getTemplateMaps().get(sid));
				}
			}
			site.setTemplates(templates);
		}
	}
	public static void compareTaskClick(String sid,Task task){
		if(siteIdsAndTaskIdsMap.containsKey(sid)){
			//比较两个任务的执行频率，使用执行评论小的任务
			Task t1 = siteIdsAndTaskIdsMap.get(sid);
			long long1 = getLongTime(t1);
			long long2 = getLongTime(task);
			if(long1 > long2){
				siteIdsAndTaskIdsMap.put(sid, task);
			}
		}else{
			siteIdsAndTaskIdsMap.put(sid, task);
		}
	}
	/**
	 * 合并任务中的站点
	 * @param task
	 */
	public static List<String> mergeSiteids1(Task task){
		List<String> allSiteids = new ArrayList<String>();
		if(task.getIsAllSites() == 0){
			List<String> siteids = siteService.find("select id from Site where statue = 1");
			//将站点和任务的对应关系保存在siteIdsAndTaskIdsMap
			for(String sid:siteids){
				compareTaskClick(sid,task);
			}
			return siteids;
		}
		if(null != task.getSiteCategories() && !"".equals(task.getSiteCategories())){
			String[] sids = task.getSiteCategories().split(",");
			StringBuffer sb = new StringBuffer("");
			for(String sid:sids){
				if(!sb.toString().equals("")){
					sb.append(",");
				}
				sb.append("'").append(sid).append("'");
			}
			List<String> siteids = siteService.find("select id from Site where statue = 1 and siteCategoryId in ("+sb.toString()+")");
			allSiteids.addAll(siteids);
		}
		if(null != task.getSites() && !"".equals(task.getSites())){
			String[] sids = task.getSites().split(",");
			for(String sid:sids){
				allSiteids.add(sid);
			}
		}
		//将站点和任务的对应关系保存在siteIdsAndTaskIdsMap
		for(String sid:allSiteids){
			compareTaskClick(sid,task);
		}
		return allSiteids;
	}
	/**
	 * 获取任务的执行频率
	 * @param t
	 * @return
	 */
	public static long getLongTime(Task task){
		long longtime = 0;
		if(task.getClickType() == 1){
			longtime = task.getClick() * 60 * 60 * 1000;
		}else if(task.getClickType() == 2){
			longtime = task.getClick() * 60 * 1000;
		}
		return longtime;
	}
	
	private static List<Task> findTask(Site site){
		List<Task> taskList = new ArrayList<Task>();
		Task crawlerAllTask = getCrawlerAllTask();
		if(crawlerAllTask!=null){
			taskList.add(crawlerAllTask);
		}
		List<Task> crawlerSiteTaskList = getCrawlerSiteTask(site);
		if(crawlerSiteTaskList!=null){
			taskList.addAll(crawlerSiteTaskList);
		}
		return taskList;
	}
	/**
	 * 当新加站点以后，需要将新加的站点分配到节点上，
	 * 1.如果有任务为采集所有站点，采集所有站点，则该站点需要加入的节点上 
	 * 2.如果有任务为按分类采集，并且新加站点为没有个任务的分类下，则需要将该站点加入的节点上进行采集
	 * 3.如果该站点没有任务执行，则不加入节点进行采集
	 * @param site
	 */
	private static void addSiteSyn(Site site){
		List<Task> taskList = findTask(site);
		if(taskList!=null && !taskList.isEmpty()){
			Map<String, List<Task>> siteTaskMap = new HashMap<String,List<Task>>();
			siteTaskMap.put(site.getId(), taskList);
			matchTaskAndSite(siteTaskMap);
			setSiteOtherProperties(site);
			String crawlercode = orderCodeSitesMapEntry();
			assignCrawler(site,crawlercode,siteIdsAndTaskIdsMap.get(site.getId()));
			//调用爬虫接口，从新加载客户端
			Crawler crawler = codeCrawlers.get(crawlercode);
			setConfigInfo(crawler);
		}
	}
	/**
	 * 修改调度端的状态
	 */
	public static void updateServerStatus(){
		CrawlerConnectionStatusManage.crawlerConnectionStatus = 1;
	}
	
	/**
	 * 删除缓存数据
	 * @param siteId
	 */
	public static void remove(String siteId){
		//站点ID和爬虫代码对应关系的Map
		siteIdsAndCrawlerCodeMapEdit.remove(siteId);
		//站点ID和任务ID对应关系的Map
		siteIdsAndTaskIdsMapEdit.remove(siteId);
	}
	/**
	 * 通知爬虫从新加载配置信息
	 * @param crawler
	 */
	public static void setConfigInfo(Crawler crawler){
		try {
			CrawlerClient.getCrawlerServiceInterface(crawler).setCrawlerConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateServerStatus();
	}
	/**
	 * 当新加站点以后，需要将新加的站点分配到节点上，
	 * 1.如果有任务为采集所有站点，采集所有站点，则该站点需要加入的节点上 
	 * 2.如果有任务为按分类采集，并且新加站点为没有个任务的分类下，则需要将该站点加入的节点上进行采集
	 * 3.如果该站点没有任务执行，则不加入节点进行采集
	 * 4.如果该站点之前已经被加入任务则修改对应关系，并通知节点，否则与新增节点相同
	 * @param site
	 */
	public static synchronized void addOrEditSiteSyn(Site site){
		realAddOrEditSiteSyn(Arrays.asList(new Site[]{site}));
	}
	
	/**
	 * 当新加站点以后，需要将新加的站点分配到节点上，
	 * 1.如果有任务为采集所有站点，采集所有站点，则该站点需要加入的节点上 
	 * 2.如果有任务为按分类采集，并且新加站点为没有个任务的分类下，则需要将该站点加入的节点上进行采集
	 * 3.如果该站点没有任务执行，则不加入节点进行采集
	 * 4.如果该站点之前已经被加入任务则修改对应关系，并通知节点，否则与新增节点相同
	 * @param sites
	 */
	public static synchronized void addOrEditSiteSyn(List<Site> sites){
		realAddOrEditSiteSyn(sites);
	}
	
	/**
	 * 具体操作实现
	 * @param sites
	 */
	private static void realAddOrEditSiteSyn(final List<Site> sites){
		new Thread(new Runnable() {
			public void run() {
				if(sites!=null){
					List<String> delSiteIdList = new ArrayList<String>();
					for(Site site : sites){
						if (!codeCrawlers.isEmpty() && site.getStatue() == 1) {
							String siteId = site.getId();
							String taskId = siteIdsAndTaskIdsMapEdit.get(siteId);
							if (taskId != null) {
								setSiteOtherProperties(site);
								assignCrawler(site,siteIdsAndCrawlerCodeMapEdit.get(siteId),siteIdsAndTaskIdsMap.get(siteId));
								setConfigInfo(codeCrawlers.get(siteIdsAndCrawlerCodeMapEdit.get(siteId)));
							} else {
								addSiteSyn(site);
							}
						}else if(!codeCrawlers.isEmpty() && site.getStatue() == 0) {
							delSiteIdList.add(site.getId());
						}
					}
					if(!delSiteIdList.isEmpty()){
						deleteSitesSyn(delSiteIdList);
					}
				}
			}
		}).start();
	}
	/**
	 * 修改爬虫信息以后，需要通知爬虫从新加载信息
	 * @param crawler
	 */
	public synchronized static void editCrawlerSyn(final Crawler crawler){
		new Thread(new Runnable() {
			public void run() {
				codeCrawlers.remove(crawler.getCode());
				codeCrawlers.put(crawler.getCode(), crawler);
				setConfigInfo(crawler);
			}
		}).start();
		
	}
	
	public synchronized static void deleteSitesSyn(final List<String> siteIdList){
		new Thread(new Runnable() {
			public void run() {
				Set<String> crawlerCodeSet=new HashSet<String>();
				for(String siteId:siteIdList){
					List<String> crawlerCodeList = deleteSiteSyn(siteId);
					if(crawlerCodeList!=null){
						crawlerCodeSet.addAll(crawlerCodeList);
					}
				}
				if(!crawlerCodeSet.isEmpty()){
					for(String crawlerCode : crawlerCodeSet){
						setConfigInfo(codeCrawlers.get(crawlerCode));
					}
				}
			}
		}).start();
		
	}
	/**
	 * 当站点删除以后，需要更新爬虫端的任务配置，并且要对任务进行从新分配
	 * @param site
	 */
	private static List<String> deleteSiteSyn(String siteId){
		Crawler crawler = removeRelattion(siteId);
		if(crawler!=null){
			//从新分配站点
			Entry<String,Integer> topEntry = orderCodeSitesMapEntryTopAndBottom(1);
			Entry<String,Integer> bottomEntry = orderCodeSitesMapEntryTopAndBottom(0);
			Integer top = topEntry.getValue();
			Integer bottom = bottomEntry.getValue();
			Integer mul = bottom - top ;
			if(mul == 0){
				//setConfigInfo(crawler);
			}else if(mul == 1){
				//setConfigInfo(crawler);
			}else if(mul == 2){
				Crawler topCrawler = codeCrawlers.get(topEntry.getKey());
				Crawler bottomCrawler = codeCrawlers.get(bottomEntry.getKey());
				List<Task> topCrawlerTasks = crawlerTaskMap.get(topEntry.getKey());
				List<Task> bottomCrawlerTasks = crawlerTaskMap.get(bottomEntry.getKey());
				Site removeSite = null;
				if(topCrawlerTasks.size() > 0){
					removeSite = bottomCrawlerTasks.get(0).getSiteList().get(0);
					Task task = siteIdsAndTaskIdsMap.get(removeSite.getId());
					removeRelattion(removeSite.getId());
					assignCrawler(removeSite, topCrawler.getCode(), task);
				}
				return Arrays.asList(new String[]{topCrawler.getCode(),bottomCrawler.getCode(),crawler.getCode()});
			}
			return Arrays.asList(new String[]{crawler.getCode()});
		}
		return null;
	}
	
	private static Crawler removeRelattion(String siteId){
		String taskId = siteIdsAndTaskIdsMapEdit.get(siteId);
		String crawlerCode = siteIdsAndCrawlerCodeMapEdit.get(siteId);
		Crawler crawler = codeCrawlers.get(crawlerCode);
		List<Task> tasks = crawlerTaskMap.get(crawlerCode);
		if(null != tasks){
			int length = tasks.size();
			for(int j = 0;j<length;j++){
				Task t = tasks.get(j);
				if(t.getId().equals(taskId)){
					if(!t.getSiteList().isEmpty()){
						length = t.getSiteList().size();
						for(int i = 0;i < length ; i ++){
							Site s = t.getSiteList().get(i);
							if(s.getId().equals(siteId)){
								t.getSiteList().remove(i);
								break;
							}
						}
					}
					tasks.set(j, t);
					break;
				}
			}
		}
		//删除爬虫代码和站点配置关系-开始
		if(codeSitesMap.get(crawlerCode)!=null){
			codeSitesMap.get(crawlerCode).remove(siteId);
		}
		remove(siteId);
		return crawler;
	}
	/**
	 * 检查是否有任务要采集所有的站点
	 * @return
	 */
	private static Task getCrawlerAllTask(){
		String queryString = " from Task where isUse = 1 and isAllSites = 0";
		List<Task> tasks = taskService.find(queryString);
		if(null != tasks && tasks.size() > 0){
			return tasks.get(0);
		}
		return null;
	}
	/**
	 * 检查是否有任务要采集该站点所在的分类或者该站点
	 * @return
	 */
	private static List<Task> getCrawlerSiteTask(Site site){
		String queryString = " from Task where isUse = 1 and isAllSites = 1 and (siteCategories like '%"+site.getSiteCategoryId()+"%' or sites like '%"+site.getId()+"%' )  ";
		return taskService.find(queryString);
	}
	/**
	 * 找到需要将站点添加到那个爬虫
	 * @return
	 */
	private static String orderCodeSitesMapEntry(){
		Map<String,Integer> map = new HashMap<String,Integer>(); 
		Integer[] sites = new Integer[codeSitesMap.size()];
		int i = 0;
		for(Entry<String, Set<String>> entry:codeSitesMap.entrySet()){
			map.put(entry.getKey(), entry.getValue().size());
			sites[i] = entry.getValue().size();
			i ++;
		}
		Arrays.sort(sites);
		Integer last = sites[0];
		for(Entry<String, Integer> entry:map.entrySet()){
			if(entry.getValue() == last){
				return entry.getKey();
			}
		}
		return null;
	}
	/**
	 * 找到需要将站点添加到那个爬虫
	 * @return
	 */
	private static Entry<String,Integer> orderCodeSitesMapEntryTopAndBottom(int top){
		Map<String,Integer> map = new HashMap<String,Integer>(); 
		Integer[] sites = new Integer[codeSitesMap.size()];
		int i = 0;
		for(Entry<String, Set<String>> entry:codeSitesMap.entrySet()){
			map.put(entry.getKey(), entry.getValue().size());
			sites[i] = entry.getValue().size();
			i ++;
		}
		Arrays.sort(sites);
		Integer last = null;
		if(top == 1){
			last = sites[0];
		}else{
			last = sites[sites.length -1];
		}
		for(Entry<String, Integer> entry:map.entrySet()){
			if(entry.getValue() == last){
				return entry;
			}
		}
		return null;
	}
	/**
	 * 从新分配数据
	 */
	public static synchronized void refreshInit(){
		new Thread(new Runnable() {
			public void run() {
				DataCache.initCrawler();
				CrawlerConfigManager.init();
				CrawlerConnectionStatusManage.crawlerConnectionStatus = 0;
			}
		}).start();
	}
	
	public static List<Task> getCrawlerTask(String crawlerCode){
		return crawlerTaskMap.get(crawlerCode);
	}
}
