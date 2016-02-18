package com.number.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.number.entity.Department;
@Component
public class DepartmentVo {

	
	private List<Department> DepartmentList;
	private int currentPage;
	private List<Department> currentDepartmentList;
	private int size;
	private int total;
	private ArrayList<Integer> sizeArray;

	public int getSize(){
		size=size+10;
		return size;
	}
	
	public ArrayList<Integer> getSizeArray() {
		return sizeArray;
	}

	public void setSizeArray(ArrayList<Integer> sizeArray) {
		this.sizeArray = sizeArray;
	}

		
	public int getTotal() {
		total = DepartmentList.size();
		return total;
	}
	
	
	public List<Department> getDepartmentList() {
		return DepartmentList;
	}
	public void setDepartmentList(List<Department> departmentList) {
		DepartmentList = departmentList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void resetCurrentDepartmentList(){
		currentDepartmentList = new ArrayList<Department>();
	}
	
	public void buildResultonPage(int currentpage){
		size = sizeArray.get(currentpage-1);
		int start = (currentpage-1)*5;
		for(int i=start;i<start+size;i++){
		   	currentDepartmentList.add(DepartmentList.get(i));
		}
			
	}
	
	public List<Department> getCurrentDepartmentList(){
		return currentDepartmentList;
	}
}
