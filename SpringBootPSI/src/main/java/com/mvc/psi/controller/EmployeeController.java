package com.mvc.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.psi.model.dto.DepartmentDto;
import com.mvc.psi.model.dto.EmployeeDto;
import com.mvc.psi.service.DepartmentService;
import com.mvc.psi.service.EmployeeService;

/*
 * URL 路徑設計
 * GET  /employee/            首頁
 * GET  /employee/edit/{id}   單筆查詢(修改用)
 * POST /employee/            新增
 * PUT  /employee/{id}        修改
 * GET  /employee/delete/{id} 刪除
 * */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String index(@ModelAttribute EmployeeDto employeeDto, Model model) {
		
		List<DepartmentDto> departmentDtos = departmentService.findAll();
		List<EmployeeDto> employeeDtos = employeeService.finaAll();
		model.addAttribute("departmentDtos", departmentDtos);
		model.addAttribute("employeeDtos", employeeDtos);
		//model.addAttribute("employeeDto", employeeDto);
		return "employee";
	}
	
}
