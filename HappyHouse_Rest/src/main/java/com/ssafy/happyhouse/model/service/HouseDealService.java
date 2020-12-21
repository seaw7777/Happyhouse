package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseDealDto;

public interface HouseDealService {

	List<HouseDealDto> getHouseDealInfo(String dong) throws Exception;
	List<HouseDealDto> getJuteakDealInfo(String dong) throws Exception;
	List<HouseDealDto> getAptNameDealInfo(String aptName) throws Exception;
	List<HouseDealDto> getjuNameDealInfo(String juName) throws Exception;
	
}
