package net.kdigital.skyscrapper.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.kdigital.skyscrapper.domain.Member;

@Mapper
public interface MemberMapper {
	public int insertMember(Member member);
	public Member selectMember(Map<String, String> map);
	public int updateMember(Member member);
}

