package net.kdigital.skyscrapper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.kdigital.skyscrapper.domain.ReviewCount;
import net.kdigital.skyscrapper.domain.SentimentCount;

@Mapper
public interface KeywordMapper {
	
	public List<ReviewCount> getBarReviewCount(String Brand_name);

	public List<ReviewCount> getPieMarketShare(String brand_name);

	public List<ReviewCount> getRadarSentiment(String brand_name);

	public List<SentimentCount> getBubbleSentiment(String brand_name);

}
