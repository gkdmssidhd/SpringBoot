package com.haeun.main.service;

import java.util.List;

import com.haeun.main.vo.MemberVO;

public interface MemberService {

	public void joinProc(MemberVO memberVo);
	
	// 접근제어자 반환타입 메소드 이름(파라미터)
	public MemberVO loginProc(MemberVO memberVo);

	// 회원정보 목록들을 조회하기 위해 만든 메소드
	public List<MemberVO> memberList();

	// 회원정보 목록을 상세하게 보기 위한 메소드
	public MemberVO memberDetail(String memid);
	
	// 회원정보를 수정하는 메소드
	public int memberModify(MemberVO memberVO);
}
