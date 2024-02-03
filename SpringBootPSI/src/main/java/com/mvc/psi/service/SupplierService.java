package com.mvc.psi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.SupplierDto;
import com.mvc.psi.model.po.Supplier;
import com.mvc.psi.repository.SupplierRepository;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增
	public void add(SupplierDto supplierDto) {
		// Dto 轉 Po
		Supplier supplier = modelMapper.map(supplierDto, Supplier.class);
		supplierRepository.save(supplier);
	}
	
	// 修改
	public void update(SupplierDto supplierDto, Long id) {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		if(supplierOpt.isPresent()) {
			Supplier supplier = supplierOpt.get();
			supplier.setName(supplierDto.getName());
			supplierRepository.save(supplier);
			return;
		}
		throw new RuntimeException("修改失敗: 無此資料");
	}
	
	// 刪除
	public void delete(Long id) {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		if(supplierOpt.isPresent()) {
			supplierRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("刪除失敗: 無此資料");
	}
	
	// 查詢單筆
	public SupplierDto getCustomerDtoById(Long id) {
		Optional<Supplier> customerOpt = supplierRepository.findById(id);
		if(customerOpt.isPresent()) {
			Supplier supplier = customerOpt.get();
			// Po 轉 Dto
			SupplierDto customerDto = modelMapper.map(supplier, SupplierDto.class);
			return customerDto;
		}
		return null;
	}
	
	// 查詢全部
		public List<SupplierDto> findAll() {
			List<Supplier> suppliers = supplierRepository.findAll();  // Customer(Po) 的 list
			List<SupplierDto> customerDtos = suppliers.stream()
					.map(supplier -> modelMapper.map(supplier, SupplierDto.class)) // 逐筆轉 Dto
					.toList();
			return customerDtos;
	
	
 }
}