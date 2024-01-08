package net.kdigital.skyscrapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kdigital.skyscrapper.domain.ReviewCount;
import net.kdigital.skyscrapper.domain.SentimentCount;
import net.kdigital.skyscrapper.mapper.KeywordMapper;

@Service
public class KeywordService {

	@Autowired
	KeywordMapper mapper;
	
	public List<ReviewCount> getBarReviewCount(String brand_name) {
		
		
		List<ReviewCount> list = mapper.getBarReviewCount(brand_name);
		
		return list;
	}

	public List<ReviewCount> getPieMarketShare(String brand_name) {
		
		List<ReviewCount> list = mapper.getPieMarketShare(brand_name);
		
		return list;
	}

	public List<ReviewCount> getRadarSentiment(String brand_name) {
		List<ReviewCount> list = mapper.getRadarSentiment(brand_name);
		
		return list;
	}

	public List<SentimentCount> getBubbleSentiment(String brand_name) {
		List<SentimentCount> list = mapper.getBubbleSentiment(brand_name);
		
		return list;
	}
}
