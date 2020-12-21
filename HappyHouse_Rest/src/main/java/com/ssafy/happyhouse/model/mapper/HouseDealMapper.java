package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.HouseDealDto;

public interface HouseDealMapper {

	List<HouseDealDto> getHouseDealInfo(String dong) throws Exception;
	List<HouseDealDto> getJuteakDealInfo(String dong) throws Exception;
	List<HouseDealDto> getAptNameDealInfo(String aptName) throws Exception;
	List<HouseDealDto> getjuNameDealInfo(String juName) throws Exception;

}
