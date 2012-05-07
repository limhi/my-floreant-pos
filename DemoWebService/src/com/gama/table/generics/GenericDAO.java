package com.gama.table.generics;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;


public interface GenericDAO<T, ID extends Serializable> {
	
	T findById(ID id);
	
	
	 
	
	List<T> findByExample(T exampleInstance, String[] excludeProperty);
	
	DetachedCriteria createDetachedCriteria();
	
	Criteria createCriteria();
	
	List<T> findByCriteria(DetachedCriteria crit);
	
	List<T> findByCriteria(DetachedCriteria crit, int firstResult, int maxResults);
	
	List<T> findByExample(T exampleInstance, int firstResult, int maxResults);
	
	void evict(T entity);
	
	int countByExample(final T exampleInstance);
	
	int countByCriteria(final DetachedCriteria crit);
	
	
	
	List<T> findAll();
	
	
	List<T> findByExample(T exampleInstance);
	
	
	T makePersistent(T entity);
	
	
	void makeTransient(T entity);
	
	public void save(T entity) ;
	public void flush() ;
	public void clear() ;
}
