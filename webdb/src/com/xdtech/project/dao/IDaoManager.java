/*
 * $Id: IDaoManager.java 421119 2007-03-07 00:49:11Z jaddy $
 *
 * Copyright 2006-2007 Beijing Xdtech Inc.
 *
 * All rights reserved. 北京线点科技有限公司
 */

package com.xdtech.project.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xdtech.project.util.dao.DCriteriaPageSupport;
import com.xdtech.project.util.log.LogUtil;

/**
 * <p>
 * Title: 北京线点科技有限公司 数据访问类
 * </p>
 * <p>
 * Description: 北京线点科技有限公司 数据访问类, 通过 Spring 管理， 增强的
 * HibernateDaoSupport ， * 封装Hibernate ， 统一的数据访问接口 , 含分页
 * </p>
 * <p>
 * Copyright: xdtech ,ltd Copyright (c) 2007
 * </p>
 * <p>
 * Company: 北京线点科技有限公司
 * </p>
 * 
 * @author jaddy0302 date 2007-03-07 00:10:39
 * @version 1.0
 */
public class IDaoManager<T, P, PK extends Serializable> extends HibernateDaoSupport implements GeneraDAO<T, P, PK> {

	/**
	 * <p>
	 * 持久化业务逻辑数据对象 ，无返回值
	 * </p>
	 */
	public void saveIObject(final T object) {
		getHibernateTemplate().save(object);
	}

	/**
	 * <p>
	 * 持久化业务逻辑数据对象，新增或者更新 ，无返回值.应该使用saveIObject(final T
	 * object)或者updateIObject(T object)代替
	 * </p>
	 * 
	 * @param object
	 *            T 业务逻辑数据对象
	 */
	@Deprecated
	public void saveOrUpdateIObject(final T object) {
		getHibernateTemplate().saveOrUpdate(object);
	}

	/**
	 * <p>
	 * 持久化操作， 删除数据库中的数据记录
	 * </p>
	 * 
	 * @param object
	 *            T
	 */
	public void deleteIObject(final T object) {
		getHibernateTemplate().delete(object);
	}

	/**
	 * <p>
	 * 根据主键获取对象，建议使用findAllByCriteria(final DetachedCriteria
	 * detachedCriteria)代替
	 * </p>
	 * 
	 * @param iClass
	 *            Class
	 * @param iPK
	 *            PK
	 * @return T
	 */
	@Deprecated
	public T getIObjectByPK(final Class iClass, final PK iPK) {
		try {
			return (T) getHibernateTemplate().load(iClass, iPK);
		} catch (Throwable e) {
			LogUtil.error(e);
		}
		return null;
	}

	/**
	 * 更新 数据
	 * 
	 * @param object
	 *            T
	 */
	public void updateIObject(T object) {
		getHibernateTemplate().update(object);
	}

	/**
	 * @param hSQL
	 * @param paramNames
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int execByHQL(final String hSQL, final String paramNames, final T[] t) {
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				Query query = session.createQuery(hSQL);
				query.setParameterList(paramNames, t);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 传入的数据对象类型查找数据
	 * 
	 * @param iClass
	 *            Class
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllByIObjectCType(final Class iClass) {
		return getHibernateTemplate().find("from " + iClass.getName());
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByIObjectCType(final Class iClass, final int page, final int pageSize) {
		return findPageByCriteria(DetachedCriteria.forClass(iClass), pageSize, page);
	}

	/**
	 * Hibernate 配置 查询 HQL
	 * 
	 * @param iNameQuery
	 *            String
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(final String iNameQuery) {
		return getHibernateTemplate().findByNamedQuery(iNameQuery);
	}

	/**
	 * Hibernate 配置 查询 HQL
	 * 
	 * @param hSQL
	 *            String
	 * @return List
	 */
	public int execByHQL(final String hSQL) {
		return getHibernateTemplate().bulkUpdate(hSQL);
	}

