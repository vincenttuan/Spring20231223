package com.mvc.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.psi.model.dto.CustomerDto;
import com.mvc.psi.model.dto.SupplierDto;
import com.mvc.psi.service.CustomerService;
import com.mvc.psi.service.SupplierService;

/*
 * URL 路徑設計
 * GET  /supplier/            首頁
 * GET  /supplier/edit/{id}   單筆查詢(修改用)
 * POST /supplier/            新增
 * PUT  /supplier/{id}        修改
 * GET  /supplier/delete/{id} 刪除
 * */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/")
	public String index(@ModelAttribute SupplierDto supplierDto, Model model) {
		List<SupplierDto> supplierDtos = supplierService.findAll();
		model.addAttribute("customerDtos", supplierDtos);
		return "customer";
	}
	
	
}