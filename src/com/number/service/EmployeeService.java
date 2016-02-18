package com.number.service;

import java.util.List;

import com.number.entity.Employee;

public interface EmployeeService {
    
	Employee findById(long id);
	Employee findByName(String name);
	void saveEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployeeById(long id);
    List<Employee> findAllEmployees(); 
	void deleteAllEmployees();
	public boolean isEmployeeExist(Employee employee);
}
