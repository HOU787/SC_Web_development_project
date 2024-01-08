package net.kdigital.skyscrapper.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kdigital.skyscrapper.mapper.ReplyProductMapper;

@Service
public class ReplyProductService {
	
	
	private ReplyProductMapper replyProductMapper;

    public ReplyProductService(ReplyProductMapper replyProductMapper) {
        this.replyProductMapper = replyProductMapper;
    }

    public List<Map<String, Object>> getBrandRatingByDateRange(LocalDate startDate, LocalDate endDate) {
        return replyProductMapper.getBrandRatingByDateRange(startDate, endDate);
    }

    public List<Map<String, Object>> getProductAverageRatingByBrandAndDateRange(String brand_name, LocalDate startDate, LocalDate endDate) {
        return replyProductMapper.getProductAverageRatingByBrandAndDateRange(brand_name, startDate, endDate);
    }

    public List<Map<String, Object>> getHourlyAverageRatingByBrandProductAndDateRange(String brand_name, String product_name, LocalDate startDate, LocalDate endDate) {
        return replyProductMapper.getHourlyAverageRatingByBrandProductAndDateRange(brand_name, product_name, startDate, endDate);
    }
}
