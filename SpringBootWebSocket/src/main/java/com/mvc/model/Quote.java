package com.mvc.model;

// adr: 每股振幅
public record Quote(
		String low, String preclose, String change, String high, String last, String avgprice, String turnover,
		String changepercentage, String adr, String open, String prevolume, String totalvolune) {}
