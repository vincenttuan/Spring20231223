package com.mvc.model;

// 下單資訊
public record Order(String username, String bs, String symbol, Double price, Integer amount) {}
