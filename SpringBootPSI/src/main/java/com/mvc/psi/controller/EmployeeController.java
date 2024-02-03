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
import org.springframework.web.bind.annotation.ResponseBody;

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
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		model.addAttribute("departmentDtos", departmentDtos);
		model.addAttribute("employeeDtos", employeeDtos);
		//model.addAttribute("employeeDto", employeeDto);
		return "employee";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		EmployeeDto employeeDto = employeeService.getEmployeeDtoById(id);
		model.addAttribute("employeeDto", employeeDto);
		model.addAttribute("departmentDtos", departmentService.findAll());
		return "employee-edit";
	}
	
	@PostMapping("/")
	//@ResponseBody
	public String create(EmployeeDto employeeDto, Model model) {
		employeeService.add(employeeDto);
		
		// 重導到指定網頁 (http://localhost:8080/psi/employee/)
		// 1.發出一個重導命令給瀏覽器
		// 2.瀏覽器接到重導命令後會自動執行重導動作
		// 3.重導動作就是自動重連 http://localhost:8080/psi/employee/ 位置
		return "redirect:/employee/";
		//return "redirect:https://tw.yahoo.com";
		//return "Save OK!";
	}
	
	@PutMapping("/{id}")
	public String update(EmployeeDto employeeDto, @PathVariable("id") Long id, Model model) {
		employeeService.update(employeeDto, id);
		return "redirect:/employee/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		employeeService.delete(id);
		return "redirect:/employee/"; // 重導到 http://localhost:8080/psi/employee/
	}
	
}
