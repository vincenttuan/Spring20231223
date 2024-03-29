package com.mvc.psi.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.psi.model.dto.SupplierDto;
import com.mvc.psi.service.SupplierService;

@SpringBootTest
public class CreateSupplier {
	
	@Autowired
	private SupplierService supplierService;
	
	@Test
	public void test() {
		SupplierDto supplierDto1 = new SupplierDto();
		supplierDto1.setName("供應商一");
		
		SupplierDto supplierDto2 = new SupplierDto();
		supplierDto2.setName("供應商二");
		
		SupplierDto supplierDto3 = new SupplierDto();
		supplierDto3.setName("供應商三");
		
		// 儲存
		supplierService.add(supplierDto1);
		supplierService.add(supplierDto2);
		supplierService.add(supplierDto3);
		
		System.out.println("Save OK !");
		
	}
	
	
}