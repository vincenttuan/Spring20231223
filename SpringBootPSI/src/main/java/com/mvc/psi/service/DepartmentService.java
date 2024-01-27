package com.mvc.psi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.DepartmentDto;
import com.mvc.psi.model.po.Department;
import com.mvc.psi.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// 新增
	// Controller 會傳來一個 DepartmentDto 物件
	// Service 要將 DTO(DepartmentDto) 轉成 PO(Department)
	// 最後將 PO(Department) 傳給 Repository 新增
	public void add(DepartmentDto departmentDto) {
		// 將 DTO 轉 PO
		Department department = new Department();
		department.setId(departmentDto.getId());
		department.setName(departmentDto.getName());
		// 儲存
		departmentRepository.save(department);
	}
	
	// 修改
	
	// 刪除
	
	// 查詢
	
}
