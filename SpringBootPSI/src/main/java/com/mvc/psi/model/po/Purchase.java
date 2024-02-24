package com.mvc.psi.model.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

// 採購單
@Entity
@Table(name = "purchase")
@Getter
@Setter
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 採購單序號
	
	@Column
	@Temporal(TemporalType.DATE) // 存放到資料表欄位的格式
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 日期格式
	private Date date; // 採購日期
	
	@JoinColumn(name = "supplier_id") // 供應商序號(外鍵)
	@ManyToOne
	private Supplier supplier; // 供應商
	
	@JoinColumn(name = "employee_id") // 員工(外鍵)
	@ManyToOne
	private Employee employee; // 員工
	
	// FetchType.EAGER 主動查詢
	// FetchType.LAZY 被動查詢
	@OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
	private List<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>(); // 採購單明細集合
	
	@Override
	public String toString() {
		return "Purchase [id=" + id + ", date=" + date + ", supplier=" + supplier + ", employee=" + employee + "]";
	}
	
}
