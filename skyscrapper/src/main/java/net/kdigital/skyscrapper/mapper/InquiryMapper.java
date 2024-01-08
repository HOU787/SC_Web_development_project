package net.kdigital.skyscrapper.mapper;

import org.apache.ibatis.annotations.Mapper;

import net.kdigital.skyscrapper.domain.Inquiry;

@Mapper
public interface InquiryMapper {
   
   // 인콰이어리 전송
   public int sendInquiry(Inquiry inquiry);
   
   void updateInquiryPendingStatus(Inquiry inquiry);
}