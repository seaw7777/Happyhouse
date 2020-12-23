package com.ssafy.happyhouse.controller;

//네이버 검색 API 예제 - blog 검색
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.service.NewsService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/news")
@Api(value = "SSAFY")
public class NewsController {
	
	@Autowired
	NewsService service;
	
	@GetMapping(value = "/list")
	public List<NewsDto> Newslist() {
		return service.Newslist();
	}
}