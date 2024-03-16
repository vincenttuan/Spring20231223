package com.mvc.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.mvc.model.Quote;

// 取得股價報價服務
@Service
public class QuoteService {
	
	public synchronized Quote getQuoteForYahooStock(String symbol) throws Exception {
		String url = "https://tw.stock.yahoo.com/quote/" + symbol;
		Document doc = null;
		try {
			// 注意：因為要掠過 SSL 驗證建議使用 jsoup 1.11.1, 之後的版本會有問題
			doc = Jsoup.connect(url).validateTLSCertificates(false).get();
		} catch (org.jsoup.HttpStatusException e2) {
			Random rand = new Random();
			return new Quote(
					symbol,
					rand.nextInt(1000) + "", 
					rand.nextInt(1000) + "",
					rand.nextInt(1000) + "",
					rand.nextInt(1000) + "",
					rand.nextInt(1000) + "",
					rand.nextInt(1000) + "",
					rand.nextInt(1000) + "",
					rand.nextInt(1000) + "",
					rand.nextInt(1000) + "",
					rand.nextInt(1000) + "",
					rand.nextInt(1000) + "",
					rand.nextInt(1000) + "");
		}
		
		//Document doc = Jsoup.connect(url).get();
		// 找到 <ul> 下的 <li class="price-detail-item"> 前面幾個字是 price-detail-item 的元素
		Elements elements = doc.select("ul > li.price-detail-item");
		//System.out.println(elements);
		// 過濾 html tag
		Map<String, String> priceMap = new HashMap<>();
		elements.forEach(e -> {
			// e 裡面有 2 個 <span> 分別印出 text
			Elements spans = e.select("span");
			String key = spans.get(0).text().replace(" ", "").replace("\n", "");
			String value = spans.get(1).text().replace("%", "").replace(",", "").replace(" ", "").replace("\n", "");
			priceMap.put(key, value);
		});
		Quote quote = new Quote(
				symbol,
				priceMap.get("最低"), 
				priceMap.get("昨收"),
				priceMap.get("漲跌"),
				priceMap.get("最高"),
				priceMap.get("成交"),
				priceMap.get("均價"),
				priceMap.get("成交金額(億)"),
				priceMap.get("漲跌幅"),
				priceMap.get("振幅"),
				priceMap.get("開盤"),
				priceMap.get("昨量"),
				priceMap.get("總量"));
		return quote;
	}
	
	private Quote getQuoteForYahooFinance(String symbol) throws Exception {
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
			return null; // new Quote(symbol, date, open, high, low, close, volume);
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new QuoteService().getQuoteForYahooStock("2330.TW"));
		System.out.println(new QuoteService().getQuoteForYahooStock("2388"));
		System.out.println(new QuoteService().getQuoteForYahooStock("0050.TW"));
		System.out.println(new QuoteService().getQuoteForYahooStock("^TWII.TW"));
		System.out.println(new QuoteService().getQuoteForYahooStock("00632R.TW"));
	}
}
