package org.impelsys.department.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity 	//POJO
@Table(name="Department")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Department {
	@Id
//	@Column(name="dept_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	int deptId;
	
	@Column(name="dept_name")
	String  deptName;
	
	@Column(name="hod_name")
	String hodName;	

}

