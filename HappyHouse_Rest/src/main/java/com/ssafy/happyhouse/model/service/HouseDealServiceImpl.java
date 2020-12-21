package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<HouseDealDto> getHouseDealInfo(String dong) throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).getHouseDealInfo(dong);
	}

	@Override
	public List<HouseDealDto> getJuteakDealInfo(String dong) throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).getJuteakDealInfo(dong);
	}

	@Override
	public List<HouseDealDto> getAptNameDealInfo(String aptName) throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).getAptNameDealInfo(aptName);
	}

	@Override
	public List<HouseDealDto> getjuNameDealInfo(String juName) throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).getjuNameDealInfo(juName);
	}
	
}
