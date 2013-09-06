package com.xdtech.cloudsearch.module.sitecrawler.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.sitecrawler.bean.CrawlerLog;

/**
 * 日志管理-Service
 * 
 * @author WangWei
 * 2013-08-08
 */
@Service(value="crawlerlogService")
@Transactional(rollbackFor=Exception.class)
public class CrawlerLogService extends BaseService{
	/**检查日志管理是否存在
	 * 
	 * @param crawlerlog
     * @author WangWei
     * @return
     */
	public String checkCrawlerLog(CrawlerLog crawlerlog){
		StringBuffer queryString =new StringBuffer("select count(*) from CrawlerLog k where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		if(crawlerlog.getId()!=null&&!crawlerlog.getId().equals("")){
			queryString.append(" and k.id<>?  ");
			list.add(crawlerlog.getId());
		}
		List<Object> listbeans = getDao().findList(queryString.toString(),list.toArray());
		Long c= (Long) listbeans.get(0);
		if(c>0){
			return "false";
		}
		return "true";
	}
	/**
	 * 日志管理分页
	 * @param crawlerlog
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPage(CrawlerLog crawlerlog,PageResult pageResult){
		String queryString  = "from CrawlerLog where 1 =1";
		if(null!=crawlerlog){
			if(null!=crawlerlog.getCrawlerCode()&&!"".equals(crawlerlog.getCrawlerCode())){
				queryString =queryString +" and crawlerCode = '"+crawlerlog.getCrawlerCode()+"'";
			}
			if(null!=crawlerlog.getUrl()&&!"".equals(crawlerlog.getUrl())){
				queryString =queryString +" and url like '%"+crawlerlog.getUrl().trim()+"%'";
			}
			if(null!=crawlerlog.getSiteName()&&!"".equals(crawlerlog.getSiteName())){
				queryString = queryString +" and siteName like '%"+crawlerlog.getSiteName().trim()+"%'";
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(null != crawlerlog.getStartTime() && null == crawlerlog.getEndTime()){
				String date = format.format(crawlerlog.getStartTime());
				String startDate = date + " 00:00:00";
				String endDate = date + " 23:23:59";
				queryString += " and crawlerTime between '"+startDate+"' and '"+endDate+"'";
			}
			if(null != crawlerlog.getEndTime() && null == crawlerlog.getStartTime()){
				String date = format.format(crawlerlog.getEndTime());
				String startDate = date + " 00:00:00";
				String endDate = date + " 23:23:59";
				queryString += " and crawlerTime between '"+startDate+"' and '"+endDate+"'";
			}
			if(null != crawlerlog.getEndTime() && null != crawlerlog.getStartTime()){
				String sdate = format.format(crawlerlog.getStartTime());
				String edate = format.format(crawlerlog.getEndTime());
				String startDate = sdate + " 00:00:00";
				String endDate = edate + " 23:23:59";
				queryString += " and crawlerTime between '"+startDate+"' and '"+endDate+"'";
			}
		}
		List<CrawlerLog> list=null;
		queryString = queryString +" order by crawlerTime desc ";
		try {
			list = findByPage(queryString, pageResult.getPageNo(), pageResult.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult.setRows(list);
		pageResult.setTotal(getCount(queryString));
		return pageResult;
	}
}
