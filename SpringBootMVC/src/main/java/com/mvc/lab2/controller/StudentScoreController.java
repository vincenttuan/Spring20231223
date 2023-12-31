package com.mvc.lab2.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.javafaker.Faker;
import com.mvc.lab2.entity.StudentScore;
import com.mvc.lab2.repository.StudentScoreRepository;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/student")
public class StudentScoreController {
	
	@Autowired
	private StudentScoreRepository studentScoreRepository;
	
	// 隨機生成一筆資料列紀錄
	@GetMapping("/add")
	@ResponseBody
	public String addStudentRandom() {
		Faker faker = new Faker();
		Random random = new Random();
		
		String name = faker.name().fullName();
		Integer chineseScore = random.nextInt(101);
		Integer englishScore = random.nextInt(101);
		Integer mathScore = random.nextInt(101);
		
		StudentScore studentScore = new StudentScore(name, chineseScore, englishScore, mathScore);
		// save to table
		studentScoreRepository.save(studentScore);
		
		return studentScore + " 新增完畢";
	}
	
	@GetMapping("/{id}")
	public String getStudentScoreById(@PathVariable("id") Integer id, Model model) {
		Optional<StudentScore> studentScoreOpt = studentScoreRepository.findById(id);
		if(studentScoreOpt.isPresent()) {
			//model.addAttribute("student_score", studentScoreOpt.get());
			model.addAttribute("studentScore", studentScoreOpt.get()); // model attribute
		}
		return "student_scores_upt";
	}
	
	@GetMapping("/")
	//@ResponseBody
	public String findAllStudentScores(Model model) {
		List<StudentScore> scores = studentScoreRepository.findAll();
		// 將 scores 資料放到 model 物件中
		model.addAttribute("scores", scores);
		// 透過指定 html 來渲染資料
		return "student_scores";
	}
	
	// 更新資料
	@PutMapping("/{id}")
	@ResponseBody
	@Transactional
	public String updateStudentScore(@PathVariable("id") Integer id, StudentScore studentScore) {
		Optional<StudentScore> studentScoreOpt = studentScoreRepository.findById(id);
		if(studentScoreOpt.isPresent()) {
			// 因為 studentScore 含有 id, 所以會進行更新
			// 若無 id 則會進行新增
			studentScoreRepository.save(studentScore); 
			return "<a href='./' title='按我一下返回主畫面'>修改成功</a>";
		}
		return "<a href='./' title='按我一下返回主畫面'>修改失敗</a>";
		
	}
	
	// 刪除資料
	@DeleteMapping("/{id}")
	@ResponseBody
	@Transactional
	public String deleteStudentScore(@PathVariable("id") Integer id) {
		Optional<StudentScore> studentScoreOpt = studentScoreRepository.findById(id);
		if(studentScoreOpt.isPresent()) {
			studentScoreRepository.delete(studentScoreOpt.get()); 
			return "<a href='./' title='按我一下返回主畫面'>刪除成功</a>";
		}
		return "<a href='./' title='按我一下返回主畫面'>刪除失敗</a>";
		
	}
	
	
}
