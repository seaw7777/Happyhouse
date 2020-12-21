package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MyPageMapper;
@Service
public class MyPageServiceImpl implements MyPageService {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void delete(String id) throws Exception {
		System.out.println("서비스 진입 : " + id);
		sqlSession.getMapper(MyPageMapper.class).delete(id);
	}

	@Override
	public void modify(MemberDto member) throws Exception {
		sqlSession.getMapper(MyPageMapper.class).update(member);
	}

	@Override
	public List<MemberDto> search(Map<String , String> map) throws Exception{
		
		map.put("word", map.get("word") == null ? "" : map.get("word"));
		if("state".equals(map.get("key")))
			map.put("word", "관리자".equals(map.get("word")) ? "100" : "1");
		System.out.println(map.get("key"));
		System.out.println(map.get("word"));
		return sqlSession.getMapper(MyPageMapper.class).search(map);
	}

}
