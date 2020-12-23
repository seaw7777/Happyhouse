package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getCityList() {
		return sqlSession.getMapper(HouseMapMapper.class).getCityList();
	}

	@Override
	public List<SidoGugunCodeDto> getGuInSido(String city) {
		return sqlSession.getMapper(HouseMapMapper.class).getGuInSido(city);
	}

	@Override
	public List<HouseInfoDto> getHouseInfo(String gu) {
		return sqlSession.getMapper(HouseMapMapper.class).getHouseInfo(gu);
	}

	@Override
	public List<HouseInfoDto> getAptInfo(String dong) {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInfo(dong);
	}

	@Override
	public List<HouseInfoDto> getJuteakInfo(String dong) {
		return sqlSession.getMapper(HouseMapMapper.class).getJuteakInfo(dong);
	}

}
