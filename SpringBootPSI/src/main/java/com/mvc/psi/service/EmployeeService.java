package com.mvc.psi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
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
	@Transactional
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
	
	// 修改
	@Transactional
	public void update(EmployeeDto employeeDto, Long id) {
		Optional<Employee> employeeOpt = employeeRepository.findById(id);
		if(employeeOpt.isPresent()) {
			Employee employee = employeeOpt.get();
			employee.setName(employeeDto.getName()); // 更新員工姓名
			
			Optional<Department> departmentOpt = departmentRepository.findById(employeeDto.getDepartment().getId());
			if(departmentOpt.isPresent()) {
				employee.setDepartment(departmentOpt.get()); // 更新部門
				employeeRepository.save(employee);
				return;
			}
			throw new RuntimeException("修改員工資料錯誤: 無此部門");
		}
		throw new RuntimeException("修改員工資料錯誤: 無此員工");
	}
	
}
