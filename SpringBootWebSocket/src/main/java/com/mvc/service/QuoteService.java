package com.mvc.service;

import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.mvc.model.Quote;

// 取得股價報價服務
@Service
public class QuoteService {
	
	
	public Quote getQuote(String symbol) throws Exception {
		/*
		 * 股票交易資訊
		 * https://query1.finance.yahoo.com/v7/finance/download/2330.TW
		 * Date     Open High Low Close Adj Close Volume
		 * 2024/3/8 795  796  772 784   784       87139744
		 * */
		String url = String.format("https://query1.finance.yahoo.com/v7/finance/download/%s", symbol);
		String csvData = new Scanner(new URL(url).openStream()).useDelimiter("\\A").next();
		//System.out.println(csvData);
		// 解析 CSV 數據資料
		CSVRecord lastRecord = null;
		try(CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			List<CSVRecord> records = parser.getRecords();
			if(!records.isEmpty()) {
				lastRecord = records.get(records.size() - 1);
			}
		}
		//System.out.println(lastRecord);
		if(lastRecord != null) {
			String date = lastRecord.get("Date");
			String open = lastRecord.get("Open");
			String high = lastRecord.get("High");
			String low = lastRecord.get("Low");
			String close = lastRecord.get("Close");
			String volume = lastRecord.get("Volume");
			return new Quote(symbol, date, open, high, low, close, volume);
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new QuoteService().getQuote("2330.TW"));
	}
}