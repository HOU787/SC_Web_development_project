//package net.kdigital.skyscrapper.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import lombok.extern.slf4j.Slf4j;
//import net.kdigital.skyscrapper.domain.SentimentCount;
//import net.kdigital.skyscrapper.service.ChartService;
//
//@Controller
////@RequestMapping("/dashboard")
//@Slf4j
//public class DashboardController {
//	
//	@Autowired
//	ChartService service;
//	
////    @GetMapping("/dashboard")
////    public String dashboard() {
////       return "/dashboard/dashboard";
////    }
//    
//	@GetMapping("/bubble")
//	@ResponseBody
//	public List<SentimentCount> bubble() {
//		List<SentimentCount> list = service.getSentimentCount();
//		return list;
//	}
//}