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
		employeeService.finaAll().stream()
			.filter(emp -> emp.getName().equals("張三"))
			.findAny()
			.get()
			.getDepartment()
			.getEmployees()
			.forEach(emp -> System.out.println(emp.getName()));
		
	}
	
}
