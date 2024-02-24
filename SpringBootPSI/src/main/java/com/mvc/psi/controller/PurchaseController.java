package com.mvc.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.psi.model.dto.EmployeeDto;
import com.mvc.psi.model.dto.PurchaseDto;
import com.mvc.psi.model.dto.SupplierDto;
import com.mvc.psi.service.EmployeeService;
import com.mvc.psi.service.ProductService;
import com.mvc.psi.service.PurchaseService;
import com.mvc.psi.service.SupplierService;

/*
 * URL 路徑設計
 * GET  /               採購單首頁
 * */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String index(Model model) {
		PurchaseDto purchaseDto = new PurchaseDto();
		List<PurchaseDto> purchaseDtos = purchaseService.findAll();
		List<SupplierDto> supplierDtos = supplierService.findAll();
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		
		model.addAttribute("purchaseDto", purchaseDto);
		model.addAttribute("purchaseDtos", purchaseDtos);
		model.addAttribute("supplierDtos", supplierDtos);
		model.addAttribute("employeeDtos", employeeDtos);
		
		return "purchase";
	}
	
}
