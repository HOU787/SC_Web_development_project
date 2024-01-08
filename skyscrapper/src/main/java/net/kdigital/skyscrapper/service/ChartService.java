package net.kdigital.skyscrapper.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kdigital.skyscrapper.domain.ReviewCount;
import net.kdigital.skyscrapper.domain.Sales;
import net.kdigital.skyscrapper.domain.SalesByBrands;
import net.kdigital.skyscrapper.domain.SentimentCount;
import net.kdigital.skyscrapper.domain.SentimentRate;
import net.kdigital.skyscrapper.domain.StarRate;
import net.kdigital.skyscrapper.mapper.ChartMapper;

@Service
public class ChartService {
	@Autowired
	ChartMapper mapper;
	
	public List<SentimentCount> getSentimentCount(String fromDate, String toDate) {
		Map<String, String> map = new HashMap<>();
		
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		
		List<SentimentCount>  list = mapper.getSentimentCount(map);
		System.out.println(list);
		return list;
	}

	public List<ReviewCount> getReviewCount(String fromDate, String toDate) {
		Map<String, String> map = new HashMap<>();
		
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		
		List<ReviewCount> list = mapper.getReviewCount(map);
		
		return list;
	}

	public List<SentimentRate> getSentimentRate(String fromDate, String toDate) {
		Map<String, String> map = new HashMap<>();
		
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		
		List<SentimentRate>  list = mapper.getSentimentRate(map);
		return list;
	}

	public List<StarRate> getStarRate(String fromDate, String toDate) {
		Map<String, String> map = new HashMap<>();
		
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		
		
		List<StarRate> list = mapper.getStarRate(map);
		return list;
	}

	public List<Sales> getSales(String fromDate, String toDate) {
		Map<String, String> map = new HashMap<>();
		
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		
		List<Sales> list = mapper.getSales(map);
		return list;
	}

	public List<SalesByBrands> getSalesByBrands(String fromDate, String toDate) {
		Map<String, String> map = new HashMap<>();
		
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);		
		
		List<SalesByBrands> list = mapper.getSalesByBrands(map);
		return list;
	}


}
