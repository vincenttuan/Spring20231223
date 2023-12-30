package com.mvc.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.lab2.entity.StudentScore;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {
	
}
