package com.mvc.psi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.EmployeeDto;
import com.mvc.psi.model.dto.ProductDto;
import com.mvc.psi.model.dto.PurchaseDto;
import com.mvc.psi.model.dto.PurchaseItemDto;
import com.mvc.psi.model.dto.SupplierDto;
import com.mvc.psi.model.po.Employee;
import com.mvc.psi.model.po.Purchase;
import com.mvc.psi.model.po.PurchaseItem;
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
	
	// 刪除
	@Transactional
	public void delete(Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			Purchase purchase = purchaseOpt.get();
			purchaseRepository.delete(purchase);
		}
	}
	
	// 單筆查詢
	public PurchaseDto getPurchaseDtoById(Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			// 自動轉
			return modelMapper.map(purchaseOpt.get(), PurchaseDto.class);
			/*
			// 手動轉
			Purchase purchase = purchaseOpt.get();
			// 將欄位資料逐一轉 Dto
			PurchaseDto purchaseDto = new PurchaseDto();
			purchaseDto.setId(purchase.getId());
			purchaseDto.setDate(purchase.getDate());
			purchaseDto.setEmployee(modelMapper.map(purchase.getEmployee(), EmployeeDto.class));
			purchaseDto.setSupplier(modelMapper.map(purchase.getSupplier(), SupplierDto.class));
			// 明細
			List<PurchaseItemDto> purchaseItemDtos = new ArrayList<>();
			// 逐一將 PO 轉 DTO
			for(PurchaseItem item : purchase.getPurchaseItems()) {
				PurchaseItemDto purchaseItemDto = new PurchaseItemDto();
				purchaseItemDto.setId(item.getId());
				purchaseItemDto.setAmount(item.getAmount());
				purchaseItemDto.setProduct(modelMapper.map(item.getProduct(), ProductDto.class));
				purchaseItemDtos.add(purchaseItemDto);
			}
			purchaseDto.setPurchaseItems(purchaseItemDtos);
			return purchaseDto;
			*/
		}
		return null;
	}
	
	// 全部查詢
	public List<PurchaseDto> findAll() {
		List<Purchase> purchases = purchaseRepository.findAll();
		List<PurchaseDto> purchaseDtos = purchases.stream()
				.map(purchase -> getPurchaseDtoById(purchase.getId()))
				.toList();
		return purchaseDtos;
	}
	
	// -----------------------------------------------------------------------------------
	// PurchaseItem-CRUD
	
	// 新增採購單項目
	// pid 指的是採購單主檔 id
	@Transactional
	public void addItem(PurchaseItemDto purchaseItemDto, Long pid) {
		// DTO -> PO
		// 採購單明細
		PurchaseItem purchaseItem = modelMapper.map(purchaseItemDto,  PurchaseItem.class);
		// 採購單主檔
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(pid);
		if(purchaseOpt.isPresent()) {
			// 採購單明細注入採購單主檔, 建立關聯
			purchaseItem.setPurchase(purchaseOpt.get());
			purchaseItemRepository.save(purchaseItem);
		}
	}
	
	// 查詢單筆採購單項目
	public PurchaseItemDto getPurchaseItemDtoById(Long iid) {
		Optional<PurchaseItem> purchaseItemOpt = purchaseItemRepository.findById(iid);
		if(purchaseItemOpt.isPresent()) {
			PurchaseItem purchaseItem = purchaseItemOpt.get();
			// 直接轉
			return modelMapper.map(purchaseItem, PurchaseItemDto.class);
			// 手動轉(逐一配置)
			/*
			PurchaseItemDto purchaseItemDto = new PurchaseItemDto();
			purchaseItemDto.setId(purchaseItem.getId());
			purchaseItemDto.setAmount(purchaseItem.getAmount());
			purchaseItemDto.setProduct(modelMapper.map(purchaseItem.getProduct(), ProductDto.class));
			purchaseItemDto.setPurchase(modelMapper.map(purchaseItem.getPurchase(), PurchaseDto.class));
			return purchaseItemDto;
			*/
		}
		return null;
	}
	
	// 修改採購單項目
	@Transactional
	public void updatePurchaseItem(PurchaseItemDto purchaseItemDto, Long pid) {
		// DTO -> PO
		// 採購單明細
		PurchaseItem purchaseItem = modelMapper.map(purchaseItemDto,  PurchaseItem.class);
		// 採購單主檔
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(pid);
		if(purchaseOpt.isPresent()) {
			purchaseItem.setPurchase(purchaseOpt.get());
			purchaseItemRepository.save(purchaseItem);
		}
	}
	
	// 刪除採購單項目
	@Transactional
	public void deletePurchaseItem(Long iid) {
		purchaseItemRepository.deleteById(iid);
	}
	
}
