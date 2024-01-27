package com.mvc.psi.read;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.psi.model.dto.DepartmentDto;
import com.mvc.psi.model.po.Department;
import com.mvc.psi.model.po.Employee;
import com.mvc.psi.repository.DepartmentRepository;
import com.mvc.psi.service.DepartmentService;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ReadDepartment {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Test
	public void readOne() {
		System.out.println("readOne:");
		DepartmentDto departmentDto = departmentService.getDepartmentDtoById(1L);
		System.out.println("id: " + departmentDto.getId());
		System.out.println("name: " + departmentDto.getName());
		System.out.println("employees: " + departmentDto.getEmployees().size());
	}
	
	
}
