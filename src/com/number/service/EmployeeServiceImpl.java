package com.number.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.number.dao.DataDao;
import com.number.entity.Employee;


@Service("empService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private DataDao employeeDAO;
	
	@Override
	public Employee findById(long id){
		return employeeDAO.getEntityById(id, Employee.class);
	}

	@Override
	public Employee findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveEmployee(Employee employee) {
		   System.out.println("enter employeeServiceImpl saveEmployee method");
		   employeeDAO.addEntity(employee);

	}

	@Override
	public void updateEmployee(Employee employee) {
		  employeeDAO.updateEntity(employee);

	}

	@Override
	public void deleteEmployeeById(long id) {
		employeeDAO.deleteEntity(id,Employee.class);

	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeDAO.getEntityList(Employee.class);
		
	}

	@Override
	public void deleteAllEmployees() {
		employeeDAO.deleteEntityList(Employee.class);

	}

	@Override
	public boolean isEmployeeExist(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

}
