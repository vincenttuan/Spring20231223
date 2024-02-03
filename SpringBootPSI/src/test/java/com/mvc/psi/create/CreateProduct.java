package com.mvc.psi.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.psi.model.dto.ProductDto;
import com.mvc.psi.service.ProductService;

@SpringBootTest
public class CreateProduct {
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void test() {
		ProductDto productDto1 = new ProductDto();
		productDto1.setName("烤雞腿");
		productDto1.setCost(40);
		productDto1.setPrice(120);
		
		ProductDto productDto2 = new ProductDto();
		productDto2.setName("肉圓");
		productDto2.setCost(20);
		productDto2.setPrice(50);
		
		// 儲存
		productService.add(productDto1);
		productService.add(productDto2);
		
		System.out.println("Save OK !");
		
	}
	
}

