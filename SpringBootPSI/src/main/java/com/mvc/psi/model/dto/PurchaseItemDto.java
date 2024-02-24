package com.mvc.psi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseItemDto {
	
	private Long id; // 採購項目序號
	private Integer amount; // 採購數量
	private PurchaseDto purchase;
	private ProductDto product;
	
	public Integer getTotalCost() {
		return amount * product.getCost();
	}
}


