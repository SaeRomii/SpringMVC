/*========================================
   MemberInsertController.java
   - 사용자 정의 컨트롤러 클래스
   - 회원 데이터 추가 액션 처리 클래스.
   - DAO 객체에 대한 의존성 주입 준비.
     → 인터페이스 형태의 속성 구성
     → setter 메소드 추가.
========================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자의 정의 컨트롤러 클래스를 구현한다.

public class MemberInsertController implements Controller		// get방식이든 post방식이든 Controller가 다 받음
{
	// 인터페이스 자료형을 취하는 속성 구성
	private IMemberDAO dao;

	public void setDao(IMemberDAO dao) 
	{
		this.dao = dao;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 여기서 handleRequest는 우리 그 get, post 둘다 받는거 만들때에서 둘다 받는 기능 하는애라고 생각하면됨
		// 액션 코드 
		ModelAndView mav = new ModelAndView();
		
		request.setCharacterEncoding("UTF-8");
		
		// 이전 페이지로부터 넘어온 데이터 수신
		String name = request.getParameter("name");
		String tel = request.getParameter("telephone");
		
		try 
		{
			// DAO 가져와야함 (add 쿼리문) → 매개변수가 memberDTO 타입
			MemberDTO member = new MemberDTO();
			member.setName(name);
			member.setTelephone(tel);
			
			//dao.add(MemberDTO 타입);
			dao.add(member);
			
		} catch (Exception e) 
		{
			System.out.println(e.toString());
		}
		
		// 내부로 포워딩하는게 아니라 db에 저장했으니 확인하려면 memberlist.action 다시 확인해봐
		// 다시 요청하는 순간 memberlistcontroller가 셀렉트 해온거 뿌려줄거임
		// try 안에서는 db로 처리하게 되는거고, ↓ 이 부분은 redirect로 다시 요청
		// memberlist.action 요청이 들어오면 memberlistcontroller 일할 수 있도록 만들었기 때문에 
		// memberlistcontroller 다시 실행
		// 그래서 ↓ 특정 뷰를 지정하는게 아니라 redirect 구문 써주는거임
		
		//mav.setViewName("memberlist.action 페이지를 다시 요청할 수 있도록 안내 (redirect)");
		mav.setViewName("redirect:memberlist.action");
		// mav.setViewName("/WEB-INF/views/memberlist.jsp"); 는 안됨
		// memberlistcontroller 에서는 이미 로직 처리된거 가지고 있고 memberlist 뷰 페이지로 내보내는거였음
		// count 값, list 값 얻어내려고함, 하지만 이 페이지에서는 수신할 수가 없음
		
		
		return mav;

	}
	
}
