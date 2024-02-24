package com.mvc.psi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.PurchaseDto;
import com.mvc.psi.model.po.Employee;
import com.mvc.psi.model.po.Purchase;
import com.mvc.psi.model.po.Supplier;
import com.mvc.psi.repository.PurchaseItemRepository;
import com.mvc.psi.repository.PurchaseRepository;

import jakarta.transaction.Transactional;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Purchase-CRUD
	// 新增
	@Transactional
	public Long add(PurchaseDto purchaseDto) {
		// DTO 轉 PO
		Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);
		purchaseRepository.saveAndFlush(purchase);
		return purchase.getId();
	}
	
	// 修改
	@Transactional
	public void update(PurchaseDto purchaseDto, Long id) {
		// 資料表中是否有此筆資料
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			Purchase purchase = purchaseOpt.get();
			// 進行修改欄位程序
			purchase.setDate(purchaseDto.getDate()); // 修改日期
			
			// 將 DTO 內的員工物件取出後再轉 PO
			Employee employee = modelMapper.map(purchaseDto.getEmployee(), Employee.class);
			purchase.setEmployee(employee); // 修改員工
			
			// 將 DTO 內的供應商物件取出後再轉 PO
			Supplier supplier = modelMapper.map(purchaseDto.getSupplier(), Supplier.class);
			purchase.setSupplier(supplier); // 修改供應商
			
			// 資料更新
			purchaseRepository.save(purchase);
		}
		
	}
	
	// -----------------------------------------------------------------------------------
	// PurchaseItem-CRUD
	
	
}
