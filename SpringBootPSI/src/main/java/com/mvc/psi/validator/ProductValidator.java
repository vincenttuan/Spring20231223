package com.mvc.psi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mvc.psi.model.dto.ProductDto;

// 商品驗證/檢驗器
@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 判斷所要驗證的是否是 ProductDto 
		return ProductDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductDto productDto = (ProductDto)target;
		
		// 商品售價 <= 商品成本
		if(productDto.getPrice() <= productDto.getCost()) {
			errors.reject("price", String.format("商品售價($%d)不可以小於商品成本($%d)", productDto.getPrice(), productDto.getCost()));
		}
		
		
	}
	
	
	
}
