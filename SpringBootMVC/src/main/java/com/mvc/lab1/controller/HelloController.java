package com.mvc.lab1.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "Hello SpringBoot " + new Date();
	}
}
