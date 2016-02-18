package com.number.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="department")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
public class Department implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int deptId;
	private String deptEmail;
	private String deptName;
	private List<Employee> employees;

	public Department() {
	}


	@Id
	@Column(name="dept_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getDeptId() {
		return this.deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}


	@Column(name="dept_email")
	public String getDeptEmail() {
		return this.deptEmail;
	}

	public void setDeptEmail(String deptEmail) {
		this.deptEmail = deptEmail;
	}


	@Column(name="dept_name")
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	//bi-directional many-to-one association to Employee
	
	@OneToMany(mappedBy="department",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	
	
	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setDepartment(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setDepartment(null);

		return employee;
	}

}
