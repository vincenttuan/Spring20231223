package com.mvc.psi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	private Long id; // 員工序號
	private String name; // 員工姓名
	private DepartmentDto department; // 員工所屬部門
	
}
