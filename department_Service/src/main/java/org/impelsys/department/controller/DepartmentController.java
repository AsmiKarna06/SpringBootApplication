package org.impelsys.department.controller;


import java.util.Optional;

import org.impelsys.department.model.Department;
import org.impelsys.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/department")  //  http://localhost:9001/department
@Slf4j	//to enable log files-generate a logger instance with default name log
public class DepartmentController {
	@Autowired
	DepartmentService deptService;
	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department dept) {
		log.info("Inside save Department");
		System.out.println("In saveDepartment()");
		return deptService.saveDepartment(dept);
	}
	
//	@GetMapping("/{id}")
//	public Department getDepartment(@PathVariable("id") Integer id) {
//		log.info("Fetching department for id:"+id);
//		return deptService.findDepartmentById(id);
//	}
	
	@GetMapping("/{id}")
	public Optional<Department> getDepartment(@PathVariable("id") Integer id) {
		log.info("Fetching department for id:"+id);
		return deptService.findDepartmentById(id);
	}
	
}
