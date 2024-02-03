package com.mvc.psi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDto {
	
	private Long id; // 供應商序號 from po
	private String name; // 供應商名稱 from po
	
	
	@Override
	public String toString() {
		return "SupplierrDto [id=" + id + ", name=" + name + "]";
	}
	
    
	
}