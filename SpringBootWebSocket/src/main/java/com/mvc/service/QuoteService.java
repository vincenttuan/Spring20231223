package com.mvc.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	public Quote getQuoteForYahooStock(String symbol) throws Exception {
		return null;
	}
	
	public Quote getQuoteForYahooFinance(String symbol) throws Exception {
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
		
		String url = "https://tw.stock.yahoo.com/quote/2330.TW";
		// 注意：因為要掠過 SSL 驗證建議使用 jsoup 1.11.1, 之後的版本會有問題
		Document doc = Jsoup.connect(url).validateTLSCertificates(false).get();
		//Document doc = Jsoup.connect(url).get();
		// 找到 <ul> 下的 <li class="price-detail-item"> 前面幾個字是 price-detail-item 的元素
		Elements elements = doc.select("ul > li.price-detail-item");
		//System.out.println(elements);
		// 過濾 html tag
		List<String> priceList = new ArrayList<>();
		elements.forEach(e -> {
			// e 裡面有 2 個 <span> 分別印出 text
			Elements spans = e.select("span");
			//String key = spans.get(0).text().replace(" ", "").replace("\n", "");
			String value = spans.get(1).text().replace("%", "").replace(",", "").replace(" ", "").replace("\n", "");
			priceList.add(value);
		});
		System.out.println(priceList);
		//{最低=753, 昨收=784, 漲跌=31.00, 最高=777, 成交=753, 均價=762, 成交金額(億)=558.06, 漲跌幅=3.95, 振幅=3.06, 開盤=771, 昨量=41961, 總量=73222}
		
	}
}
