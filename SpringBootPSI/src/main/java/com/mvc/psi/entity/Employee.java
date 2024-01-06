package com.mvc.psi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
	@Id // @Id: 主鍵
	// @GeneratedValue: 自動產生值
	// strategy = GenerationType.IDENTITY : 自動增長, 適用於 MySQL
	// strategy = GenerationType.SEQUENCE : 自動增長, 適用於 Oracle
	// strategy = GenerationType.TABLE : 自動增長, 適用於所有資料庫
	// GenerationType.TABLE 會建立一個資料表, 用來紀錄目前的 id 值, 並且鎖定該筆資料, 避免同時新增時發生重複
	// strategy = GenerationType.AUTO : 自動增長, 由系統自動選擇適合的策略
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 員工序號
	
	//@Column(name = "name", unique = false, nullable = true, length = 255) // 預設(可不寫)
	@Column(name = "name", unique = true, nullable = false, length = 50)
	private String name; // 員工姓名
	
	@JoinColumn(name = "department_id") // 外鍵(部門序號)
	@ManyToOne(optional = false) // 該欄位不允許為 null 
	private Department department;
}
