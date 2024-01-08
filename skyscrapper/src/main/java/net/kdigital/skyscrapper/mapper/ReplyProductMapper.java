package net.kdigital.skyscrapper.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyProductMapper {
	List<Map<String, Object>> getBrandRatingByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Map<String, Object>> getProductAverageRatingByBrandAndDateRange(@Param("brand_name") String brand_name, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Map<String, Object>> getHourlyAverageRatingByBrandProductAndDateRange(@Param("brand_name") String brand_name, @Param("productName") String productName, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
