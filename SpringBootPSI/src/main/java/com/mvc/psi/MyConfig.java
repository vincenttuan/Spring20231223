package com.mvc.psi;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
	
	// 註冊第三方資源
	// 註冊 ModelMapper
	public ModelMapper modelMapper() {
		return new ModelMapper();
	} 
	
}
