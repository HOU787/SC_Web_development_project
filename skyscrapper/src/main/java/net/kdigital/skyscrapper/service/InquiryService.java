package net.kdigital.skyscrapper.service;

import java.util.Collections;
import java.util.HashMap;
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
import net.kdigital.skyscrapper.domain.Inquiry;
import net.kdigital.skyscrapper.mapper.InquiryMapper;

@Service
@Slf4j
public class InquiryService {

    @Autowired
    InquiryMapper inquiryMapper;

    @Value("${inq.predict.server}")
    String url;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    
    public int sendInquiry(Inquiry inquiry) {
        log.info("모델 적용 전 inquiry: {}", inquiry.toString());
        log.info("FastAPI 서버 URL: {}", url);

        
        
//        int result = inquiryMapper.sendInquiry(inquiry);

        // 머신러닝 모델 호출 및 결과 처리
        try {
            Map<String, String> param = new HashMap<>();
            param.put("message", inquiry.getMessage());
            
            log.info("message = {}", inquiry.getMessage());
            
            // 헤더 준비
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            RestTemplate restTemplate = restTemplateBuilder.build();
            
            log.info("url 정보 = {}",url.toString());
//            url += "/predinquiry";
            
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
            
            inquiry.setPending_status(pendingStatus);
            inquiryMapper.updateInquiryPendingStatus(inquiry);

//            log.info("머신러닝 결과 처리 완료. pending_status: {}", pendingStatus);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("머신러닝 서버 오류: {}", e.getMessage());
            // 오류 처리
        } catch (Exception e) {
            log.error("머신러닝 결과 처리 오류: {}", e.getMessage());
            // 오류 처리
        }
        
        int result = inquiryMapper.sendInquiry(inquiry);
        
        return result;
        
    }
    
}