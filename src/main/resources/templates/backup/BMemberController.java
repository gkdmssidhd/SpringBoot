package com.haeun.main.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haeun.main.service.MemberService;
import com.haeun.main.vo.MemberVO;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// Service와 연결
	@Resource(name="memberService")
	private MemberService memberService;	
	/**
	 * 기능 - Join 회원가입 화면
	 * 		 폼안에서 아이디, 비번, 이메일을 입력만 하는애! 
	 * 		 submit을 joinProc로 보내서 joinProc가 실제 디비까지 보낸다.
	 * 		 joinProc가 URL에서 입력되면 나는 joinOk로 연결시켜서 회원가입 완료되었다고 띄울거임. 
	 * Controller - String 
	 * URL이름 - join
	 * 화면이름 - join.html
	 * Method(접근제어자|반환타입|메소드이름|(매개변수)) 
	 * 	- public|Stirng|join();
	 * Parameter(매개변수 ID/PW , 없으면 N/A) 
	 * 	- N/A 조인화면만에서는 아직 파라미터 없음. 근데 조인ok에서는 membervo가 있겠지
	 * return type(void, String, MemberVO) 
	 * 	- String 
	 * 쿼리(DB 작업 insert, select, update, delete) 
	 * 	- 얘는 일단 입력만하는 곳
	 */
	@RequestMapping("/member/join")
	public String join(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		return "join";
	}
	
	/**
	 * 기능 - Join 회원가입 오케이화면 Proc여기서 실제로 디비까지 다녀와야 하는데(DB까지 가야 회원가입이 완료 되는 것)
	 * 		 여기서 VO가져다 쓰고, Service 연결하고 Dao 연결 mybatis 다녀오고 return으로는 joinOk페이지를!
	 * Controller - String 
	 * URL이름 - join
	 * 화면이름 - join.html
	 * Method(접근제어자|반환타입|메소드이름|(매개변수)) 
	 * 	- public|MemberVO|joinProc();
	 * Parameter(매개변수 ID/PW , 없으면 N/A) 
	 * 	- membervo
	 * return type(void, String, MemberVO) 
	 * 	- joinProce는 반환타입 없음 void 
	 * 쿼리(DB 작업 insert, select, update, delete) 
	 * 	- 아이디, 비번, 메일 값 넣어야 함.
	 * 	- insert into member(memid, mempw, memmail) service랑 dao랑 mybatis 가즈아
	 */
	@RequestMapping("/member/joinProc")
	public String joinProc(MemberVO memberVo, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 서비스로 값을 넘겨보자
		memberService.joinProc(memberVo);
						
		// 다 잘 되어지면 joinOk를 리턴
		return "joinOk";
	}
	
	/**
	 * 기능 - login! 아이디와 비번을 일단 입력만! 하고 loginProc에 보낼거임 
	 * Controller - String 
	 * URL이름 - login
	 * 화면이름 - login.html
	 * Method(접근제어자|반환타입|메소드이름|(매개변수)) 
	 * 	- public String login();
	 * Parameter(매개변수 ID/PW , 없으면 N/A) 
	 * 	- N/A
	 * return type(void, String, MemberVO) 
	 * 	- String
	 * 쿼리(DB 작업 insert, select, update, delete)
	 * 	- 로그인입력만 하는 페이지라서 아직은 없음.
	 */
	@RequestMapping("/member/login")
	public String login(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		return "login";
	}
	/**
	 * 기능 - loginProc 
	 * 		 login에서 받은 애들이 회원에 있는 애들인지 없는애들인지(아닐때는 loginError) 
	 * 		 Service로 값들을 보내야 한다.
	 * 		 login될때 session도 부여 할거임! session.setAttribute("설정한 세션아이디", 세션에 넣을 값);   
	 * 		 session.getAttribute("user_id"); > 세션값 가져올때
	 * 		 session.invalidate(); > 세션값 삭제할때 로그아웃
	 * Controller - String 
	 * URL이름 - loginProc
	 * 화면이름 - loginOk.html
	 * Method(접근제어자|반환타입|메소드이름|(매개변수)) 
	 * 	- public MemberVO loginProc(MemberVO membervo);
	 * 	- 반환타입이 MemberVO
	 * Parameter(매개변수 ID/PW , 없으면 N/A) 
	 * 	- membervo
	 * return type(void, String, MemberVO) 
	 * 	- 돌려줄때 Model에 담아서 돌려준다. model.addAttribute("이름", 값);
	 * 쿼리(DB 작업 insert, select, update, delete)
	 * SELECT memid, mempw, memmail from member where memid, mempw
	 */
	@PostMapping("/member/loginProc")
	public String loginProc(MemberVO memberVo, HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		
		// Service로 보내기
		MemberVO member = memberService.loginProc(memberVo);
		
		// login에서 입력한애들이 DB에 실제로 있는애들인지
		String returnUrl = "";
		
			if(member == null) {
				logger.info("존재하지 않는 회원입니다.");
				returnUrl = "loginError";
			} else {
				// 성공적으로 로그인이 될때 session 부여
				session.setAttribute("memid", member);
				
				// Model에 담아서 반환
				model.addAttribute("memidVo", member);
				
				returnUrl = "loginOk";
			}
		
		return returnUrl; 
	}
	/**
	 * 기능 - logout 
	 * 		 logout되게
	 * 		 session.getAttribute("user_id"); > 세션값 가져올때
	 * 		 session.invalidate(); > 세션값 삭제할때 로그아웃
	 * Controller - String 
	 * URL이름 - logout
	 * 화면이름 - logout.html
	 * Method(접근제어자|반환타입|메소드이름|(매개변수)) 
	 * 	- public String logout();
	 * Parameter(매개변수 ID/PW , 없으면 N/A) 
	 * 	- memid?
	 * return type(void, String, MemberVO) 
	 * 	- 
	 * 쿼리(DB 작업 insert, select, update, delete)
	 */
	// logout
	@RequestMapping("/member/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		logger.info("로그아웃 되었습니다.");
		// 로그아웃시키고 메인으로 리다이렉트
		return "redirect:/";
	}
	/**
	 * 기능 - 리스트 list 가입이 되어있는 회원의 정보를 다 나오게 한다. 
	 * 		 List라는 컬렉션프레임웍 인터페이스사용
	 * 		 List 인터페이스 순서가 있는 데이터의 집합으로 중복을 허용. 저장순서가 유지되는 컬렉션을 구현하는데 사용
	 * 		 DB에서부터 받아와야 됌. Model에 담아서 반환도 해줘야 함.
	 * 		 for문 사용 MemberVO member : memberList를 담기 
	 * 		 for문에다가 syso는 toString();
	 *		 session.getAttribute("user_id"); > 세션값 가져올때 
	 * Controller - String 
	 * URL이름 - list
	 * 화면이름 - list.html
	 * Method(접근제어자|반환타입|메소드이름|(매개변수)) 
	 * 	- public String list(Model model);
	 * Parameter(매개변수 ID/PW , 없으면 N/A) 
	 * 	- N/A 
	 * return type(void, String, MemberVO) 
	 * 	- List<MemberVO> List의 타입이 MemberVO
	 * 쿼리(DB 작업 insert, select, update, delete)
	 * 	- select memid, mempw, memmail from member
	 */
	
	@RequestMapping("/member/list")
	public String list(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		
		// List인터페이스 <MemberVO>타입의 memberList에 서버->Dao->DB 통해서 리스트들 가져와서 담는다.
		List<MemberVO> memberList = memberService.memberList();
		
		model.addAttribute("memberList", memberList);
		
		return "list";
	}
	
	/**
	 * 기능 - 상세보기
	 * 		 리스트에서 아이디를 누르면 상세보기로 넘어옴. 클릭한을한 아이디, 비번, 이메일만 보여야 함.
	 * 		 사용자가 어떤 아이디인지를 눌렀는지의 구별은 세션으로 확인. 클릭한 아이디를 구분하기 위해서는 session으로 구분! 
	 * 		 밑에 수정버튼 눌러서 수정으로 넘어갈 수 있게함.
	 * Controller -  String
	 * URL이름 - detail
	 * 화면이름 - detail
	 * Method(접근제어자|반환타입|메소드이름|(매개변수)) 
	 * 	- public String detail(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session)
	 * Parameter(매개변수 ID/PW , 없으면 N/A) 
	 * 	- MemberVO
	 * return type(void, String, MemberVO) 
	 * 	- @ModelAttribute("memid")MemberVO memid
	 * 	- @ModelAttribute에서("memid")받을거고 MemberVO타입에 memid로
	 * 쿼리(DB 작업 insert, select, update, delete)
	 * 	- SELECT MEMID FROM MEMBER WHERE = ? ;
	 *  - SELECT * FROM MEMBER WHERE MEMID = #{memid}
	 * 	- 물음표로 했던거 같은데
	 */
	@RequestMapping("/member/detail")
	// list에서 누른 memid를 디비에서 가져와야함 service->dao->mybatis
	// 매개변수 목록은 메소드 호출시에 전달되는 인수의 값을 저장할 변수들
	public String detail(@ModelAttribute("memid")MemberVO memid, Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		logger.info(memid.getMemid());
		
		MemberVO member = memberService.memberDetail(memid.getMemid());
		
		model.addAttribute("member", member);
		
		return "detail";
	}
	
	/**
	 * 기능 - 상세보기에서 수정버튼을 누를시 수정화면으로 넘어온다. 여기서 상세보기에 있는 값들중 아이디와 이메일만 수정을하고 완료 버튼을 누르면
	 * 		 상세보기로 다시 넘어간다. 상세보기에서는 수정한 그 값이 보여지게 된다.
	 * Controller - 
	 * URL이름 - modify
	 * 화면이름 - modify
	 * Method(접근제어자|반환타입|메소드이름|(매개변수)) 
	 * 	- public String modify(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session)
	 * Parameter(매개변수 ID/PW , 없으면 N/A) 
	 * 	- MemberVO
	 * return type(void, String, MemberVO) 
	 * 	- MemberVO일듯
	 * 쿼리(DB 작업 insert, select, update, delete)
	 * 	- UPDATE MEMBER SET MEMPW, MEMMAIL WHERE MEMID
	 */
	@RequestMapping("/member/modify")
	public String modify(@ModelAttribute("member")MemberVO memberVO, Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		
		logger.info(memberVO.getMemmail());
		
		// 여기엔 member객체 받아야됌.
		model.addAttribute("member", memberVO);
		
		return "modify";
	}
	
	/* 실제 db에 갔다오는 메소드 */
	@RequestMapping("/member/modifyOk")
	public String modifyOk(@ModelAttribute("member")MemberVO memberVO, RedirectAttributes redirectAttributes, Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		
		logger.info(memberVO.getMemid());
		// Service로 memberModify-> 이 메소드 이름으로 보내기
		memberService.memberModify(memberVO);
		
		// 상세보기에서 수정한것 받아서 리턴
		redirectAttributes.addFlashAttribute("memid", memberVO);
		
		// 리다이렉트로 디테일로 넘어가서도 수정한값으로 바로 보이게
		return "redirect:/member/detail";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
