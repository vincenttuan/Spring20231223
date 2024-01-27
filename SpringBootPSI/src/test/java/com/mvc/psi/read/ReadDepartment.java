package com.mvc.psi.read;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.psi.model.dto.DepartmentDto;
import com.mvc.psi.model.po.Department;
import com.mvc.psi.repository.DepartmentRepository;
import com.mvc.psi.service.DepartmentService;

@SpringBootTest
public class ReadDepartment {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	/*
	@Transactional
	@Test
	public void readOne() {
		System.out.println("readOne:");
		DepartmentDto departmentDto = departmentService.getDepartmentDtoById(1L);
		System.out.println("id: " + departmentDto.getId());
		System.out.println("name: " + departmentDto.getName());
		//System.out.println("employees: " + departmentDto.getEmployees().size());
	}
	*/
	/*
	@Transactional // 在測試的時候若 fetch = FetchType.LAZY, 要加上 @Transactional
	@Test
	public void readOne() {
		System.out.println("readOne:");
		Department department = departmentRepository.findById(1L).get();
		System.out.println("id: " + department.getId());
		System.out.println("name: " + department.getName());
		System.out.println("employees: " + department.getEmployees().size());
	}
	*/
	
	
	
	
}
