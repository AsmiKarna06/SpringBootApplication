package org.impelsys.employeeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity          
@Table(name="Employee")        
@Data                     
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	@Column(name="emp_id")       
	@GeneratedValue(strategy=GenerationType.AUTO)           
	int empId;
	
	@Column(name="emp_name")  
	String empName;
	
	@Column(name="phone") 
	String Phone;            

	@Column(name="deptId")
	Integer deptId;
	
}
