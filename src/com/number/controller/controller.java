package com.number.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.number.entity.Department;
import com.number.entity.Employee;
import com.number.service.DepartmentService;
import com.number.vo.DepartmentVo;
import com.useless.entity.processedNumber;

@Controller
public class controller {
	@Autowired
	DepartmentService departmentService;

	@Autowired
	DepartmentVo departmentVo;

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String GomainPage(ModelMap model){
		return "showpage";
	}
	
	 @RequestMapping("/getResult")
		public @ResponseBody DepartmentVo getResult(HttpServletRequest req){
			
		 List<Department> departmentList = departmentService.findAllDepartments();
		  
		 departmentVo.setDepartmentList(departmentList);
		 departmentVo.setCurrentPage(1);
		 int totalNumber = departmentVo.getTotal();
		 ArrayList<Integer> sizeArray = new ArrayList<Integer>();
		 int completedPageNumber = totalNumber/5;
		 int uncompletedPageNumber = totalNumber%5;
		 
		 for(int i=0;i<completedPageNumber;i++){
			 sizeArray.add(5);
		 }
		 sizeArray.add(uncompletedPageNumber);
		 departmentVo.setSizeArray(sizeArray);
		 
		 
		 departmentVo.resetCurrentDepartmentList();
		 departmentVo.buildResultonPage(departmentVo.getCurrentPage());
		 
			 
		 		 
		 req.getSession().setAttribute("currentPage", departmentVo.getCurrentPage());
		 req.getSession().setAttribute("totalNumber", departmentVo.getTotal());
		 req.getSession().setAttribute("rowPerPage", departmentVo.getSize());
		 		
			return departmentVo;
		}

	 @RequestMapping("/getNextPageResult")
	 public @ResponseBody DepartmentVo getNextPageResult(HttpServletRequest req){
		 int currentPage = (Integer)req.getSession().getAttribute("currentPage");
		 currentPage = currentPage+1;
		 req.getSession().setAttribute("currentPage", currentPage);
		 departmentVo.setCurrentPage(currentPage);
		 departmentVo.resetCurrentDepartmentList();
		 departmentVo.buildResultonPage(departmentVo.getCurrentPage());
		 	
		
		 return departmentVo;
	 }
	 
	 @RequestMapping("/getPrePageResult")
	 public @ResponseBody DepartmentVo getPreviousPageResult(HttpServletRequest req){
		 int currentPage = (Integer)req.getSession().getAttribute("currentPage");
		 currentPage = currentPage-1;

		 req.getSession().setAttribute("currentPage", currentPage);
		 departmentVo.setCurrentPage(currentPage);
		 departmentVo.resetCurrentDepartmentList();
		 departmentVo.buildResultonPage(departmentVo.getCurrentPage());
		 		
		 return departmentVo;
	 }
	 
	 @RequestMapping(value = "/getResult/{id}",method = RequestMethod.GET)
	 public @ResponseBody Department getSingleDepartment(@PathVariable("id") int deptId ){
		 
		 Department dept = departmentService.findById(deptId);
		 return dept;		 
	 }
	 
	 
	 @RequestMapping(value = "/save", method=RequestMethod.POST)
	 public void saveNewDepartment(@RequestParam(value="DepartmentName") String deptN, @RequestParam(value="DepartmentEmail") String deptE){
		 Department dept = new Department();
		 dept.setDeptName(deptN);
		 dept.setDeptEmail(deptE);
		 
		 departmentService.saveDepartment(dept);
		 
	 }
	 
	 @RequestMapping(value = "/saveJson", method=RequestMethod.POST)
	 public void saveNewDepartmentByJson(@RequestBody List<Department> deptlist){
		 
		 for(Department newdept:deptlist){
			 departmentService.saveDepartment(newdept);	 
		 }
		 
	 }
	 
	 
}
	

//	@RequestMapping(value = "/abc",method = RequestMethod.POST)
//	public processedNumber doMethod(@RequestBody processedNumber number) {
//
//		processedNumber processNumber = new processedNumber();
//		String stringNumber = String.valueOf(number.getNumber());
//
//		if(stringNumber.length()==7 || stringNumber.length()==10){
//			processNumber.setNumber(number.getNumber());
//			ArrayList<String> result = processNumber.listCombination();
//			processNumber.setResultSet(result);
//			return processNumber;
//		}
//		return null;
//	}

//	@RequestMapping(value = {"/" }, method = RequestMethod.GET)
//	public String toEnterNumber(ModelMap model) {
//		processedNumber processNumber = new processedNumber();
//		model.addAttribute("processNumber", processNumber);
//		return "enterNumber";
//	}

