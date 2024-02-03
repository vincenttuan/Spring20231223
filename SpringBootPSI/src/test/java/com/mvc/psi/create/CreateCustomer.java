package com.mvc.psi.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.psi.model.dto.CustomerDto;
import com.mvc.psi.service.CustomerService;

@SpringBootTest
public class CreateCustomer {
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void test() {
		CustomerDto customerDto1 = new CustomerDto();
		customerDto1.setName("John");
		
		CustomerDto customerDto2 = new CustomerDto();
		customerDto2.setName("Mary");
		
		CustomerDto customerDto3 = new CustomerDto();
		customerDto3.setName("Helen");
		
		// 儲存
		customerService.add(customerDto1);
		customerService.add(customerDto2);
		customerService.add(customerDto3);
		
		System.out.println("Save OK !");
		
	}
	
	
}
