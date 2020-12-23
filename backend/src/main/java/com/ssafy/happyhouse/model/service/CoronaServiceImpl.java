package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.mapper.CoronaMapper;

@Service
public class CoronaServiceImpl implements CoronaService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ClinicDto> getClinicList(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(CoronaMapper.class).getClinicList(map);
	}

	@Override
	public List<HospitalDto> getHospitalList(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(CoronaMapper.class).getHospitalList(map);
	}

}
