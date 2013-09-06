package com.xdtech.cloudsearch.module.sitecrawler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Plugin;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Region;
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteCategory;

/**
 * 区域-Service
 * 
 * @author WangWei
 * 2013-06-06
 */
@Service(value="regionService")
public class RegionService extends BaseService{
	/**查询所有插件
     * @author WangWei
     * @return
     */
	public List<Region> findAll(){
		String queryString = " from Region where deleted = 1 ";
		List<Region> list = find(queryString);
		return list;
	}
	/**检查区域是否存在
	 * 
	 * @param region
     * @author WangWei
     * @return
     */
	public String checkRegion(Region region){
		StringBuffer queryString =new StringBuffer("select count(*) from Region r where deleted = 1 ");
		List<Object> list =new ArrayList<Object>();
		if(region.getId()!=null&&!region.getId().equals("")){
			queryString.append(" and r.id<>?  ");
			list.add(region.getId());
		}
		if(region.getAreaName()!=null&&!region.getAreaName().equals("")){
			queryString.append("and r.areaName=?  ");
			list.add(region.getAreaName());
		}
		if(null!=region.getParentId()&&!"".equals(region.getParentId())){
			queryString.append(" and parentId =? ");
			list.add(region.getParentId());
		}
		System.out.println(queryString.toString());
		List<Object> userlist = getDao().findList(queryString.toString(),list.toArray());
		Long c= (Long) userlist.get(0);
		if(c>0){
			return "false";
		}
		return "true";
	}
	/**
	 * 分页
	 * @param region
	 * @author WangWei
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPage(Region region,PageResult pageResult){
		String queryString  = "from Region where deleted = 1";
		if(null!=region){
			if(null!=region.getAreaName()&&!"".equals(region.getAreaName())){
				queryString =queryString +" and areaName like '%"+region.getAreaName()+"%'";
			}
			if(null!=region.getParentId()&&!"".equals(region.getParentId())){
				queryString =queryString +" and parentId = '"+region.getId()+"'";
			}
		}
		List<Region> list=null;
		queryString = queryString +" order by sortNo";
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
	 * 分页
	 * @param region
	 * @author WangWei
	 * @param pageResult
	 * @return
	 */
	public PageResult getAll(Region region,PageResult pageResult){
		pageResult.setPageSize(10000);
		String queryString  = "from Region where deleted = 1";
		if(null!=region){
			if(null!=region.getAreaName()&&!"".equals(region.getAreaName())){
				queryString =queryString +" and areaName like '%"+region.getAreaName()+"%'";
			}
			if(null!=region.getParentId()&&!"".equals(region.getParentId())){
				queryString =queryString +" and parentId = '"+region.getParentId()+"'";
			}
		}
		List<Region> list=null;
		queryString = queryString +" order by sortNo";
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
	 * 分页
	 * @param region
	 * @author WangWei
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPageC(Region region,PageResult pageResult){
		String queryString  = "from Region where deleted = 1";
		if(null!=region){
			if(null!=region.getId()&&!"".equals(region.getId())){
				queryString =queryString +" and parentId = '"+region.getId()+"'";
			}
		}
		List<Region> list=null;
		queryString = queryString +" order by sortNo";
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
	 * 分页
	 * @param region
	 * @author WangWei
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPageTree(Region region,PageResult pageResult){
		String queryString  = "from Region where deleted = 1";
		if(null!=region){
			if(null!=region.getAreaName()&&!"".equals(region.getAreaName())){
				queryString =queryString +" and areaName like '%"+region.getAreaName()+"%'";
			}
			if(null!=region.getId()&&!"".equals(region.getId())){
				queryString =queryString +" and parentId = '"+region.getId().replace("_1", "").replace("_0", "")+"'";
			}
		}
		List<Region> list=null;
		queryString = queryString +" order by sortNo";
		try {
			list = findByPage(queryString, pageResult.getPageNo(), pageResult.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult.setRows(list);
		pageResult.setTotal(getCount(queryString));
		return pageResult;
	}
	/**查询所有的国家级的地区
     * @author WangWei
     * @return
     */
	public List<Region> findCountry(){
		String queryString = " from Region where deleted = 1 and parentId = '0' ";
		List<Region> list = find(queryString);
		return list;
	}
	/**查询所有的子节点
     * @author WangWei
     * @return
     */
	public List<Region> findChildrenByParentId(String parentId){
		String queryString = " from Region where deleted = 1 and parentId = '"+parentId+"' ";
		List<Region> list = find(queryString);
		return list;
	}
	/**查找该区域下有没有子节点
	 * 
	 * @param region
     * @author WangWei
     * @return
     */
	public boolean checkRegionExistsChildren(String id){
		StringBuffer queryString =new StringBuffer("select count(*) from Region r where deleted = 1 and parentId = '"+id+"'");
		List<Object> userlist = getDao().findList(queryString.toString());
		Long c= (Long) userlist.get(0);
		if(c>0){
			return true;
		}
		return false;
	}
}
