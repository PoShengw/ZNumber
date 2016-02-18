package com.number.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.number.dao.DataDao;
import com.number.entity.Department;
import com.number.entity.Employee;

@Service("depService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
    private DataDao departmentDAO;

	@Override
	public Department findById(long id) {
		System.out.println("Enter DepartmentService FindById B");
		Department dept = departmentDAO.getEntityById(id, Department.class);
		
		Hibernate.initialize(dept.getEmployees());
		return dept;
	}

	@Override
	public Department findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDepartment(Department department) {
		   System.out.println("enter departmetnServiceImpl saveDepartment method");
		   
		   //Hibernate.initialize(department.getEmployees()); 
		   departmentDAO.addEntity(department);

	}

	@Override
	public void updateDepartment(Department department) {
		 departmentDAO.updateEntity(department);

	}

	@Override
	public void deleteDepartmentById(long id) {
		departmentDAO.deleteEntity(id,Department.class);

	}

	@Override
	public List<Department> findAllDepartments() {
		System.out.println("Enter DepartmentService 2");
		List<Department> dept = departmentDAO.getEntityList(Department.class);
		
		for(Department d:dept){
			Hibernate.initialize(d.getEmployees()); 
		}
		
		return dept;
	}

	@Override
	public void deleteAllDepartments() {
		departmentDAO.deleteEntityList(Department.class);

	}

	@Override
	public boolean isDepartmentExist(Department department) {
		// TODO Auto-generated method stub
		return false;
	}

}