	/**
	 * 执行hql
	 */
	public int execByHQL(final String hSQL, T[] t) {
		return getHibernateTemplate().bulkUpdate(hSQL, t);
	}

	/**
	 * HQL 查询 ， 带参数
	 * 
	 * @param iQuery
	 *            String
	 * @param param
	 *            P
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(final String iQuery, final P param) {
		return getHibernateTemplate().findByNamedQuery(iQuery, param);
	}

	/**
	 * HQL查询， 带多个参数
	 * 
	 * @param iQuery
	 *            String
	 * @param params
	 *            P[]
	 * @return List
	 */
	public List<T> findByNamedQuery(final String iQuery, final P[] params) {
		return getHibernateTemplate().findByNamedQuery(iQuery, params);
	}

	/**
	 * HQL 查询 ，构造查询 语句
	 * 
	 * @param iQuery
	 *            String
	 * @return List
	 */
	public List<T> findByQuery(final String iQuery) {
		return getHibernateTemplate().find(iQuery);
	}

	/**
	 * 构造HQL 查询语句， 带参数
	 * 
	 * @param iQuery
	 *            String
	 * @param param
	 *            P
	 * @return List
	 */
	public List<T> findByQuery(final String iQuery, final P param) {
		return getHibernateTemplate().find(iQuery, param);
	}

	/**
	 * @param iQuery
	 * @param param
	 * @return
	 */
	public List<T> findByQuery(final String iQuery, final P[] param) {
		return getHibernateTemplate().find(iQuery, param);
	}

	/**
	 * 构造DetachedCriteria ， 带分页信息
	 * 
	 * @param detachedCriteria
	 *            DetachedCriteria
	 * @return DCriteriaPageSupport
	 */
	public DCriteriaPageSupport<T> findPageByCriteria(final DetachedCriteria detachedCriteria) {
		return findPageByCriteria(detachedCriteria, DCriteriaPageSupport.IPAGESIZE, 0);
	}

	/**
	 * 
	 */
	public DCriteriaPageSupport<T> findPageByCriteria(final DetachedCriteria detachedCriteria, final int page) {
		return findPageByCriteria(detachedCriteria, DCriteriaPageSupport.IPAGESIZE, page);
	}

