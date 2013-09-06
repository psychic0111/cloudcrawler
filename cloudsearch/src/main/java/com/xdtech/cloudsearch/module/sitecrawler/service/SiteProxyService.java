package com.xdtech.cloudsearch.module.sitecrawler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteCategory;
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteProxy;

/**
 * 代理-Service
 * 
 * @author WangWei
 * 2013-06-05
 */
@Service(value="siteProxyService")
public class SiteProxyService  extends BaseService {
	/**查询所有代理
     * @author WangWei
     * @return
     */
	public List<SiteProxy> findAll(){
		String queryString = "from SiteProxy ";
		List<SiteProxy> list = find(queryString);
		return list;
	}
	/**检查代理是否存在
	 * 
	 * @param SiteProxy
     * @author WangWei
     * @return
     */
	public String checkSiteProxy(SiteProxy siteProxy){
		StringBuffer queryString =new StringBuffer("select count(*) from SiteProxy sp where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		if(siteProxy.getId()!=null&&!siteProxy.getId().equals("")){
			queryString.append(" and sp.id<>?  ");
			list.add(siteProxy.getId());
		}
		if(siteProxy.getName()!=null&&!siteProxy.getName().equals("")){
			queryString.append("and sp.name=?  ");
			list.add(siteProxy.getName());
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
	 * @param siteProxy
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPage(SiteProxy siteProxy,PageResult pageResult){
		String queryString  = "from SiteProxy where 1 = 1";
		if(null!=siteProxy){
			if(null!=siteProxy.getName()&&!"".equals(siteProxy.getName())){
				queryString =queryString +" and name like '%"+siteProxy.getName()+"%'";
			}
			if(null!=siteProxy.getProxyIp()&&!"".equals(siteProxy.getProxyIp())){
				queryString =queryString +" and proxyIp like '%"+siteProxy.getProxyIp()+"%'";
			}
			if(null!=siteProxy.getProxyPort()&&0!=siteProxy.getProxyPort()){
				queryString =queryString +" and proxyPort = "+siteProxy.getProxyPort()+"";
			}
		}
		List<SiteProxy> list=null;
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
