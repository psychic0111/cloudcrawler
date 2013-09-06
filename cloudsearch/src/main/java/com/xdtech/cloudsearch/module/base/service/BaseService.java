package com.xdtech.cloudsearch.module.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.cloudsearch.module.base.dao.BaseDAO;

/**
 * BaseService
 * 
 * @author Chang Fei
 */
@Service
@Transactional(rollbackFor = Exception.class)
public abstract class BaseService {
	
	/*** DAO*/
	@Autowired(required = true)
	private BaseDAO dao;

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	/**
	 * 添加
	 * 
	 * @param <T>
	 * @param obj
	 */
	public <T> void save(T obj) {
		getDao().save(obj);
	}
	/**
	 * 添加或修改
	 * 
	 * @param <T>
	 * @param obj
	 */
	public <T> void saveOrUpdate(T obj) {
		getDao().getTemplate().saveOrUpdate(obj);
	}

	/**
	 * 批量添加
	 * 
	 * @param <T>
	 * @param list
	 */
	public <T> void saveAll(Collection<T> list) {
		getDao().saveOrUpdateAll(list);
	}

	/**
	 * 删除对象
	 * 
	 * @param obj
	 */
	public void delete(Object obj) {
		getDao().delete(obj);
	}

	/**
	 * 根据指定的ID删除对象
	 * 
	 * @param <T>
	 * @param id
	 * @param clz
	 */
	public <T> void delete(Serializable id, Class<T> clz) {
		Object obj = getDao().findById(clz, id);
		getDao().delete(obj);
	}

	/**
	 * 根据 指定的多个ID删除对象
	 * 
	 * @param <T>
	 * @param ids
	 * @param clz
	 */
	public <T> void deleteAll( Class<T> clz,String ids[]) {
		String queryString  = "from "+clz.getName() +" where id in(";
		for(String id:ids){
			queryString += "'"+id+"',";
		}
		queryString = queryString.substring(0,queryString.length()-1)+")";
	    List<T> list =	find(queryString);
	    if(list.size()>0){
	    	deleteAll(list);
	    }
	}

	/**
	 * 删除多个对象
	 * 
	 * @param <T>
	 * @param list
	 */
	public <T> void deleteAll(Collection<T> list) {
		getDao().deleteAll(list);
	}

	/**
	 * 更新对象
	 * 
	 * @param obj
	 */
	public void update(Object obj) {
		getDao().update(obj);
	}

	/**
	 * 通过查询语句查询
	 * 
	 * @param <T>
	 * @param queryString
	 * @return
	 */
	public <T> List<T> find(String queryString) {
		return  getDao().findList(queryString);
	}
	
	/**
	 * 通过查询语句和一个或多个参数查询
	 * @param <T>
	 * @param queryString
	 * @param params
	 * @return
	 */
	public <T> List<T> find(String queryString,Object ... params){
		return getDao().findList(queryString,params);
	}

	/**
	 * 查询全部
	 * 
	 * @param <T>
	 * @param clz
	 * @return
	 */
	public <T> List<T> findAll(Class<T> clz) {
		return getDao().findAll(clz);
	}
	
	/**
	 * 根据model实例查询
	 * 
	 * @param <T> 实例
	 * @param firstResult 记录开始
	 * @param maxResults 记录数量
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> findByExample(T t, int firstResult, int maxResults) {
		return getDao().getTemplate().findByExample(t, firstResult, maxResults);
	}
	
	/**
	 * 通过ID查询
	 * 
	 * @param <T>
	 * @param clz
	 * @param id
	 * @return
	 */
	public <T> T findById(Class<?> clz, Serializable id) {
		return getDao().findById(clz, id);
	}

	/**
	 * 分页查询
	 * 
	 * @param <T>
	 * @param queryString
	 * @param page
	 * @param rows
	 * @return
	 */
	public <T> List<T> findByPage(String queryString, int page, int rows) {

		return getDao().findList(queryString, (page - 1) * rows, rows);
	}

	/**
	 * 查询总数
	 * 
	 * @param clz
	 * @return
	 */
	public Long getCount(Class<?> clz) {
		String queryString = "select count(*) from " + clz.getName();
		return (Long) getDao().findList(queryString).get(0);
	}
	/**
	 * 查询总数
	 * @param sql 查询的sql
	 * @return
	 */
	public Long getCount(String sql) {
		String queryString = "select count(*) "+sql.substring(0,sql.indexOf("order")); 
		return (Long) getDao().findList(queryString).get(0);
	}
	/**
	 * 合并更新
	 * 
	 * @param <T>
	 * @param obj
	 */
	public <T> void merge(T obj) {
		getDao().merge(obj);
	}
	/**
	 * 根据 指定的多个ID对象,修改某一个属性
	 * 
	 * @param <T>
	 * @param ids
	 * @param clz
	 */
	public <T> void updateAll( Class<T> clz,String ids[],String property,Object value) {
		String queryString  = " update "+clz.getName() +" set "+property+" =? where id in(";
		for(String id:ids){
			queryString += "'"+id+"',";
		}
		queryString = queryString.substring(0,queryString.length()-1)+")";
		getDao().getTemplate().bulkUpdate(queryString, value);
	}

}
