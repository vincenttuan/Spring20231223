package com.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Order(下單資料)
// 帳號, 買賣方向, 股票代號, 價格, 數量
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private String username;
	private String bs;
	private String symbol;
	private Double price;
	private Integer amount;
}
