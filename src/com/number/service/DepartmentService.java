package com.number.service;

import java.util.List;

import com.number.entity.Department;


public interface DepartmentService {
	Department findById(long id);
	Department findByName(String name);
	void saveDepartment(Department department);
	void updateDepartment(Department department);
	void deleteDepartmentById(long id);
    List<Department> findAllDepartments(); 
	void deleteAllDepartments();
	public boolean isDepartmentExist(Department department);
}
