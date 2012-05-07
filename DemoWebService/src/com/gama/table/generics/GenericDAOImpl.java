package com.gama.table.generics;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;


/**
 * 
 * @author Gush
 * 
 * @param <T>
 * @param <ID>
 */
public abstract class GenericDAOImpl<T, ID extends Serializable> implements
		GenericDAO<T, ID> {
	protected Logger log;
	private Class<T> persistentClass;

	private HibernateTemplate template;

	@SuppressWarnings("unchecked")
	protected GenericDAOImpl() {
		log = Logger.getLogger(this.getClass());
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	


	
	public List<T> findAll() {
		return getTemplate().loadAll(getPersistentClass());
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Criteria crit = DetachedCriteria.forClass(getPersistentClass())
				.getExecutableCriteria(
						getTemplate().getSessionFactory().getCurrentSession());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	public T makePersistent(T entity) {
		getTemplate().saveOrUpdate(entity);
		return entity;
	}

	public void makeTransient(T entity) {
		getTemplate().delete(entity);
	}

	public void save(T entity) {
		getTemplate().save(entity);
	}

	public void flush() {
		getTemplate().flush();

	}

	public void clear() {
		getTemplate().clear();
	}

	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(getPersistentClass());
	}

	public Criteria createCriteria() {
		return createDetachedCriteria().getExecutableCriteria(
				getTemplate().getSessionFactory().getCurrentSession());
	}

	/**
	 * Execute a query based on the given Hibernate criteria object.
	 * 
	 * @param crit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria crit) {
		return getTemplate().findByCriteria(crit);
	}

	/**
	 * Execute a query based on the given Hibernate criteria object.
	 * 
	 * @param crit
	 * @param firstResult
	 *            - the index of the first result object to be retrieved
	 *            (numbered from 0)
	 * @param maxResults
	 *            - the maximum number of result objects to retrieve (or <=0 for
	 *            no limit)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria crit, int firstResult,
			int maxResults) {
		return getTemplate().findByCriteria(crit, firstResult, maxResults);
	}

	
	public T findById(ID id) {
		T entity;
		entity = (T) getTemplate().get(getPersistentClass(), id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance) {
		DetachedCriteria crit = DetachedCriteria.forClass(getPersistentClass());
		Example example = Example.create(exampleInstance);
		example.excludeZeroes();
		crit.add(example);
		return getTemplate().findByExample(exampleInstance);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, int firstResult,
			int maxResults) {
		return getTemplate().findByExample(exampleInstance);
	}

	public void evict(T entity) {
		getTemplate().evict(persistentClass);
	}

	public int countByExample(final T exampleInstance) {
		DetachedCriteria crit = DetachedCriteria.forClass(getPersistentClass());
		Example example = Example.create(exampleInstance);
		crit.add(example);
		return countByCriteria(crit);
	}

	public int countByCriteria(final DetachedCriteria crit) {

		Number count = (Number) getTemplate().executeWithNativeSession(
				new HibernateCallback<Number>(){

					public Number doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria executableCriteria = crit
								.getExecutableCriteria(session);

						// Cast to CriteriaImpl to get access to the Projection
						// and ResultTransformer
						CriteriaImpl cImpl = (CriteriaImpl) executableCriteria;

						// Save original Projection and ResultTransformer (if
						// any, could be null).
						Projection originalProjection = cImpl.getProjection();
						ResultTransformer originalResultTransformer = cImpl
								.getResultTransformer();

						executableCriteria
								.setProjection(Projections.rowCount());
						
						List<Object>  list= new ArrayList<Object>();
						
						for (Object result :executableCriteria.list()) { 
							list.add(result);
						}
						
						// Restore original Projection and
						// ResultTransformer
						
						crit.setProjection(originalProjection);
						crit.setResultTransformer(originalResultTransformer);
						
						
						for (Object result : list) {
							if (result instanceof Integer) {						
								return (Integer) result;
							}
							if(result instanceof Long) {							
								return (Long) result;
							}
						}
						
						return 0;
					}

				});
		return count.intValue();
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public HibernateTemplate getTemplate() {
		return template;
	}

	public Session getSession() {
		HibernateTemplate template = getTemplate();
		SessionFactory sessionFactory = template.getSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	


}
