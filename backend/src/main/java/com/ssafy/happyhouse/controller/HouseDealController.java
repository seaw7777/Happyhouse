package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.service.HouseDealService;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping(value = "/apt")
public class HouseDealController {
	
	@Autowired
	private HouseDealService service;
	
	@GetMapping(value = "/aptDetail/{dong}")
	public List<HouseDealDto> getHouseDealInfo(@PathVariable("dong") String dong) {
		try {
			List<HouseDealDto> list = service.getHouseDealInfo(dong);
			System.out.println("aaaaa");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	@GetMapping(value = "/juDetail/{dong}")
	public List<HouseDealDto> getJuteakDealInfo(@PathVariable("dong") String dong) {
		try {
			List<HouseDealDto> list = service.getJuteakDealInfo(dong);
			System.out.println(list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping(value = "/aptName/{aptName}")
	public List<HouseDealDto> getAptNameDealInfo(@PathVariable String aptName) {
		try {
			List<HouseDealDto> list = service.getAptNameDealInfo("%" + aptName + "%");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping(value = "/juName/{juName}")
	public List<HouseDealDto> getjuNameDealInfo(@PathVariable String juName) {
		try {
			List<HouseDealDto> list = service.getjuNameDealInfo("%" + juName + "%");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
