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

import com.mvc.psi.model.dto.DepartmentDto;
import com.mvc.psi.service.DepartmentService;

/*
 * URL 路徑設計
 * GET  /            部門首頁
 * GET  /edit/{id}   單筆查詢(修改用)
 * POST /            新增
 * PUT  /{id}        修改
 * GET  /delete/{id} 刪除
 * */
@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/")
	public String index(@ModelAttribute DepartmentDto departmentDto, Model model) {
		List<DepartmentDto> departmentDtos = departmentService.findAll();
		model.addAttribute("departmentDtos", departmentDtos);
		model.addAttribute("departmentDto", departmentDto);
		return "department";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("departmentDto", departmentService.getDepartmentDtoById(id));
		return "department-edit";
	}
	
	@PostMapping("/")
	public String add(DepartmentDto departmentDto) {
		departmentService.add(departmentDto);
		return "redirect:/department/"; // 重導到首頁
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, DepartmentDto departmentDto) {
		departmentService.update(departmentDto, id);
		return "redirect:/department/"; // 重導到首頁
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		departmentService.delete(id);
		return "redirect:/department/"; // 重導到首頁
	}
	
}
