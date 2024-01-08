package net.kdigital.skyscrapper.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.skyscrapper.domain.Inquiry;
import net.kdigital.skyscrapper.mapper.AdminMapper;
import net.kdigital.skyscrapper.mapper.InquiryMapper;
import net.kdigital.skyscrapper.mapper.ProductMapper;

@Slf4j
@Service
public class AdminService {

   @Autowired
   AdminMapper adminMapper;
   
   @Autowired
   InquiryMapper inquiryMapper;
   
   @Autowired
   ProductMapper productMapper;

   // 전체 Pending Inquiry 요청
   public List<Inquiry> selectPendingInq() {
      
      Map<String, Object> paramMap = new HashMap<>();
      
      List<Inquiry> pendinginq = adminMapper.selectPendingInq(paramMap);
      
//      log.info(pendinginq.toString());
      
      return pendinginq;
   }
   
   // 인콰이어리 1개 삭제
      public int deleteInq(int inq_id) {
         int result = adminMapper.deleteInq(inq_id);
         return result;
      }
      
   // 인콰이어리 수정처리
      public int updateInq(int inq_id) {
         int result = adminMapper.updateInq(inq_id);
         return result;
      }
      
      
      
      // 전체 Pending Product 요청
      public List<Inquiry> selectPendingPro() {
         
         Map<String, Object> paramMap = new HashMap<>();
         
         List<Inquiry> pendingpro = adminMapper.selectPendingPro(paramMap);
         
//         log.info(pendinginq.toString());
         
         return pendingpro;
      }
      
      // 인콰이어리 1개 삭제
         public int deletePro(int product_id) {
            int result = adminMapper.deletePro(product_id);
            return result;
         }
         
      // 인콰이어리 수정처리
         public int updatePro(int product_id) {
            int result = adminMapper.updatePro(product_id);
            return result;
         }
         
      
      


}