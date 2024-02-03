package com.mvc.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.psi.model.dto.ProductDto;
import com.mvc.psi.service.ProductService;

/*
 * URL 路徑設計
 * GET  /product/            首頁
 * GET  /product/edit/{id}   單筆查詢(修改用)
 * POST /product/            新增
 * PUT  /product/{id}        修改
 * GET  /product/delete/{id} 刪除
 * */
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String index(@ModelAttribute ProductDto productDto, Model model) {
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("productDtos", productDtos);
		return "product";
	}
	
	
}
