package com.mvc.lab1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	// name=John&age=18&score=80.5&pass=true
	private String name;
	private Integer age;
	private Double score;
	private Boolean pass;
	
}
