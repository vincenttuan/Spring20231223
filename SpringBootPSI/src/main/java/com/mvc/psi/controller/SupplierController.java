package com.mvc.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.psi.model.dto.CustomerDto;
import com.mvc.psi.model.dto.SupplierDto;
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
		model.addAttribute("supplierDtos", supplierDtos);
		return "supplier";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		SupplierDto supplierDto = supplierService.getSupplierDtoById(id);
		model.addAttribute("supplierDto", supplierDto);
		return "supplier-edit";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		supplierService.delete(id);
		return "redirect:/supplier/";
	}
	
	@PostMapping("/")
	public String add(SupplierDto supplierDto) {
		supplierService.add(supplierDto);
		return "redirect:/supplier/";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, SupplierDto supplierDto) {
		supplierService.update(supplierDto, id);
		return "redirect:/supplier/";
	}
	
	
}