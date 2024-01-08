package net.kdigital.skyscrapper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.skyscrapper.domain.ReviewCount;
import net.kdigital.skyscrapper.domain.SentimentCount;
import net.kdigital.skyscrapper.service.KeywordService;

@Controller
@Slf4j
public class KeywordController {
	
	@Autowired
	KeywordService service;
	
	@GetMapping("/category/keywords")
	public String keywords() {
		return "/category/keywords";
	}
	
	@GetMapping("/category/brand_bar")
	@ResponseBody
	public List<ReviewCount> brandBar(
		@RequestParam("brand_name") String brand_name){
	    List<ReviewCount> list = service.getBarReviewCount(brand_name);

	    return list;
		
	}
	
	@GetMapping("/category/brand_pie")
	@ResponseBody
	public List<ReviewCount> brandPie(
		@RequestParam("brand_name") String brand_name){
	    List<ReviewCount> list = service.getPieMarketShare(brand_name);

	    return list;
		
	}
	
	@GetMapping("/category/brand_radar")
	@ResponseBody
	public List<ReviewCount> brandRadar(
		@RequestParam("brand_name") String brand_name){
	    List<ReviewCount> list = service.getRadarSentiment(brand_name);

	    return list;
		
	}
	
	@GetMapping("/category/brand_bubble")
	@ResponseBody
	public List<SentimentCount> bubble(
			@RequestParam("brand_name") String brand_name) {
		List<SentimentCount> list = service.getBubbleSentiment(brand_name);
		return list;
	}
}