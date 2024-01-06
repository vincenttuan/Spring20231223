package com.mvc.psi.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.psi.entity.Department;
import com.mvc.psi.repository.DepartmentRepository;

@SpringBootTest
public class CreateDepartment {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Test
	public void test() {
		Department dept1 = new Department();
		dept1.setName("Sales");
		
		Department dept2 = new Department();
		dept2.setName("IT");
		
		Department dept3 = new Department();
		dept3.setName("Purchase");
		
		// 儲存
		departmentRepository.save(dept1);
		departmentRepository.save(dept2);
		departmentRepository.save(dept3);
		
		System.out.println("Save OK");
	}
	
}
