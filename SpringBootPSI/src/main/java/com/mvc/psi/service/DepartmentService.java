package com.mvc.psi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.DepartmentDto;
import com.mvc.psi.model.po.Department;
import com.mvc.psi.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增
	// Controller 會傳來一個 DepartmentDto 物件
	// Service 要將 DTO(DepartmentDto) 轉成 PO(Department)
	// 最後將 PO(Department) 傳給 Repository 新增
	public void add(DepartmentDto departmentDto) {
		// 將 DTO 轉 PO
		/*
		Department department = new Department();
		department.setId(departmentDto.getId());
		department.setName(departmentDto.getName());
		*/
		// 利用 modelMapper 轉換
		// 注意: 會根據 DTO 與 PO 同名的屬性才會進行轉換
		Department department = modelMapper.map(departmentDto, Department.class);
		// 儲存
		departmentRepository.save(department);
	}
	
	// 修改
	
	// 刪除
	
	// 查詢
	
}
