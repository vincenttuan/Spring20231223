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
		
		if(productDto.getName() == null) {
			errors.reject("name", "商品名稱不可空白");
		}
		
		if(productDto.getName().length() > 10) {
			errors.reject("name", "商品名稱不可超過 10 個字");
		}
		
		// 商品售價 <= 商品成本
		if(productDto.getPrice() <= productDto.getCost()) {
			errors.reject("price", String.format("商品售價($%d)不可以小於商品成本($%d)", productDto.getPrice(), productDto.getCost()));
		}
		
		if(productDto.getPrice() < 0) {
			errors.reject("price", String.format("商品售價($%d)不可以小於0", productDto.getPrice()));
		}
		
		if(productDto.getCost() < 0) {
			errors.reject("cost", String.format("商品成本($%d)不可以小於0", productDto.getCost()));
		}
		
		// 成本不可以高於售價
		if(productDto.getCost() > productDto.getPrice()) {
			String field = "price";
			String errorCode = "validation.error.product.price.canNotLessThenCost";
			Object[] errorArgs = {productDto.getPrice(), productDto.getCost()};
			String defaultMessage = "商品價格不可以低於成本";
			errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
		}
		
	}
	
	
	
}
