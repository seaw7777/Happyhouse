package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.happyhouse.model.HospitalDto;

public interface CoronaMapper {

	List<ClinicDto> getClinicList(Map<String, String> map) throws Exception;
	List<HospitalDto> getHospitalList(Map<String, String> map) throws Exception;

}
