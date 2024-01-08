package net.kdigital.skyscrapper.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.kdigital.skyscrapper.domain.Member;
import net.kdigital.skyscrapper.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	MemberMapper mapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	// 회원가입 + 암호화
	public int insertMember(Member member) {
		System.out.println(member);
		String encodedPassword = passwordEncoder.encode(member.getMember_pw());
		member.setMember_pw(encodedPassword);  // 암호화된 비번을 세팅

		int result = mapper.insertMember(member);
		return result;
	}

	// 회원정보 수정을 위해 데이터 조회 ( 비번이 암호화 되지 않은 상태로 넘어옴)
	public Member selectMember(String member_id, String member_pw) {
		Map<String, String> map = new HashMap<>();
		map.put("member_id", member_id);
		
		Member m = mapper.selectMember(map);
		
		if(member_pw != null) {
			boolean result = passwordEncoder.matches(member_pw, m.getPassword()); 
			
			if(result) return m;  // 원본비번과 암호화된 비번이 같은 경우
		}
		
		return null; // 원본비번과 암호화된 비번이 다른 경우 
	}

	// 회원정보 수정처리
	public int updateMember(Member member) {
		String encodedPassword = passwordEncoder.encode(member.getMember_pw());
		member.setMember_pw(encodedPassword);  // 암호화된 비번을 세팅

		int result = mapper.updateMember(member);
		return result;		
	}
	
	public Member idCheck(String member_id) {
		Map<String, String> map = new HashMap<>();

		map.put("member_id", member_id);

		Member member = mapper.selectMember(map);

		return member;
	}
	
}







