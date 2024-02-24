package com.mvc.psi.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDto {
	private Long id; // 採購單序號
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date; // 採購日期
	
	private SupplierDto supplier;
	
	private EmployeeDto employee;
	
	private List<PurchaseItemDto> purchaseItems = new ArrayList<>();
	
	// 計算採購單總價
	public Integer getTotal() {
		
	}
}

