package net.kdigital.skyscrapper.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.skyscrapper.domain.Product;
import net.kdigital.skyscrapper.mapper.ProductMapper;

@Service
@Slf4j
public class ProductService {
   
   @Value("${pro.predict.server}")
   String url;
   
   @Autowired
   ProductMapper productMapper;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;
   
   /**
    * 게시글 등록 요청
    * @param product 제품 domain
    * @return 
    */
   public int insertProduct(Product product) {
      
      try {
            Map<String, String> param = new HashMap<>();
            param.put("product_info", product.getProduct_info());
            
            log.info(param.toString());
            
            // 헤더 준비
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            RestTemplate restTemplate = restTemplateBuilder.build();
            log.info("프로덕트", restTemplate.toString());
            log.info("url 정보",url);
//            url += "/predproduct";
            // POST 전송
            ResponseEntity<String> response = restTemplate.postForEntity(url, param, String.class);
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String responseBody = response.getBody();
            Map<String, Object> resultData = gson.fromJson(response.getBody(), Map.class);
            
            log.info("머신러닝 결과 resultData: {}", resultData);
            
            // 머신러닝 결과 처리
            String pendingStatusStr = (String) resultData.get("pending_status");
            int pendingStatus = Integer.parseInt(pendingStatusStr);
//            int blockStatus = (int) resultData.get("pending_status");
            
            product.setPending_status(pendingStatus);
            productMapper.updateProductPendingStatus(product);

//            log.info("머신러닝 결과 처리 완료. pending_status: {}", pendingStatus);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("머신러닝 서버 오류: {}", e.getMessage());
            // 오류 처리
        } catch (Exception e) {
            log.error("머신러닝 결과 처리 오류: {}", e.getMessage());
            // 오류 처리
        }
        
        int result = productMapper.insertProduct(product);
        
        log.info(product.toString());
        
        return result;
        
    }
      
      
      
      

   /**
    * 게시글 개수 요청
    * @param searchItem
    * @param searchWord
    * @return
    */
   public int getProductCount(String searchItem, String searchWord) {
      Map<String, Object> map = new HashMap<>();
      
      map.put("searchItem", searchItem);
      map.put("searchWord", searchWord);
      
      int totalRecordCount = productMapper.getProductCount(map);
      
      return totalRecordCount;
   }

   
   /**
    * 전체 상품 목록 요청
    * @param startRecord
    * @param endRecord
    * @param searchItem
    * @param searchWord
    * @return
    */
   public List<Product> selectProductAll(
         int srow,
         int erow,
         String searchItem,
         String searchWord) {
      
      Map<String, Object> map = new HashMap<>();
      
      map.put("srow", srow);
      map.put("erow", erow);
      map.put("searchItem", searchItem);
      map.put("searchWord", searchWord);
      
      List<Product> listproduct = productMapper.selectProductAll(map);
      
      return listproduct;
   }

   public Product selectProduct(int product_id) {
      
      Product product = productMapper.selectProduct(product_id);
      
      return product;
   }
}