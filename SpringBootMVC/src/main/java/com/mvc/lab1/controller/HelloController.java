package com.mvc.lab1.controller;

import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;

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
	// http://localhost:8080/hello/bmi -> 參數錯誤
	@GetMapping(value = "/bmi", produces = {"text/plain;charset=utf-8"})
	@ResponseBody
	public String bmi(@RequestParam(value = "h", required = false, defaultValue = "-1") Double h,
					  @RequestParam(value = "w", required = false, defaultValue = "-1") Double w) {
		if(h < 0 || w < 0) {
			return "參數錯誤";
		}
		
		String message = String.format("bmi = %.2f", w / Math.pow(h/100, 2));
		return message;
	}
	
	// 4. 多筆資料
	// http://localhost:8080/hello/age?age=17&age=19&age=25
	// 取得統計資料, count, max, min, avg, sum
	@GetMapping("/age")
	@ResponseBody
	public String getAgeInfo(@RequestParam("age") List<Integer> ages) {
		IntSummaryStatistics stat = ages.stream().mapToInt(Integer::intValue).summaryStatistics();
		return stat.toString();
	}
	
	// 5. Lab 多筆資料 (奧運跳水比賽)
	// 成績算法: 10 個分數, 將最高的 2 個分數與最低的 2 個分數刪除, 其餘 6 個取平均
	// http://localhost:8080/hello/score?score=10.0&score=3.8&score=8.5&score=7.4&score=8.8&score=7.8&score=9.0&score=6.5&score=7.0&score=9.8
	
	
	
}
