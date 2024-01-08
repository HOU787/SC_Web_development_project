package net.kdigital.skyscrapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.skyscrapper.domain.Inquiry;
import net.kdigital.skyscrapper.service.InquiryService;

@Slf4j
@Controller
public class InquiryController {
   
   @Autowired
   InquiryService inquiryService;
   
   // 인콰이어리 화면 요청
   @GetMapping("/inquiry")
    public String popupInq() {
        return "/inquiry/inquiry";
    }
   
   
   // 인콰이어리 전송 요청
   @PostMapping("/category/inquiry")
   @ResponseBody
   public String sendInq(Inquiry inquiry) {
      log.info("인콰이어리: {}", inquiry.toString());
      // 인콰이어리에 사용될 ML
      
      int result = inquiryService.sendInquiry(inquiry);
      
      
      return "success";
   }
   
   
}