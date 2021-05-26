package com.haeun.main;

public class Comment {
	/**
	 * 기능 - 
	 * Controller -  
	 * URL이름 - 
	 * 화면이름 - 
	 * Method(접근제어자|반환타입|메소드이름|(매개변수)) 
	 * 	- 
	 * Parameter(매개변수 ID/PW , 없으면 N/A) 
	 * 	- 
	 * return type(void, String, MemberVO) 
	 * 	-
	 * 쿼리(DB 작업 insert, select, update, delete)
	 * 	- 
	 */
	// 스프링 흐름
	// 1. URL 입력
	// 2. 서블릿 > 컨트롤러 탐색 (스프링 부트 자동)
	// 3. 컨트롤러에서 매핑되는 URL의 메소드 수행
	// 4-1. DB 작업이 없는 경우 : return "" 로 바로 페이지 리턴
	// 4-2. DB 작업이 있는 경우 : service를 호출하여 DB 작업수행 (select, insert, update, delete)
	// 5. 결과를 화면으로 전송 model.setAttribute("보낼이름", 보낼객체)
	// 6. 화면에서 기능 처리
	
	// 개발 작업 시 주의사항
	// 1. 절대로 생각하기 전에 소스작업 금지
	// 2. 모르는 내용은 이해 필수 소스작업 금지
	// 3. 모르는 내용 복사붙여넣기 금지
	// 4. F12 개발자도구, 이클립스 콘솔 우선적으로 보기
	
	// 개발 작업 순서
	// 1. 내가 만들 기능 정리 : ex) 수정 기능 구현
	// 2. 내가 만들 파일들에 대한 정의
	// - Controller 이름은? ex) MemberController
	// - URL 이름은? ex) /member/modify
	// - 메소드 이름은? ex) modify()
	//	- 파라미터는? ex) ID/PW , 없으면 N/A로 표기
	//	- 리턴타입은? ex) MemberVO
	// - 쿼리 이름은? 
	//	- 쿼리는 어떤 쿼리? 어떤 테이블을 조회해서 어떤 결과를 가지고 올 것인가, 가져온 데이터로 어떤 작업을 할 것인가
	// - 화면 이름은?
	//	- 화면에서 처리할 기능은? ex) 로그인 성공할 경우, 로그아웃 버튼만 나오게 한다.
	// 3. 시스템 순서는? ex) 메인 > 회원가입 > 로그인 > 로그인(성공) > 목록
}
