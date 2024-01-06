package com.mvc.psi.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "department")
@Data
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	// mappedBy = "department" : 這個屬性是由 Employee 的 department 屬性來對應
	// 意思是說, Employee 這個類別裡面有一個 department 屬性, 這個屬性是用來對應 Department 這個類別的, 這個屬性的名稱是 department, 
	// 所以要寫成 mappedBy = "department"
	// 若不加上 mappedBy = "department", 則會產生一個中間表格, 用來對應 Department 與 Employee 的關係
	// 中間表格的欄位名稱為 department_id, employee_id
	// 中間表格的欄位名稱可以透過 @JoinColumn(name = "department_id") 來指定
	@OneToMany(mappedBy = "department")
	private Set<Employee> employees = new LinkedHashSet<>();
}
