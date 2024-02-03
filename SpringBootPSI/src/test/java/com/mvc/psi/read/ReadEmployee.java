package com.mvc.psi.read;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.psi.service.EmployeeService;

@SpringBootTest
public class ReadEmployee {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Transactional
	@Test
	public void readOne() {
		// 請協助找出 "張三" 所屬的部門總共有哪些幾位員工 ?
		employeeService.findAll().stream()
			.filter(employeeDto -> employeeDto.getName().equals("張三"))
			.findAny()
			.get()
			.getDepartment()
			.getEmployees()
			.forEach(employeeDto -> System.out.println(employeeDto.getName()));
		
	}
	
}
