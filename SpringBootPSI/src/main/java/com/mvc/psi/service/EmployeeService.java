package com.mvc.psi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.EmployeeDto;
import com.mvc.psi.model.po.Department;
import com.mvc.psi.model.po.Employee;
import com.mvc.psi.repository.DepartmentRepository;
import com.mvc.psi.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增 I
	public void add(EmployeeDto employeeDto) {
		// DTO -> PO
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		// 根據 employeeDto 的 department id 找到 department po
		Long departmentId = employeeDto.getDepartment().getId();
		Optional<Department> departmentOpt = departmentRepository.findById(departmentId);
		if(departmentOpt.isPresent()) {
			Department department = departmentOpt.get();
			employee.setDepartment(department); // 注入 department 物件
			employeeRepository.save(employee);
		}
	}
	
	// 新增 II
	public void add(EmployeeDto employeeDto, Long departmentId) {
		// DTO -> PO
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		// 根據 departmentId 找到 department
		Optional<Department> departmentOpt = departmentRepository.findById(departmentId);
		if(departmentOpt.isPresent()) {
			employee.setDepartment(departmentOpt.get());
			employeeRepository.save(employee);
		}
	}
	
	
}
