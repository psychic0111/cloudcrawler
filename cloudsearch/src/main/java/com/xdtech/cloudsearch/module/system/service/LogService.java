package com.xdtech.cloudsearch.module.system.service;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.system.bean.Log;

/**
 * 日志Service
 * 
 * @author yuhao
 */
@Service(value="logService")
@Transactional(rollbackFor=Exception.class)
public class LogService extends BaseService{
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	/**查询所有日志（分页）
	 * 
     * @author wangwenxiang
     * @return
     */
	@Transactional(readOnly = true) 
	public PageResult findByPage(String actionUser,String startTime,String endTime,PageResult pageResult){
		StringBuffer queryString =new StringBuffer("from Log l where 1=1 ");
		StringBuffer appString =new StringBuffer("");
		List <Object> parmList=new  ArrayList<Object>();
		try { 
			if(actionUser!=null && !"".equals(actionUser)){
				appString.append(" and l.actionUser like '%"+actionUser+"%' ");
			}
			if(startTime!=null && !"".equals(startTime)){
				appString.append(" and l.actiontime>? ");
				parmList.add(sf.parse(startTime+" 00:00:00"));
			}
			if(endTime!=null && !"".equals(endTime)){
				appString.append(" and l.actiontime<? ");
				parmList.add(sf.parse(endTime+" 23:59:59"));
			}
			
			List<Log> list=null;
			list=getDao().findList(queryString.append(appString).append(" order by actiontime desc ").toString(), (pageResult.getPageNo() - 1) *  pageResult.getPageSize(), pageResult.getPageSize(),parmList.toArray());
			pageResult.setRows(list);
			List<Object> countlist = getDao().findList(new StringBuffer("select count(*) from Log l where 1=1 ").append(appString).toString(),parmList.toArray());
			Long c = (Long) countlist.get(0);
			pageResult.setTotal(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageResult;
	}
	/**
	 * 清空日志
	 * @return
	 */
	public String clearLog(String actionUser,String startTime ,String endTime){
		StringBuffer queryString =new StringBuffer("delete Log l where 1=1 ");
		List<Object> parmList= new  ArrayList<Object>();
		try {
			if(actionUser!=null && !"".equals(actionUser)){
				queryString.append(" and l.actionUser like '%"+actionUser+"%' ");
			}
			if(startTime!=null && !"".equals(startTime)){
				queryString.append(" and l.actiontime>? ");
				parmList.add(sf.parse(startTime));
			}
			if(endTime!=null && !"".equals(endTime)){
				queryString.append(" and l.actiontime<? ");
				parmList.add(sf.parse(endTime));
			}
			getDao().delete(queryString.toString(), parmList.toArray());
		} catch (ParseException e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
}
