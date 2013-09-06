package com.xdtech.cloudsearch.module.webcrawler.service;

import java.util.ArrayList;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.webcrawler.bean.Keyword;
import com.xdtech.cloudsearch.util.ValidateObject;

/**
 * 全网监控词-Service
 * 
 * @author WangWei
 * 2013-06-04
 */
@Service(value="keywordService")
@Transactional(rollbackFor=Exception.class)
public class KeywordService extends BaseService{
	/**检查全网监控词是否存在
	 * 
	 * @param Keyword
     * @author WangWei
     * @return
     */
	public String checkKeyword(Keyword keyword){
		StringBuffer queryString =new StringBuffer("select count(*) from Keyword k where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		if(keyword.getId()!=null&&!keyword.getId().equals("")){
			queryString.append(" and k.id<>?  ");
			list.add(keyword.getId());
		}
		if(keyword.getName()!=null&&!keyword.getName().equals("")){
			queryString.append("and k.name=?  ");
			list.add(keyword.getName());
		}
		if(keyword.getWords()!=null&&!keyword.getWords().trim().equals("")){
			queryString.append("and k.words=?  ");
			list.add(keyword.getWords().trim());
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
	 * @param keyword
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPage(Keyword keyword,PageResult pageResult){
		String queryString  = "from Keyword where 1 =1";
		if(null!=keyword){
			if(null!=keyword.getName()&&!"".equals(keyword.getName())){
				queryString =queryString +" and name like '%"+keyword.getName()+"%'";
			}
			if(ValidateObject.hasValue(keyword.getWords())){
				queryString =queryString +" and words like '%"+keyword.getWords()+"%'";
			}
		}
		List<Keyword> list=null;
		queryString = queryString +" order by createTime desc ";
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
