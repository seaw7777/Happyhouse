package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MyPageMapper {

	void delete(String id);

	void update(MemberDto member) throws Exception;
	
	List<MemberDto> search(Map<String, String> map) throws Exception;

	

	
}
