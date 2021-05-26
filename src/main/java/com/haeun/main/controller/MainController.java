package com.haeun.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
// main페이지 URL에서 /을 입력했고 MAIN페이지를 돌려줌.
	/**
	 * 기능 - 게시판의 main화면 회원가입, 로그인 두개로 페이지를 이동할 수 있게 한다.
	 * 		 로그인을 할때 session을 부여해서 회원들에게는 회원정보 목록을 볼 수 있게 하고, 로그아웃도 할 수 있게 한다.
	 * Controller - String 
	 * URL이름 - /
	 * 화면이름 - main.html
	 * Method(접근제어자|반환타입|메소드이름|(매개변수)) 
	 * 	- main
	 * Parameter(ID/PW , 없으면 N/A) 
	 * 	- N/A 
	 * return type(void, String, MemberVO) 
	 * 	- String 
	 * 쿼리(DB 작업 insert, select, update, delete) 
	 * 	- 없음.
	 * 
	 */
	@RequestMapping("/")
	public String main() {
		return "main";
	}
}