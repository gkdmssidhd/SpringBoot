package com.haeun.main.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.haeun.main.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAO extends AbstractDAO {

	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	/**
	 * 	 여긴 DAO mybatis로 값을 넘겨야 함. insert member 값은 membervo
	 * 	 namespace가 member임 <insert id="insertMember" parameterType="MemberVO">
	 * @return 
	 */
	public void joinProc(MemberVO memberVo) {
		try {
			// namespace(이름 맞춰줘야 함) = member
			// insert id(얘도) = insertMember
			// 쿼리("namespace.id", 넘어갈 값 파라미터!)
			insert("member.insertMember", memberVo);
		} catch(Exception e) {
			logger.info("DB Error >>>>>>>>>");
			e.printStackTrace();
		}
	}
	/**
	 * 로그인값들이 디비로 연결되어짐.
	 * @param membervo
	 */

	public MemberVO loginProc(MemberVO memberVo) {
		
		// MemberVO 가져와야지
		MemberVO member = null;
		
		try {
			// namespace(이름 맞춰줘야 함) = member
			// insert id(얘도) = selectMember
			// 쿼리("namespace.id", 넘어갈 값 파라미터!)
			member = (MemberVO) select("member.selectMember", memberVo); 
		} catch(Exception e) {
			logger.info("DB Error >>>>>>>>>");
			e.printStackTrace();
		}
		
		return member;
	}
	
	//<select id="selectMemberList" parameterType="MemberVO" resultType="MemberVO"> 
	public List<MemberVO> memberList() {
		
		// selectList 로 정보 조회
		@SuppressWarnings("unchecked")
		List<MemberVO> list = (List<MemberVO>) selectList("member.selectMemberList");
		
		// 조회된 리스트를 리턴
		return list;
	}
	// 회원 상세보기 MemverVO 타입으로 반환 메소드 이름 memberDetail(넘기는 파라미터 String타입의 memid)
	public MemberVO memberDetail(String memid) {
		
		return (MemberVO) select("member.selectDetail", memid);
	}
	
	// 회원수정 db의 값을 바꿀 메소드
	// 보통 int로 성공인지 실패인지 디비오류인지 구분함
	public int memberUpdate(MemberVO member) {
		
		return (int) update("member.updateMember", member);
	}
}
