package com.xdtech.cloudsearch.webservice.xdservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.bean.DataBase;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Region;
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteCategory;
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteProxy;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Template;
import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerService;
import com.xdtech.cloudsearch.module.sitecrawler.service.DataBaseService;
import com.xdtech.cloudsearch.module.sitecrawler.service.RegionService;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteCategoryService;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteProxyService;
import com.xdtech.cloudsearch.module.sitecrawler.service.TemplateService;
import com.xdtech.cloudsearch.util.AppContextUtils;
/**
 * 数据缓存类
 * @author Administrator
 *
 */
public class DataCache {
	private static RegionService regionService = AppContextUtils.getBean("regionService");
	private static TemplateService templateService = AppContextUtils.getBean("templateService");
	private static CrawlerService crawlerService = AppContextUtils.getBean("crawlerService");
	private static SiteCategoryService siteCategoryService = AppContextUtils.getBean("siteCategoryService");
	private static DataBaseService dataBaseService = AppContextUtils.getBean("dataBaseService");
	private static SiteProxyService siteProxyService = AppContextUtils.getBean("siteProxyService");
	private static Map<String, Region> regionMaps = new HashMap<String, Region>();
	private static Map<String,SiteCategory> siteCategoryMap = new HashMap<String, SiteCategory>();
	private static List<Crawler> activeCrawlerList = new ArrayList<Crawler>();
	private static List<Crawler> detahCrawlerList = new ArrayList<Crawler>();
	private static Map<String,Crawler> crawlerMap = new HashMap<String,Crawler>();
	private static Map<String, Template> templateMaps = new HashMap<String, Template>();
	private static Map<String, SiteProxy> proxyMaps = new HashMap<String, SiteProxy>();
	public static void init(){
		initRegion();
		initSiteCategory();
		initCrawler();
		initTemplate();
		initSiteProxy();
	}
	public static void initRegion() {
		regionMaps.clear();
		// 获取国家，省，区，县的列表
		List<Region> regions = regionService.findAll();
		for (Region r : regions) {
			regionMaps.put(r.getId(), r);
		}
	}
	/**
	 * 初始化代理
	 */
	public static void initSiteProxy(){
		proxyMaps.clear();
		List<SiteProxy> siteProxys = siteProxyService.findAll();
		for(SiteProxy sp:siteProxys){
			proxyMaps.put(sp.getId(), sp);
		}
	}
	public static void initSiteCategory() {
		// 站点分类
		List<SiteCategory> siteCategorys = siteCategoryService.findAll();
		for (SiteCategory sc : siteCategorys) {
			siteCategoryMap.put(sc.getId(), sc);
		}
	}
	
	public synchronized static void initCrawler(){
		activeCrawlerList = crawlerService.find("from Crawler where status = 1 and isDeath = 1");
		detahCrawlerList = crawlerService.find("from Crawler where status = 1 and isDeath = 0");
		List<Crawler> crawlerList = crawlerService.find("from Crawler ");
		crawlerMap.clear();
		if(crawlerList!=null){
			for(Crawler crawler :crawlerList){
				crawlerMap.put(crawler.getCode(), crawler);
				DataBase dataBase = dataBaseService.getDao().getTemplate().get(DataBase.class, crawler.getDatabaseId());
				crawler.setDataBase(dataBase);
			}
		}
		
	}

	public static List<Crawler> getActiveCrawlerList() {
		List<Crawler> activeCrawlerList = new ArrayList<Crawler>();
		activeCrawlerList.addAll(DataCache.activeCrawlerList);
		return activeCrawlerList;
	}

	/**
	 * 选择一个全网爬虫
	 * @return
	 */
	public static Crawler getWebCrawler(){
		if(activeCrawlerList!=null){
			for(Crawler crawler :activeCrawlerList){
				if(crawler.getCrawlerType().contains("网爬虫")){
					return crawler;
				}
			}
		}
		return null;
	}
	/**
	 * 得到处于活跃状态的网页爬虫
	 * @return
	 */
	public static List<Crawler> getActiveWebCrawlerList() {
		List<Crawler> activeCrawlerList = new ArrayList<Crawler>();
		for(Crawler crawler :DataCache.activeCrawlerList){
			if(crawler.getCrawlerType().indexOf("网页爬虫")!=-1){
				activeCrawlerList.add(crawler);
			}
		}
		return activeCrawlerList;
	}
	public static void initTemplate(){
		List<Template> templates = templateService.find(" from Template ");
		for(Template t:templates){
			templateMaps.put(t.getId(), t);
		}
	}

	public static List<Crawler> getDetahCrawlerList() {
		List<Crawler> detahCrawlerList = new ArrayList<Crawler>();
		detahCrawlerList.addAll(DataCache.detahCrawlerList);
		return detahCrawlerList;
	}

	public static Map<String, Region> getRegionMaps() {
		return regionMaps;
	}

	public static Map<String, SiteCategory> getSiteCategoryMap() {
		return siteCategoryMap;
	}
	public static Map<String, Template> getTemplateMaps() {
		return templateMaps;
	}
	
	/**
	 * 返回爬虫信息
	 * @param crawlerCode
	 * @return
	 */
	public static Crawler getCrawler(String crawlerCode){
		return crawlerMap.get(crawlerCode);
	}
	/**
	 * 获取代理信息
	 * @param id
	 * @return
	 */
	public static SiteProxy getSiteProxy(String id){
		return proxyMaps.get(id);
	}
}
