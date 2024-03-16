package com.mvc.model;

// 成交單
// 成交編號, 成交狀態, 成交時間, 成交價格, 成交張數, 委託單 
public record TransactionReturn(String txId, String status, String time, Double price, Integer amount, CommissionReturn cr) {}
