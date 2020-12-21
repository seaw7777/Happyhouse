package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.StoreDto;
import com.ssafy.happyhouse.model.service.StoreService;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping(value = "/store")
public class StoreController {
	
	@Autowired
	private StoreService service;
	
	@GetMapping(value = "/list/{dong}")
	public List<StoreDto> getStoreList(@PathVariable String dong) {
		if("동".equals(dong))
			dong = null;
		try {
			List<StoreDto> list = service.getStoreList(dong);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
