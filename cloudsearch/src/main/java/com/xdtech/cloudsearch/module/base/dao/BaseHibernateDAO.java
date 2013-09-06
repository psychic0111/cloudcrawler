package com.xdtech.cloudsearch.module.base.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * HibernateDAO
 * 
 * @author Chang Fei
 */
@Repository
@SuppressWarnings( {"unchecked"})
public class BaseHibernateDAO implements BaseDAO {

	/**
	 * hibernateTemplate 模板
	 */
	@Autowired(required = true)
	private HibernateTemplate template;

	public HibernateTemplate getTemplate() {
		return template;
	}

	public <T> T findById(Class clz, Serializable id) {
		return (T) template.get(clz, id);
	}

	public <T> T loadById(Class<?> clz, Serializable id) {
		T entity = (T) template.load(clz, id);
		return entity;
	}

	public <T> List<T> findAll(Class<?> clz) {
		List list = template.find("from " + clz.getName());
		return list;
	}

	public <T> T save(T entity) {
		try {
			template.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public void update(Object entity) {
		template.saveOrUpdate(entity);
	}

	public void update(Object entity, LockMode lock) {
		template.saveOrUpdate(entity);
	}

	public <T> void saveOrUpdateAll(Collection<?> entities) {
		template.saveOrUpdate(entities);
	}

	public void delete(Object entity) {
		template.delete(entity);
	}

	public void delete(Object entity, LockMode lock) {
		template.delete(entity);
	}

	public void deleteById(Class<?> clz, Serializable id) {
		this.delete(this.loadById(clz, id));
	}

	public void deleteAll(Collection<?> entities) {
		getTemplate().deleteAll(entities);
	}

	public void merge(Object entity) {
		template.merge(entity);
	}

	public <T> T get(final String hsql) {
		return getTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException, SQLException {
				return (T) session.createQuery(hsql).uniqueResult();
			}
		});
	}

	public List get(String hql, Object... values) {
		return getTemplate().find(hql, values);
	}

	public <T> List<T> findList(String hsql) {
		List list = getTemplate().find(hsql);
		return (List<T>) list;
	}

	public List findList(final String hql, final int start, final int number, final Object... values) {
		return getTemplate().executeWithNativeSession(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setFirstResult(start);
				query.setMaxResults(number);
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
				List list = query.list();
				return list;
			}
		});
	}

	public List findList(final String hql, final int start, final int number) {
		return getTemplate().executeWithNativeSession(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setFirstResult(start);
				query.setMaxResults(number);
				List list = query.list();
				return list;
			}
		});
	}

	public List findList(String queryString, Object... values) {
		return getTemplate().find(queryString, values);
	}

	public List findByNamedQuery(String queryName) {

		return getTemplate().findByNamedQuery(queryName);
	}

	public <T> List<T> findByNamedQuery(String queryName, Object... values) {
		return (List<T>) getTemplate().findByNamedQuery(queryName, values);
	}

	public <T> List<T> findByNamedQuery(final String queryName, final String[] paramNames, final Object[] values) {
		return getTemplate().executeWithNewSession(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException {
				Query query = session.getNamedQuery(queryName);
				for (int i = 0; i < paramNames.length; i++) {
					query.setParameter(paramNames[i], values[i]);
				}
				return query.list();
			}
		});
	}

	public <T> Iterator<T> iterate(final String hql) {
		return getTemplate().executeWithNativeSession(new HibernateCallback<Iterator>() {
			public Iterator doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery(hql).iterate();
			}

		});

	}

	public <T> Iterator<T> iterate(final String hql, final Object... values) {
		return getTemplate().executeWithNativeSession(new HibernateCallback<Iterator>() {
			public Iterator doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
				return query.iterate();
			}

		});
	}

	public DetachedCriteria createDetachedCriteria(Class clz) {
		return DetachedCriteria.forClass(clz);
	}

	public Criteria createCriteria(final Class<?> clz) {
		return getTemplate().executeWithNativeSession(new HibernateCallback<Criteria>() {
			public Criteria doInHibernate(Session session) throws HibernateException, SQLException {
				return createDetachedCriteria(clz).getExecutableCriteria(session);
			}
		});
	}

	public List findByCriteria(final DetachedCriteria criteria) {
		return getTemplate().executeFind(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				return criteria.getExecutableCriteria(session).list();
			}
		});
	}

	public <T> List<T> findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults) {
		return getTemplate().executeFind(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				return criteria.getExecutableCriteria(session).setFirstResult(firstResult).setMaxResults(maxResults).list();
			}
		});
	}

	public <T> List<T> findByCriteria(final Class<?> clz, final Criterion... criterion) {
		return getTemplate().executeWithNativeSession(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(clz);
				for (Criterion c : criterion) {
					if (c != null) {
						crit.add(c);
					}
				}
				List list = crit.list();
				return list;
			}

		});
	}

	public Integer getRowCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List list = this.findByCriteria(criteria, 0, 1);
		return (Integer) list.get(0);

	}

	public Object getStatValue(DetachedCriteria criteria, String propertyName, String StatName) {
		if (StatName.toLowerCase().equals("max"))
			criteria.setProjection(Projections.max(propertyName));
		else if (StatName.toLowerCase().equals("min"))
			criteria.setProjection(Projections.min(propertyName));
		else if (StatName.toLowerCase().equals("avg"))
			criteria.setProjection(Projections.avg(propertyName));
		else if (StatName.toLowerCase().equals("sum"))
			criteria.setProjection(Projections.sum(propertyName));
		else
			return null;
		List list = this.findByCriteria(criteria, 0, 1);
		return list.get(0);
	}

	public int delete(final String hql, final Object... values) {
		return (Integer) getTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				Query query = arg0.createQuery(hql);
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
				return query.executeUpdate();
			}
		});

	};

}
