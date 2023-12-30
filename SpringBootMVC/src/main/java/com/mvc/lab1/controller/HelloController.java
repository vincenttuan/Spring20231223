package com.mvc.lab1.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	// 1. 取得服務位置並回傳字串
	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "Hello SpringBoot " + new Date();
	}
	
	// 2. ? 帶參數
	// http://localhost:8080/hello/sayhi?name=John&age=18
	// http://localhost:8080/hello/sayhi?name=Mary
	@GetMapping(value = "/sayhi")
	@ResponseBody
	public String sayhi(@RequestParam(value = "name", required = true) String name,
						@RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
		
		String message = String.format("Hi %s, %d", name, age);
		return message;
	}
	
	// 3. Lab 練習
	// http://localhost:8080/hello/bmi?h=170&w=60 -> 印出 bmi = 20.76
	// http://localhost:8080/hello/bmi -> No data
	@GetMapping(value = "/bmi")
	@ResponseBody
	public String bmi(@RequestParam(value = "h", required = false, defaultValue = "-1") Double h,
					  @RequestParam(value = "w", required = false, defaultValue = "-1") Double w) {
		if(h < 0 || w < 0) {
			return "No data";
		}
		
		String message = String.format("bmi = %.2f", w / Math.pow(h/100, 2));
		return message;
	}
	
	
	
}
