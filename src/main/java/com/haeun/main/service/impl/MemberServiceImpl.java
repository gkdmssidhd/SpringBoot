package com.haeun.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haeun.main.dao.MemberDAO;
import com.haeun.main.service.MemberService;
import com.haeun.main.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	// Dao와 연결
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	// joinProc 회원가입되는 데이터를 DAO에 보낸다.
	@Override
	public void joinProc(MemberVO memberVo) {
		
		// 여기서 DAO랑 또 연결을 해줘야 됌.
		memberDAO.joinProc(memberVo);		
	}
	
	// loginProc은 사용자가 입력한 정보가 DB에 있는지 확인하고 돌아
	@Override
	public MemberVO loginProc(MemberVO memberVo) {

		// DAO에서 받아온 것 다시 리턴
		return memberDAO.loginProc(memberVo);
	}
	
	// 리스트만 받아오면 된다. 리턴은 memberList에다가 디비에서 넘어오는애 담기
	@Override
	public List<MemberVO> memberList() {
		// 회원정보를 조회할 DAO 메소드 연결
		List<MemberVO> memberList = memberDAO.memberList();
		// 조회된 리스트를 리턴
		return memberList;
	}
	// 회원상세보기
	@Override
	public MemberVO memberDetail(String memid) {
		return memberDAO.memberDetail(memid);
	}
	// 회원수정
	@Override
	public int memberModify(MemberVO member) {
		return memberDAO.memberUpdate(member);
	}

}
