package com.mvc.psi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class SpringBootPsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPsiApplication.class, args);
	}

}
