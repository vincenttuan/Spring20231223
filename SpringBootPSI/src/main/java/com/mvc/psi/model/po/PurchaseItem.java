package com.mvc.psi.model.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// 採購單明細
@Entity
@Table(name = "purchase_item")
@Getter
@Setter
public class PurchaseItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Integer amount; // 採購數量
	
	@JoinColumn(name = "purchase_id") // 採購單序號
	@ManyToOne
	private Purchase purchase; // 採購單
	
	@JoinColumn(name = "product_id") // 商品序號
	@ManyToOne
	private Product product; // 商品

	@Override
	public String toString() {
		return "PurchaseItem [id=" + id + ", amount=" + amount + ", purchase=" + purchase + ", product=" + product
				+ "]";
	}
	
}
