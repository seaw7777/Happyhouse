package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/notice")
@Api(value="SSAFY")
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@GetMapping(value = "/list")
	public List<NoticeDto> getNoticeList(@RequestParam Map<String, String> map) {
		try {
			List<NoticeDto> list = service.getNoticeList(map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PostMapping(value = "/write")
	public String writeNotice(@RequestBody NoticeDto notice) {
		System.out.println(notice);
		try {
			service.writeNotice(notice);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "fail";
	}
	
	@GetMapping(value = "/view/{no}")
	public NoticeDto getNotice(@PathVariable int no) {
		try {
			NoticeDto notice = service.getNotice(no);
			return notice;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping(value = "/modify/{no}")
	public NoticeDto modifyForm(@PathVariable int no) {
		try {
			NoticeDto notice = service.getNotice(no);
			return notice;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PutMapping(value = "/modify")
	public String modifyNotice(@RequestBody NoticeDto notice) {
		System.out.println(notice);
		try {
			service.modifyNotice(notice);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "fail";
	}
	
	@DeleteMapping(value = "/delete/{no}")
	public String deleteNotice(@PathVariable int no) {
		try {
			service.deleteNotice(no);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "fail";
	}
	
}
