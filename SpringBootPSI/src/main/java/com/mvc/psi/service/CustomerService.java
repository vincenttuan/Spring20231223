package com.mvc.psi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.CustomerDto;
import com.mvc.psi.model.po.Customer;
import com.mvc.psi.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增
	public void add(CustomerDto customerDto) {
		// Dto 轉 Po
		Customer customer = modelMapper.map(customerDto, Customer.class);
		customerRepository.save(customer);
	}
	
	// 修改
	public void update(CustomerDto customerDto, Long id) {
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if(customerOpt.isPresent()) {
			Customer customer = customerOpt.get();
			customer.setName(customerDto.getName());
			customerRepository.save(customer);
			return;
		}
		throw new RuntimeException("修改失敗: 無此資料");
	}
	
	// 刪除
	public void delete(Long id) {
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if(customerOpt.isPresent()) {
			customerRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("刪除失敗: 無此資料");
	}
	
	// 查詢單筆
	public CustomerDto getCustomerDtoById(Long id) {
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if(customerOpt.isPresent()) {
			Customer customer = customerOpt.get();
			// Po 轉 Dto
			CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
			return customerDto;
		}
		return null;
	}
	
	// 查詢全部
	
	
}
