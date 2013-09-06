package com.xdtech.cloudsearch.module.webcrawler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Plugin;
import com.xdtech.cloudsearch.module.webcrawler.bean.Engine;
/**
 * 搜索引擎-Service
 * 
 * @author WangWei
 * 2013-06-24
 */
@Service(value="engineService")
public class EngineService extends BaseService{
	/**查询所有搜索引擎
     * @author WangWei
     * @return
     */
	public List<Engine> findAll(Integer use){
		String queryString = "from Engine where isUse = "+use;
		List<Engine> list = find(queryString);
		return list;
	}
	/**搜索引擎是否存在
	 * 
	 * @param Engine
     * @author WangWei
     * @return
     */
	public String checkEngine(Engine engine){
		StringBuffer queryString =new StringBuffer("select count(*) from Engine s where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		if(engine.getId()!=null&&!engine.getId().equals("")){
			queryString.append(" and s.id<>?  ");
			list.add(engine.getId());
		}
		if(engine.getEnginName()!=null&&!engine.getEnginName().equals("")){
			queryString.append("and s.enginName=?  ");
			list.add(engine.getEnginName());
		}
		if(engine.getEnginCode()!=null&&!engine.getEnginCode().equals("")){
			queryString.append("and s.enginCode=?  ");
			list.add(engine.getEnginCode());
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
	 * @param Engine
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPage(Engine engine,PageResult pageResult){
		String queryString  = "from Engine where 1 = 1";
		if(null!=engine){
			if(null!=engine.getEnginName()&&!"".equals(engine.getEnginName())){
				queryString =queryString +" and enginName like '%"+engine.getEnginName()+"%'";
			}
		}
		List<Engine> list=null;
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
}
