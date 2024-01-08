package net.kdigital.skyscrapper.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.kdigital.skyscrapper.domain.Product;

@Mapper
public interface ProductMapper {
	// 게시글 등록
	public int insertProduct(Product product);

	// 게시글 개수 조회
	public int getProductCount(Map<String, Object> map);

	// 게시글 목록 요청
	public List<Product> selectProductAll(Map<String, Object> map);
	
	// 제품 상세보기
	public Product selectProduct(int product_id);
	
	// 제품 보류 상태 업데이트
	void updateProductPendingStatus(Product product);
}
