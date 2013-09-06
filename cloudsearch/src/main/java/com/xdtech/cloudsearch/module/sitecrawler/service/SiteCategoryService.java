package com.xdtech.cloudsearch.module.sitecrawler.service;

import java.util.ArrayList;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteCategory;
import com.xdtech.cloudsearch.module.system.bean.User;

/**
 * 站点分类-Service
 * 
 * @author WangWei
 * 2013-06-04
 */
@Service(value="siteCategoryService")
@Transactional(rollbackFor=Exception.class)
public class SiteCategoryService extends BaseService {
	/**查询所有站点分类
     * @author WangWei
     * @return
     */
	public List<SiteCategory> findAll(){
		String queryString = "from SiteCategory ";
		List<SiteCategory> list = find(queryString);
		return list;
	}
	/**检查站点分类是否存在
	 * 
	 * @param siteCategory
     * @author WangWei
     * @return
     */
	public String checkSiteCategory(SiteCategory siteCategory){
		StringBuffer queryString =new StringBuffer("select count(*) from SiteCategory sc where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		if(siteCategory.getId()!=null&&!siteCategory.getId().equals("")){
			queryString.append(" and sc.id<>?  ");
			list.add(siteCategory.getId());
		}
		if(siteCategory.getName()!=null&&!siteCategory.getName().equals("")){
			queryString.append("and sc.name=?  ");
			list.add(siteCategory.getName());
		}
		List<Object> userlist = getDao().findList(queryString.toString(),list.toArray());
		Long c= (Long) userlist.get(0);
		if(c>0){
			return "false";
		}
		return "true";
	}
	public PageResult findByPage(SiteCategory siteCategory,PageResult pageResult){
		String queryString  = "from SiteCategory where 1= 1";
		if(null!=siteCategory){
			if(null!=siteCategory.getName()&&!"".equals(siteCategory.getName())){
				queryString =queryString +" and name like '%"+siteCategory.getName()+"%'";
			}
		}
		List<SiteCategory> list=null;
		queryString = queryString +" order by operateTime desc ";
		try {
			list = findByPage(queryString, pageResult.getPageNo(), pageResult.getPageSize());
			for(SiteCategory l:list){
				l.setCountSite(find("from Site where siteCategoryId='"+l.getId()+"'").size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult.setRows(list);
		pageResult.setTotal(getCount(queryString));
		return pageResult;
	}
}
