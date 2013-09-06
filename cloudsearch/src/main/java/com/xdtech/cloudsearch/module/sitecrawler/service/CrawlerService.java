package com.xdtech.cloudsearch.module.sitecrawler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.bean.DataBase;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;
import com.xdtech.cloudsearch.module.task.bean.Task;
/**
 * 爬虫数据库-Service
 * 
 * @author WangWei
 * 2013-06-20
 */
@Service(value="crawlerService")
public class CrawlerService extends BaseService{
	@Autowired(required = true)
	private DataBaseService dataBaseService;
	/**爬虫数据库是否存在
	 * 
	 * @param Crawler
     * @author WangWei
     * @return
     */
	public String checkCrawler(Crawler crawler){
		StringBuffer queryString =new StringBuffer("select count(*) from Crawler s where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		if(crawler.getId()!=null&&!crawler.getId().equals("")){
			queryString.append(" and s.id<>?  ");
			list.add(crawler.getId());
		}
		if(crawler.getName()!=null&&!crawler.getName().equals("")){
			queryString.append("and s.name=?  ");
			list.add(crawler.getName());
		}
		List<Object> userlist = getDao().findList(queryString.toString(),list.toArray());
		Long c= (Long) userlist.get(0);
		if(c>0){
			return "false";
		}
		return "true";
	}
	/**
	 * 爬虫代码是否存在
	 * @param crawler
	 * @return
	 */
	public String checkCode(Crawler crawler){
		StringBuffer queryString =new StringBuffer("select count(*) from Crawler s where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		if(crawler.getId()!=null&&!crawler.getId().equals("")){
			queryString.append(" and s.id<>?  ");
			list.add(crawler.getId());
		}
		if(crawler.getCode()!=null&&!crawler.getCode().equals("")){
			queryString.append("and s.code=?  ");
			list.add(crawler.getCode());
		}
		List<Object> userlist = getDao().findList(queryString.toString(),list.toArray());
		Long c= (Long) userlist.get(0);
		if(c>0){
			return "false";
		}
		return "true";
	}
	/**
	 * 分页
	 * @param Crawler
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPage(Crawler crawler,PageResult pageResult){
		String queryString  = "from Crawler where 1 = 1";
		if(null!=crawler){
			if(null!=crawler.getName()&&!"".equals(crawler.getName())){
				queryString =queryString +" and name like '%"+crawler.getName()+"%'";
			}
		}
		List<Crawler> list=null;
		queryString = queryString +" order by operateTime desc ";
		try {
			list = findByPage(queryString, pageResult.getPageNo(), pageResult.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult.setRows(list);
		pageResult.setTotal(getCount(queryString));
		return pageResult;
	}
	
	/**
	 * 根据爬虫实例查询
	 * @param task
	 * @param pageResult
	 * @return
	 */
	public PageResult findCrawlersByExample(Crawler crawler, PageResult pageResult){
		List<Crawler> list=null;
		try {
			list = findByExample(crawler, 1, pageResult.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult.setRows(list);
		return pageResult;
	}
	
	/**根据爬虫代码，获取爬虫配置信息
     * @author WangWei
     * @return
     */
	public Crawler getCrawlerByCode(String code){
		String queryString = "from Crawler where code = '"+code+"'";
		List<Crawler> list = find(queryString);
		if(null != list && list.size() > 0){
			Crawler crawler = list.get(0);
			DataBase dataBase = dataBaseService.getDao().getTemplate().get(DataBase.class, crawler.getDatabaseId());
			crawler.setDataBase(dataBase);
			return crawler;
		}
		return null;
	}
	/**获取所有启用并且停止的爬虫
     * @author WangWei
     * @return
     */
	public List<Crawler> getAllCrawlerUsedAndNoStop(){
		String queryString = "from Crawler where status = 1";
		List<Crawler> list = find(queryString);
		return list;
	}
	
	/**获取所有启用的爬虫
     * @author WangWei
     * @return 启用的爬虫集合
     */
	public List<Crawler> getAllCrawler(){
		String queryString = "from Crawler where status = 1";
		List<Crawler> list = find(queryString);
		return list;
	}
	/**检查是否所有爬虫都启动了
     * @author WangWei
     * @return
     */
	public boolean checkAllCrawlerStart(){
		String queryString = "from Crawler where status = 1 and crawlerStatus <> 2";
		List<Crawler> list = find(queryString);
		//queryString = "from Crawler where status = 1";
		//List<Crawler> list1 = find(queryString);
		if(null == list || list.isEmpty()){
			return true;
		}
		return false;
	}
	/**检查是否有爬虫启动了
     * @author WangWei
     * @return
     */
	public boolean checkCrawlerStart(){
		String queryString = "from Crawler where status = 1 and crawlerStatus = 2";
		List<Crawler> list = find(queryString);
		if(null != list && list.size() > 0){
			return true;
		}
		return false;
	}
	/**获取已启用未死的爬虫
     * @author WangWei
     * @return
     */
	public List<Crawler> getRunningCrawlers(){
		String queryString = "from Crawler where status = 1 and isDeath = 1 ";
		List<Crawler> list = find(queryString);
		return list;
	}
	/**获取已启用已死掉的爬虫
     * @author WangWei
     * @return
     */
	public List<Crawler> getDeathCrawlers(){
		String queryString = "from Crawler where status = 1 and isDeath = 0 ";
		List<Crawler> list = find(queryString);
		return list;
	}
	/**获取已启用未死的全网爬虫
     * @author WangWei
     * @return
     */
	private Crawler getWebCrawler(){
		String queryString = "from Crawler where status = 1 and crawlerType like '%全网爬虫%' ";
		List<Crawler> list = find(queryString);
		if(null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
