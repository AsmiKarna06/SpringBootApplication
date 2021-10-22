package org.impelsys.department.service;

import java.util.Optional;

import org.impelsys.department.model.Department;
import org.impelsys.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository deptRepo;

	public Department saveDepartment(Department dept) {
		
		// TODO Auto-generated method stub
		return deptRepo.save(dept);
	}

//	public Department findDepartmentById(Integer id) {
//		// TODO Auto-generated method stub
//		return deptRepo.getById(id);
//	}
	public Optional<Department> findDepartmentById(Integer id) {
		// TODO Auto-generated method stub
		return deptRepo.findById(id);
	}

}
