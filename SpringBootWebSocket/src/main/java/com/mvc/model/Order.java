package com.mvc.model;

// Order(下單資料)
// 帳號, 買賣方向, 股票代號, 價格, 數量
public record Order(String username, String bs, String symbol, Double price, Integer amount) {}
