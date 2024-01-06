package com.mvc.psi.read;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.psi.entity.Department;
import com.mvc.psi.entity.Employee;
import com.mvc.psi.repository.DepartmentRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ReadDepartment {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Test
	public void read() {
		List<Department> departments = departmentRepository.findAll();
		//System.out.println(departments);
		
		for(Department dept : departments) {
			System.out.print("部門名稱: " + dept.getName() + " 員工: ");
			Set<Employee> employees = dept.getEmployees();
			employees.forEach(emp -> System.out.print(emp.getName() + " "));
			System.out.println();
		}
		
		
	}
	
	
}
