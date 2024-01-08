package net.kdigital.skyscrapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.skyscrapper.domain.Member;
import net.kdigital.skyscrapper.service.MemberService;


@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

	@Autowired
	MemberService service;
	
	/**
	 * 회원 가입을 위한 화면 요청
	 * @return
	 */
	@GetMapping("/signup")
	public String signup() {
		return "/member/signup";
	}
	
	/**
	 * 회원가입 처리 요청
	 * @param member
	 * @return
	 */
	@PostMapping("/signup")
	public String signup(Member member) {
		log.info("회원정보: {}", member.toString());
		
		service.insertMember(member);
		
		return "redirect:/";
	}
	
	/**
	 * 로그인을 위한 화면 요청
	 * @return
	 */
	@GetMapping("/signin")
	public String signin() {
		return "/member/signin";
	}
	
	/**
	 * 개인정보 수정을 위한 비번체크 화면 요청 ==> 로그인이 된 상태
	 * @return
	 */
	@GetMapping("/mypage")
	public String mypage() {
		return "/member/pwcheck";
	}
	
	// 개인정보 수정을 위해 DB에서 정보를 가져옴
		@PostMapping("/pwcheck")
		public String pwdCheck(String member_id, String member_pw, Model model) {

			Member m = service.selectMember(member_id, member_pw);
			
			if(m != null) { // 수정하는 화면으로 이동
				// 사용자의 전화번호 01711112222
				String member_nm = m.getMember_nm();
				
				model.addAttribute("member_nm", member_nm);
				model.addAttribute("member", m);
				
				return "/member/mypage";
			}
			else 
				return "redirect:/";
		}
		
		/**
		 * 회원정보 수정 요청
		 * @param member
		 * @return
		 */
		@PostMapping("/modify")
		public String modify(@AuthenticationPrincipal UserDetails user, 
				Member member) {
			
			member.setMember_id(user.getUsername());
			log.info("회원정보: {}", member.toString());
			
			service.updateMember(member);
			
			return "redirect:/logout";
		}
		
		
	
}
