package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.JwtService;
import com.ssafy.happyhouse.model.service.MemberService;



@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberController {
		@Autowired
		private JwtService jwtService;

		@Autowired
		private MemberService memberService;
		
		public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

		@PostMapping("/confirm/login")
		public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto, HttpServletResponse response, HttpSession session) {
			System.out.println(memberDto);
			Map<String, Object> resultMap = new HashMap<>();
			System.out.println("진입");
			HttpStatus status = null;
			try {
				MemberDto loginUser = memberService.login(memberDto);
				
				if(loginUser != null) {
//					jwt.io에서 확인
//					로그인 성공했다면 토큰을 생성한다.
					String token = jwtService.create(loginUser);
					logger.trace("로그인 토큰정보 : {}", token);
					
//					토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
					resultMap.put("auth-token", token);
					resultMap.put("user-id", loginUser.getId());
					resultMap.put("user-name", loginUser.getName());
					resultMap.put("user-city", loginUser.getCity());
					resultMap.put("user-state", loginUser.getState());
					resultMap.put("user-gu", loginUser.getGu());
					resultMap.put("user-dong", loginUser.getDong());
					resultMap.put("user-pw", loginUser.getPw());
					resultMap.put("message", "로그인 성공");

					status = HttpStatus.ACCEPTED;
				} else {
					resultMap.put("message", "로그인 실패");
					status = HttpStatus.ACCEPTED;
				}
			} catch (Exception e) {
				logger.error("로그인 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
		@PostMapping(value = "/sign")
		public String SignUp(@RequestBody MemberDto member) {
			System.out.println(member);
			String msg = "success";
			try {
				memberService.SignUP(member);
			} catch (Exception e) {
				System.out.println(e);
				msg = "fail";
			}
			System.out.println(msg);
			return msg;
		}		
}
