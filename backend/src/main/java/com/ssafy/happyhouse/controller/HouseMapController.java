package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/map")
@Api(value="SSAFY")
public class HouseMapController {
   @Autowired
   private HouseMapService houseMapService;
   
   @GetMapping(value = "/city")
   private List<SidoGugunCodeDto> city() {
	   return houseMapService.getCityList();
   }
   
   @GetMapping(value = "/gu/{city}")
   private List<SidoGugunCodeDto> gu(@PathVariable String city) {
	   return houseMapService.getGuInSido(city);
   }
   
   @GetMapping(value = "/dong/{gu}")
   private List<HouseInfoDto> dong(@PathVariable String gu) {      
	   return houseMapService.getHouseInfo(gu);
   }
   
   @GetMapping(value = "/apt/{dong}")
   private List<HouseInfoDto> apt(@PathVariable String dong) {
	   return houseMapService.getAptInfo(dong);
   }
   
   @GetMapping(value = "/ju/{dong}")
   private List<HouseInfoDto> juteak(@PathVariable String dong) {
	   return houseMapService.getJuteakInfo(dong);
   }
   
   @GetMapping(value = "all/{dong}")
   private List<HouseInfoDto> all(@PathVariable String dong) {
	   List<HouseInfoDto> list = houseMapService.getAptInfo(dong);
	   list.addAll(houseMapService.getJuteakInfo(dong));
	   return list;
   }
}