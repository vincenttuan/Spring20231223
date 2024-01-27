package com.mvc.psi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
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
	@Transactional
	public void update(DepartmentDto departmentDto, Long id) {
		// 根據 id 找到可以修改的紀錄
		Optional<Department> departmentOpt = departmentRepository.findById(id);
		if(departmentOpt.isPresent()) {
			Department department = departmentOpt.get();
			// 針對要修改的欄位進行修改
			department.setName(departmentDto.getName());
			// 修改
			departmentRepository.save(department);
			return;
		}
		throw new RuntimeException("修改失敗: 無此資料");
	}
	
	// 刪除
	@Transactional
	public void delete(Long id) {
		// 根據 id 找到可以刪除的紀錄
		Optional<Department> departmentOpt = departmentRepository.findById(id);
		if(departmentOpt.isPresent()) {
			departmentRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("刪除失敗: 無此資料");
	}
	
	// 查詢單筆
	public DepartmentDto getDepartmentDtoById(Long id) {
		// 根據 id 可以找到該筆紀錄
		Optional<Department> departmentOpt = departmentRepository.findById(id);
		if(departmentOpt.isPresent()) {
			Department department = departmentOpt.get();
			// PO 轉 DTO
			DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
			return departmentDto;
		}
		return null;
	}
	
	// 全部查詢
	
	
}
