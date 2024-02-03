package com.mvc.psi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	
	private Long id; // 客戶序號 from po
	private String name; // 客戶姓名 from po
	
	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", name=" + name + "]";
	}
	
}
