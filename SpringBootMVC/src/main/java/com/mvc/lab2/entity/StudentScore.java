package com.mvc.lab2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class StudentScore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
