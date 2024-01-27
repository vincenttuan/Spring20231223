package com.mvc.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.psi.model.dto.DepartmentDto;
import com.mvc.psi.service.DepartmentService;

/*
 * URL 路徑設計
 * GET  /                       部門首頁
 * GET  /department/edit/{id}   單筆查詢(修改用)
 * POST /department/            新增
 * PUT  /department/{id}        修改
 * GET  /department/delete/{id} 刪除
 * */
@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	public String index(@ModelAttribute DepartmentDto departmentDto, Model model) {
		List<DepartmentDto> departmentDtos = departmentService.findAll();
		model.addAttribute("departmentDtos", departmentDtos);
		model.addAttribute("departmentDto", departmentDto);
		return "department";
	}
	
	
}
