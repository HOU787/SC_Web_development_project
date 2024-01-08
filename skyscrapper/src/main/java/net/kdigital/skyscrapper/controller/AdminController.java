package net.kdigital.skyscrapper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.skyscrapper.domain.Inquiry;
import net.kdigital.skyscrapper.service.AdminService;

@Slf4j
@Controller
public class AdminController {
   
   @Autowired
   AdminService adminService;
   
   // admin 페이지 요청
   @GetMapping("/admin")
   public String admin() {
      return "/admin/admin";
   }

   // Inqury pending 페이지 요청
   @GetMapping("/admin/inquiry")
   public String adminInq(Model model) {
      
      List<Inquiry> pendingInq = adminService.selectPendingInq();      
      
      model.addAttribute("pendingInq", pendingInq);
      
      return "/admin/admininq";
   }
   
   // pending 페이지의 inquiry 삭제
   @GetMapping("/admin/inquiry/deleteinq")
   @ResponseBody
   public String deleteinq(int inq_id) {
      log.info("삭제할 inq_id: {}", inq_id);

      int result = adminService.deleteInq(inq_id);

      return "success";
   }
   
   @GetMapping("/admin/inquiry/updateinq")
   @ResponseBody
   public String updateinq(int inq_id) {
      log.info("수정데이터: {}", inq_id);

      int result = adminService.updateInq(inq_id); 

      return "success";
   }
   

   // Product pending 페이지 요청
   @GetMapping("/admin/product")
   public String adminPro(Model model) {
      
      List<Inquiry> pendingPro = adminService.selectPendingPro();      
      
      model.addAttribute("pendingPro", pendingPro);
      
      return "/admin/adminpro";
   }
   
   // pending 페이지의 product 삭제
   @GetMapping("/admin/product/deletepro")
   @ResponseBody
   public String deletepro(int product_id) {
      log.info("삭제할 product_id: {}", product_id);

      int result = adminService.deletePro(product_id);

      return "success";
   }
   
   @GetMapping("/admin/product/updatepro")
   @ResponseBody
   public String updatepro(int product_id) {
      log.info("수정데이터: {}", product_id);

      int result = adminService.updatePro(product_id); 

      return "success";
   }
   
   


   
}