package net.kdigital.skyscrapper.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.kdigital.skyscrapper.domain.ReviewCount;
import net.kdigital.skyscrapper.domain.Sales;
import net.kdigital.skyscrapper.domain.SalesByBrands;
import net.kdigital.skyscrapper.domain.SentimentCount;
import net.kdigital.skyscrapper.domain.SentimentRate;
import net.kdigital.skyscrapper.domain.StarRate;

@Mapper
public interface ChartMapper {

	public List<SentimentCount> getSentimentCount(Map<String, String> map);

	public List<ReviewCount> getReviewCount(Map<String, String> map);

	public List<SentimentRate> getSentimentRate(Map<String, String> map);

	public List<StarRate> getStarRate(Map<String, String> map);

	public List<Sales> getSales(Map<String, String> map);

	public List<SalesByBrands> getSalesByBrands(Map<String, String> map);

}
