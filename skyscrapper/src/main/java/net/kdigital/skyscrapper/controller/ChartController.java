package net.kdigital.skyscrapper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.kdigital.skyscrapper.domain.ReviewCount;
import net.kdigital.skyscrapper.domain.Sales;
import net.kdigital.skyscrapper.domain.SalesByBrands;
import net.kdigital.skyscrapper.domain.SentimentCount;
import net.kdigital.skyscrapper.domain.SentimentRate;
import net.kdigital.skyscrapper.domain.StarRate;
import net.kdigital.skyscrapper.service.ChartService;

@Controller
public class ChartController {
	
	@Autowired
	ChartService service;

	@GetMapping("/bubble")
	@ResponseBody
	public List<SentimentCount> bubble(
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		List<SentimentCount> list = service.getSentimentCount(fromDate,toDate);
		return list;
	}
	
	@GetMapping("/bar")
	@ResponseBody
	public List<ReviewCount> bar(
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
	    List<ReviewCount> list = service.getReviewCount(fromDate, toDate);
	    return list;
		
	}
	
	@GetMapping("/radar")
	@ResponseBody
	public List<SentimentRate> radar(
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		List<SentimentRate> list = service.getSentimentRate(fromDate, toDate);
		return list;
	}
	@GetMapping("/spline1")
	@ResponseBody
	public List<StarRate> spline1(
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		List<StarRate> list = service.getStarRate(fromDate,toDate);
		
		return list;
	}
	
	@GetMapping("/spline2")
	@ResponseBody
	public List<Sales> spline2(
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		List<Sales> list = service.getSales(fromDate, toDate);
		return list;
	}
	
	@GetMapping("/spline3")
	@ResponseBody
	public List<SalesByBrands> spline3(
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		List<SalesByBrands> list = service.getSalesByBrands(fromDate, toDate);
		return list;
	}
	
	
	@GetMapping({"/bubblelist"})
	public String bubblelist() {
		return "/template/bubble";
	}
//	@GetMapping({"/barlist"})
//	public String barlist() {
//		return "/template/bar";
//	}
	@GetMapping({"/barlist"})
	public String barlist() {
		return "/dashboard/bar";
	}
	
	@GetMapping({"/splinelist1"})
	public String splinelist1() {
		return "/template/spline1";
	}
	@GetMapping({"/splinelist2"})
	public String splinelist2() {
		return "/template/spline2";
	}
	@GetMapping({"/splinelist3"})
	public String splinelist3() {
		return "/template/spline3";
	}
	@GetMapping({"/radarlist"})
	public String radarlist() {
		return "/template/radar";
	}
	@GetMapping("/db")
	public String dashboard() {
		return "/dashboard/dashboard";
	}
}
