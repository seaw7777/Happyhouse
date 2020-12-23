package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MyPageService {

	void delete(String id) throws Exception;
	void modify (MemberDto member) throws Exception;
	List<MemberDto> search(Map<String , String> map) throws Exception; 
}
