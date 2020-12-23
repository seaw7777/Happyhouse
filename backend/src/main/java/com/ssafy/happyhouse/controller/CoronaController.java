package com.ssafy.happyhouse.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.service.CoronaService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/corona")
public class CoronaController {
	
	@Autowired
	private CoronaService service;

	@GetMapping(value = "/clinic")
	public List<ClinicDto> getClinicList(@RequestParam Map<String, String> map) {
		List<ClinicDto> list = new LinkedList<ClinicDto>();
		try {
			list = service.getClinicList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(map);
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		return list;
	}
	
	@RequestMapping(value = "/hospital")
	public List<HospitalDto> getHospitalList(@RequestParam Map<String, String> map) {
		List<HospitalDto> list = new LinkedList<HospitalDto>();
		try {
			list = service.getHospitalList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
