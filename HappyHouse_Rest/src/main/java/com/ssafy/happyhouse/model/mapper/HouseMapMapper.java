package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapMapper {
	List<SidoGugunCodeDto> getCityList();
	List<SidoGugunCodeDto> getGuInSido(String city);
	List<HouseInfoDto> getHouseInfo(String gu);
	List<HouseInfoDto> getAptInfo(String dong);
	List<HouseInfoDto> getJuteakInfo(String dong);
}
