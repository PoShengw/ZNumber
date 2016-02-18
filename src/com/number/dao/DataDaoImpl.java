package com.number.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.number.entity.Employee;


@Repository
@Transactional
public class DataDaoImpl implements DataDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public DataDaoImpl(){
		System.out.println("Enter EmployeeDaoImpl");
	}
	
	@Override
	public <T> void addEntity(T entity) {
		System.out.println("enter DataDaoImpl addEntity method");
		sessionFactory.getCurrentSession().save(entity);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEntityById(long id, Class<T> entityClass) {
		System.out.println("Enter getEntityById  C");
		int idd = (int) id;
		 return	(T)sessionFactory.getCurrentSession().get(entityClass, idd);
	}
	
	
	@Override
	public <T> List<T> getEntityList(Class<T> entityClass){
		System.out.println("Enter DataDaoImpl 3");
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) sessionFactory.getCurrentSession()
                .createCriteria(entityClass)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//		for(T t:list){
//			
//			Hibernate.initialize();
//		}
 
        return list;
	}

	@Override
	public <T> void deleteEntity(long id, Class<T> entityClass){
		T entity = getEntityById(id, entityClass);
		sessionFactory.getCurrentSession().delete(entity);
		
//		Employee userToDelete = new Employee();
//		int idd = (int) id;
//		userToDelete.setEmpId(idd);
//		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	
	
	@Override
	public <T> void updateEntity(T entity) {
		sessionFactory.getCurrentSession().merge(entity);
		
	}

	@Override
	public <T> void deleteEntityList(Class<T> entityClass) {
		
		//sessionFactory.getCurrentSession().delete(entity);
		
		List<T> tempT = this.getEntityList(entityClass);
		for(T entity: tempT){
			sessionFactory.getCurrentSession().delete(entity);
		}
//		List<Employee> tempEmp = this.getEntityList();
//		for(Employee entity:tempEmp){
//			sessionFactory.getCurrentSession().delete(entity);
//			
//		}
//		
		
	}
	
     
	

	

	

}
