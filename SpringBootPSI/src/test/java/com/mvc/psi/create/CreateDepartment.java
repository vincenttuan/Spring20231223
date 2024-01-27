package com.mvc.psi.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.psi.model.dto.DepartmentDto;
import com.mvc.psi.service.DepartmentService;

@SpringBootTest
public class CreateDepartment {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Test
	public void test() {
		// DTO
		DepartmentDto d1 = new DepartmentDto();
		d1.setName("業務部");
		DepartmentDto d2 = new DepartmentDto();
		d2.setName("資訊部");
		DepartmentDto d3 = new DepartmentDto();
		d3.setName("採購部");
		
		// 儲存
		departmentService.add(d1);
		departmentService.add(d2);
		departmentService.add(d3);
		
		System.out.println("Save OK");
	}
	
}
