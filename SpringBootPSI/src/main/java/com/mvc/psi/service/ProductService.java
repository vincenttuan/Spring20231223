package com.mvc.psi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.ProductDto;
import com.mvc.psi.model.po.Product;
import com.mvc.psi.model.vo.Inventory;
import com.mvc.psi.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增
	public void add(ProductDto productDto) {
		// Dto 轉 Po
		Product product = modelMapper.map(productDto, Product.class);
		productRepository.save(product);
	}
	
	// 修改
	public void update(ProductDto productDto, Long id) {
		Optional<Product> productOpt = productRepository.findById(id);
		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			product.setName(productDto.getName());
			product.setCost(productDto.getCost());
			product.setPrice(productDto.getPrice());
			productRepository.save(product);
			return;
		}
		throw new RuntimeException("修改失敗: 無此資料");
	}
	
	// 刪除
	public void delete(Long id) {
		Optional<Product> productOpt = productRepository.findById(id);
		if(productOpt.isPresent()) {
			productRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("刪除失敗: 無此資料");
	}
	
	// 查詢單筆
	public ProductDto getProductDtoById(Long id) {
		Optional<Product> productOpt = productRepository.findById(id);
		if(productOpt.isPresent()) {
			ProductDto productDto = modelMapper.map(productOpt.get(), ProductDto.class);
			return productDto;
		}
		return null;
	}
	
	// 查詢多筆
	public List<ProductDto> findAll() {
		return productRepository.findAll().stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.toList();
	}
	
	// 庫存查詢
	public Inventory findInventoryById(Long id) {
		return productRepository.findInventoryById(id);
	}
	
	public List<Inventory> queryInventory() {
		return productRepository.queryInventory();
	}
	
}