	/**
	 * DetachedCriteria 带分页信息 ，指定开始位置
	 * 
	 * @param detachedCriteria
	 *            DetachedCriteria
	 * @param pageSize
	 *            int
	 * @param page
	 *            int
	 * @return DCriteriaPageSupport
	 */
	public DCriteriaPageSupport<T> findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int page) {
		return findPageByCriteria(detachedCriteria, pageSize, (page > 0) ? (page - 1) * pageSize : page * pageSize, true);
	}

	/**
	 * DetachedCriteria 带分页信息 ，指定开始位置
	 * 
	 * @param detachedCriteria
	 *            DetachedCriteria
	 * @param pageSize
	 *            int
	 * @param startIndex
	 *            int
	 * @return DCriteriaPageSupport
	 */
	@SuppressWarnings("unchecked")
	private DCriteriaPageSupport<T> findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int startIndex, boolean isPage) {

		return (DCriteriaPageSupport) this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
				criteria.setProjection(null);
				DCriteriaPageSupport ps = new DCriteriaPageSupport(criteria.setFirstResult(startIndex).setMaxResults(pageSize).list(), totalCount, pageSize, startIndex);
				return ps;
			}
		});
	}

	/**
	 * DetachedCriteria 带分页信息 ，指定开始位置
	 * 
	 * @param detachedCriteria
	 *            DetachedCriteria
	 * @param pageSize
	 *            int
	 * @param startIndex
	 *            int
	 * @return DCriteriaPageSupport
	 */
	@SuppressWarnings("unchecked")
	public List<T> findPageByCriteriaNoTotal(final DetachedCriteria detachedCriteria, final int pageSize, final int startIndex, final boolean isPage) {
		return (DCriteriaPageSupport) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				if (isPage) {
					criteria.setFirstResult(startIndex);
					criteria.setMaxResults(pageSize);
				}
				List list = criteria.list();
				Set set = new HashSet(list);
				List list2 = new ArrayList(set);
				return new DCriteriaPageSupport(list2, -1, pageSize, startIndex);
			}
		});
	}

	/**
	 * detachedCriteria 查询
	 * 
	 * @param detachedCriteria
	 *            DetachedCriteria
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.list();
			}
		});
	}

	/**
	 * detachedCriteria 查询所有记录数
	 * 
	 * @param detachedCriteria
	 *            DetachedCriteria
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Object count = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
		});
		if (count instanceof Number) {
			return ((Number) count).intValue();
		}
		return 0;
	}

	/**
	 * 带分页信息的执行hql的查询
	 */
	@SuppressWarnings("unchecked")
	public List<T> hqlList(final String sql, final int startIndex, final int pageSize) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(sql);
				if (startIndex > 0) {
					query.setFirstResult(startIndex);
				}
				query.setMaxResults(pageSize);
				Iterator iterator = query.list().iterator();
				List clazzList = new ArrayList();
				while (iterator.hasNext()) {
					clazzList.add(iterator.next());
				}
				return clazzList;
			}
		});
	}

	/**
	 * @param sql
	 * @param params
	 * @param types
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> hqlListWithParam(final String sql, final Object[] params, final Type[] types, final int startIndex, final int pageSize) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(sql);
				query.setParameters(params, types);
				if (startIndex > 0) {
					query.setFirstResult(startIndex);
				}
				query.setMaxResults(pageSize);
				Iterator iterator = query.list().iterator();
				List clazzList = new ArrayList();
				while (iterator.hasNext()) {
					clazzList.add(iterator.next());
				}
				return clazzList;
			}

		});
	}

	/**
	 * @param sql
	 * @param params
	 * @param types
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> hqlListWithParam(final String sql, final Object[] params, final Type[] types) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {
				Query query = session.createQuery(sql);
				query.setParameters(params, types);

				Iterator iterator = query.list().iterator();
				List clazzList = new ArrayList();
				while (iterator.hasNext()) {
					clazzList.add(iterator.next());
				}
				return clazzList;
			}

		});
	}

	/**
	 * @param sql
	 * @param params
	 */
	@SuppressWarnings("unchecked")
	public void doSql(final String sql, final Object[] params) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Connection conn = session.connection();
				java.sql.PreparedStatement pstm = null;
				try {
					pstm = conn.prepareStatement(sql);
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							pstm.setObject(i + 1, params[i]);
						}
					}
					pstm.execute();
				} catch (Exception e) {
					throw new HibernateException(e);
				} finally {
					try {
						pstm.close();
					} catch (SQLException e) {
					}
				}
				return null;
			}
		});
	}

	/**
	 * 批量执行sql
	 * 
	 * @param sql
	 * @param dataList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void doBySQLBatch(final String sql, final List<List> dataList) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				java.sql.PreparedStatement ps = null;
				java.sql.Connection conn = session.connection();
				try {
					conn.setAutoCommit(false);
					ps = conn.prepareStatement(sql);
					for (int i = 0; i < dataList.size(); i++) {
						for (int j = 0; j < dataList.get(i).size(); j++) {
							ps.setObject(j + 1, dataList.get(i).get(j));
						}
						ps.addBatch();
					}
					ps.executeBatch();
					conn.commit();
				} catch (Exception ex) {
					try {
						conn.rollback();
					} catch (SQLException e) {
						LogUtil.error(e);
					}
					throw new HibernateException(ex);
				} finally {
					try {
						if (ps != null) {
							ps.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException ex1) {
						LogUtil.error(ex1.getMessage());
					}
				}
				return null;
			}
		});
	}

	/**
	 * sql语句操作数据库
	 * 
	 * @param sql
	 * @param params
	 */
	@SuppressWarnings("unchecked")
	public void doOprateSql(final String sql, final Object[] params) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Connection conn = session.connection();
				java.sql.PreparedStatement pstm = null;
				try {
					pstm = conn.prepareStatement(sql);
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							pstm.setObject(i + 1, params[i]);
						}
					}
					pstm.executeUpdate();
				} catch (Exception e) {
					LogUtil.error(e);
				} finally {
					try {
						pstm.close();
					} catch (SQLException e) {
						LogUtil.error(e.getMessage());
					}
				}

				return null;

			}
		});
	}

	/**
	 * 获得总数
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public int getCountByHql(final String hql, final Object[] params) {
		List list = getHibernateTemplate().find(hql, params);
		int count = 0;
		long c = (Long) list.get(0);
		count = (int) c;
		return count;
	}

	/**
	 * @param hql
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public DCriteriaPageSupport<T> findPageByHql(final String hql, final int pageSize, final int page) {
		return findPageByHql(hql, null, pageSize, (page > 0) ? (page - 1) * pageSize : page * pageSize, true);
	}

	/**
	 * @param hql
	 * @param params
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public DCriteriaPageSupport<T> findPageByHql(final String hql, final Object[] params, final int pageSize, final int page) {
		return findPageByHql(hql, params, pageSize, (page > 0) ? (page - 1) * pageSize : page * pageSize, true);
	}

	/**
	 * @param hql
	 * @param params
	 * @param pageSize
	 * @param startIndex
	 * @param isPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DCriteriaPageSupport<T> findPageByHql(final String hql, final Object[] params, final int pageSize, final int startIndex, boolean isPage) {
		return (DCriteriaPageSupport) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				String countHql = "select count(*) " + removeSelect(removeOrders(hql));
				Query query = session.createQuery(countHql);
				if (params != null && params.length > 0) {
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
					}
				}
				long totalCount2 = ((Long) query.list().get(0));
				int totalCount = (int) totalCount2;
				query = session.createQuery(hql);
				if (params != null && params.length > 0) {
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
					}
				}
				if (startIndex > 0) {
					query.setFirstResult(startIndex);
				}
				query.setMaxResults(pageSize);
				Iterator iterator = query.list().iterator();
				List clazzList = new ArrayList();
				while (iterator.hasNext()) {
					clazzList.add(iterator.next());
				}
				DCriteriaPageSupport ps = new DCriteriaPageSupport(clazzList, totalCount, pageSize, startIndex);
				return ps;
			}

		});
	}

	/**
	 * 执行hql 带参数的查询
	 * 
	 * @param hql
	 * @param params
	 * @param paramNames
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<T> findPageByHql(final String hql, final Object[] params, final String[] paramNames, final int pageSize, final int page) {
		return findPageByHql(hql, params, paramNames, pageSize, (page > 0) ? (page - 1) * pageSize : page * pageSize, true);
	}

	/**
	 * @param hql
	 * @param params
	 * @param paramNames
	 * @param pageSize
	 * @param startIndex
	 * @param isPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findPageByHql(final String hql, final Object[] params, final String[] paramNames, final int pageSize, final int startIndex, boolean isPage) {
		return (DCriteriaPageSupport) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				String countHql = "select count(*) " + removeSelect(removeOrders(hql));
				Query query = session.createQuery(countHql);
				if (params != null && params.length > 0) {
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							if (params[i] instanceof Collection) {
								query.setParameterList(paramNames[i], (Collection) params[i]);
							} else {
								query.setParameter(paramNames[i], params[i]);
							}

						}

					}
				}
				long totalCount2 = ((Long) query.list().get(0));
				int totalCount = (int) totalCount2;
				query = session.createQuery(hql);
				if (params != null && params.length > 0) {
					if (params != null) {
						if (params != null) {
							for (int i = 0; i < params.length; i++) {
								if (params[i] instanceof Collection) {
									query.setParameterList(paramNames[i], (Collection) params[i]);
								} else {
									query.setParameter(paramNames[i], params[i]);
								}

							}

						}
					}
				}
				if (startIndex > 0) {
					query.setFirstResult(startIndex);
				}
				query.setMaxResults(pageSize);
				Iterator iterator = query.list().iterator();
				List clazzList = new ArrayList();
				while (iterator.hasNext()) {
					clazzList.add(iterator.next());
				}
				DCriteriaPageSupport ps = new DCriteriaPageSupport(clazzList, totalCount, pageSize, startIndex);
				return ps;
			}

		});
	}

	/**
	 * @param hql
	 * @param name
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllyHqlWithList(final String hql, final String name, final T[] params) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(hql);
				if (params != null && params.length > 0) {
					query.setParameterList(name, params);
				}
				return query.list();
			}

		});
	}

	/**
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllyHql(final String hql, final Object[] params) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(hql);
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.list();
			}

		});
	}

	/**
	 * 去除hql的select 子句，
	 */
	private static String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句
	 */
	private static String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * @param sql
	 * @param fields
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getAllBySQL(final String sql, final String[] fields) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				List list = new ArrayList();
				java.sql.ResultSet rs = null;
				java.sql.Statement statement = null;
				java.sql.Connection conn = session.connection();
				try {
					Map form = null;
					statement = conn.createStatement();
					rs = statement.executeQuery(sql);
					while (rs.next()) {
						form = new HashMap();

						for (int i = 0; i < fields.length; i++) {
							form.put(fields[i], rs.getObject(fields[i]) == null ? "" : rs.getObject(fields[i]));
						}
						list.add(form);
					}
				} catch (SQLException ex) {
					LogUtil.error(ex);
				} finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (statement != null) {
							statement.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException ex1) {
						LogUtil.error(ex1.getMessage());
					}
				}
				return list;
			}
		});
	}

	/**
	 * @param sql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getAllPreBySQL(final String sql, final Object[] values) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				List list = new ArrayList();
				java.sql.ResultSet rs = null;
				java.sql.PreparedStatement statement = null;
				java.sql.Connection conn = session.connection();
				try {
					Map form = null;
					statement = conn.prepareStatement(sql);
					int length = (values != null ? values.length : 0);
					for (int i = 1; i <= length; i++) {
						statement.setObject(i, values[i - 1]);
					}
					rs = statement.executeQuery();
					ResultSetMetaData ms = rs.getMetaData();
					int colNum = ms.getColumnCount();
					while (rs.next()) {
						form = new HashMap();
						for (int i = 1; i <= colNum; i++) {
							form.put(ms.getColumnName(i).toUpperCase(), rs.getObject(i) == null ? "" : rs.getObject(i));
						}
						list.add(form);
					}
				} catch (SQLException ex) {
					LogUtil.error(ex);
				} finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (statement != null) {
							statement.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException ex1) {
						LogUtil.error(ex1.getMessage());
					}
				}
				return list;
			}
		});
	}

	/**
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getAllBySQL(final String sql) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				List list = new ArrayList();
				java.sql.ResultSet rs = null;
				java.sql.Statement statement = null;
				java.sql.Connection conn = session.connection();
				try {
					Map form = null;
					statement = conn.createStatement();
					rs = statement.executeQuery(sql);
					ResultSetMetaData ms = rs.getMetaData();
					int colNum = ms.getColumnCount();
					while (rs.next()) {
						form = new HashMap();

						for (int i = 1; i <= colNum; i++) {
							form.put(ms.getColumnName(i).toUpperCase(), rs.getObject(i) == null ? "" : rs.getObject(i));
						}
						list.add(form);
					}
				} catch (SQLException ex) {
					LogUtil.error(ex);
				} finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (statement != null) {
							statement.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException ex1) {
						LogUtil.error(ex1.getMessage());
					}
				}
				return list;
			}
		});
	}

	public void flushSession() {
		super.getSession().clear();
	}
}
