package com.mvc.service;

import java.net.URL;
import java.util.Scanner;

import org.springframework.stereotype.Service;

// 取得股價報價服務
@Service
public class QuoteService {
	
	
	public static void main(String[] args) throws Exception {
		/*
		 * 股票交易資訊
		 * https://query1.finance.yahoo.com/v7/finance/download/2330.TW
		 * Date     Open High Low Close Adj Close Volume
		 * 2024/3/8 795  796  772 784   784       87139744
		 * */
		String url = String.format("https://query1.finance.yahoo.com/v7/finance/download/%s", "2330.TW");
		String csvData = new Scanner(new URL(url).openStream()).useDelimiter("\\A").next();
		System.out.println(csvData);
		
	}
	
}
