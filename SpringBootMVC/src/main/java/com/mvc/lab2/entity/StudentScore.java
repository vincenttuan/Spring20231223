package com.mvc.lab2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentScore {
	
	private Integer id;
	private String name;
	private Integer chineseScore;
	private Integer englishScore;
	private Integer mathScore;
	private Integer totalScore;
	private Double averageScore;
	
	public StudentScore(String name, Integer chineseScore, Integer englishScore, Integer mathScore) {
		this.name = name;
		this.chineseScore = chineseScore;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.totalScore = chineseScore + englishScore + mathScore;
		this.averageScore = totalScore / 3.0;
	}
	
	
	
}
