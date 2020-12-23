package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.MyPageService;

/**
 * Servlet implementation class MyPageServelt
 */
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/mypage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MyPageService mypageService;

	/**
	 * 멤버 리스트
	 * 
	 * @throws ServletException
	 **/
	@GetMapping(value = {"/list"})
    private List<MemberDto> memberList(@RequestParam Map<String, String>map) throws ServletException{
        try {
            List<MemberDto> list = mypageService.search(map);
            return list;
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }
	/**
	 * 회원정보 수정 업데이트
	 * 
	 * @throws ServletException
	 **/
	@PutMapping("/modify")
	private String modify(@RequestBody MemberDto member) {
		String msg = "fail";
		try {
			System.out.println(member);
			mypageService.modify(member);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정 오류 : " + e);
		}
		return msg;
	}
	//탈퇴
	@DeleteMapping("/delete/{getUserId}")
	public void delete(@PathVariable("getUserId") String id) {
		System.out.println("진입");
		try {
			mypageService.delete(id); // 탈퇴하고
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
