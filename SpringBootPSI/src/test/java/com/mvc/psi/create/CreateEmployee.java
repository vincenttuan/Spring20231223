package com.mvc.psi.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.psi.entity.Department;
import com.mvc.psi.entity.Employee;
import com.mvc.psi.repository.DepartmentRepository;
import com.mvc.psi.repository.EmployeeRepository;

@SpringBootTest
public class CreateEmployee {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void test() {
		Department sales    = departmentRepository.findById(1L).get();
		Department it       = departmentRepository.findById(2L).get();
		Department purchase = departmentRepository.findById(3L).get();
		
		Employee emp1 = new Employee();
		emp1.setName("John");
		
		Employee emp2 = new Employee();
		emp2.setName("Mary");
		
		Employee emp3 = new Employee();
		emp3.setName("Helen");
		
		Employee emp4 = new Employee();
		emp4.setName("Bob");
		
		// 配置關聯
		emp1.setDepartment(sales);
		emp2.setDepartment(it);
		emp3.setDepartment(it);
		emp4.setDepartment(purchase);
		
		// 儲存
		employeeRepository.save(emp1);
		employeeRepository.save(emp2);
		employeeRepository.save(emp3);
		employeeRepository.save(emp4);
	}
}
