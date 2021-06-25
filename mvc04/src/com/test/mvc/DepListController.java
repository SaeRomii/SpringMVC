/*======================================
   #63. DepListController.java
   - 사용자 정의 컨트롤러 클래스
======================================*/

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자의 정의 컨트롤러 클래스를 구현한다.

public class DepListController implements Controller		// get방식이든 post방식이든 Controller가 다 받음
{
	private IDepartmentDAO dao;

	public void setDao(IDepartmentDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 여기서 handleRequest는 우리 그 get, post 둘다 받는거 만들때에서 둘다 받는 기능 하는애라고 생각하면됨
		// 액션 코드 
		
		ModelAndView mav = new ModelAndView();
		
		// 세션 처리에 따른 추가 구성 → 로그인 여부 확인 → 관리자 확인할 필요 없음 ------------------------------------------------
		// 로그인 해야지 emplist.action 접근할 수 있게 해주는 부분
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name")==null)		//-- 로그인을 하지 못한 상황
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
		}
		
		// ------------------------------------------------ 세션 처리에 따른 추가 구성 → 로그인 여부 확인 → 관리자 확인할 필요 없음
		
		ArrayList<Department> departmentList = new ArrayList<Department>();
		
		try
		{
			departmentList = dao.list();			// list가 가지고 온 모든 내용 이 뷰 페이지에서 다 쓰지 않음
												// 그래서 필요한 것만 가지고 올 dao.list를 따로 구성해야 바람직함. (그 값만 전해질 수 있게)
			mav.addObject("departmentList", departmentList);
			
			mav.setViewName("/WEB-INF/views/DepList.jsp");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;

	}
	
}
