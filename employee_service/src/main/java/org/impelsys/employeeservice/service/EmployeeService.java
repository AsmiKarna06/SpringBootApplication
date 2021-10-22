package org.impelsys.employeeservice.service;


import java.util.Optional;

import org.impelsys.employeeservice.model.Employee;
import org.impelsys.employeeservice.repository.EmployeeRepository;
import org.impelsys.employeeservice.vo.Department;
import org.impelsys.employeeservice.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class EmployeeService {	
//	@Value("${deptservice_endpoint}")
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	EmployeeRepository empRepo;

	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub		
		return empRepo.save(emp);
	}

//	public Employee findEmployeeById(Integer id) {
//		// TODO Auto-generated method stub
////		return empRepo.findById(id); //eagar
//		return empRepo.getById(id);	//lazy ->gives a reference to a dummy Employee obje
//	}
	public Optional<Employee> findEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return empRepo.findById(id); //eagar
	}

	public ResponseVO findEmployeeWithDeptById(Integer id) {
		ResponseVO responseVO = new ResponseVO();
		Employee emp = empRepo.findById(id).get();
		//need to call a different microservice to get dept
		ResponseEntity<Department> deptResponse = restTemplate.getForEntity("http://localhost:9001/department" + emp.getDeptId(), Department.class);
//		ResponseEntity<Department> deptResponse = restTemplate.getForEntity(""+ emp.getDeptId(), Department.class);
		log.info("deptResponse" + deptResponse);
		responseVO.setDepartment(deptResponse.getBody());
		responseVO.setEmployee(emp);
		return responseVO;
	}
}