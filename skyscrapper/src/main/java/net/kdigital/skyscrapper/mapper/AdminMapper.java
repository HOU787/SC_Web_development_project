package net.kdigital.skyscrapper.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.kdigital.skyscrapper.domain.Inquiry;

@Mapper
public interface AdminMapper {
   // Pending Inquiry List 요청
   public List<Inquiry> selectPendingInq(Map<String, Object> map);
   
   // Inquiry 삭제 요청(block_status 1로 수정)
   public int deleteInq(int inq_id);
   
   // Inquiry 수정 요청
   public int updateInq(int inq_id);
   
   // Pending Product List 요청
   public List<Inquiry> selectPendingPro(Map<String, Object> map);
      
   // Product 삭제 요청(block_status 1로 수정)
   public int deletePro(int product_id);
      
   // Product 수정 요청
   public int updatePro(int product_id);
}