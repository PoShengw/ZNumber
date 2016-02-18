package com.number.dao;

import java.util.List;


public interface DataDao {
	 
 
	
	<T> void addEntity(T entity);
	<T> T getEntityById(long id, Class<T> entityClass);
	<T> List<T> getEntityList(Class<T> entityClass);
	<T> void deleteEntity(long id, Class<T> entityClass);
	<T> void updateEntity(T entity);
	
	<T> void deleteEntityList(Class<T> entityClass);
}
