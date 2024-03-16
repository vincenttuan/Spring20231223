package com.mvc.model;

public record Quote(
		String 最低, String 昨收, String 漲跌, String 最高, String 成交, String 均價, String 成交金額,
		String 漲跌幅, String 振幅, String 開盤, String 昨量, String 總量) {}
