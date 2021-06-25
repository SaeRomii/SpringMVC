/*================================================
   #34. RegionListController.java
   - 사용자 정의 컨트롤러 클래스
   - 리스트 페이지 요청에 대한 액션 처리
   - DAO 객체에 대한 의존성 주입(DI)을 위한 준비
     → 인터페이스 형태의 자료형을 속성으로 구성
     → setter 메소드 구성    
=================================================*/

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자의 정의 컨트롤러 클래스를 구현한다.

public class RegionListController implements Controller		// get방식이든 post방식이든 Controller가 다 받음
{
	// DAO 인터페이스 자료형 멤버 구성
	private IRegionDAO dao;

	public void setDao(IRegionDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 여기서 handleRequest는 우리 그 get, post 둘다 받는거 만들때에서 둘다 받는 기능 하는애라고 생각하면됨
		// 액션 코드 
		
		ModelAndView mav = new ModelAndView();
		
		// 세션 처리과정 추가 --------------------------------------------
		HttpSession session = request.getSession();
		
		if (session.getAttribute("name") == null)		//--로그인이 되어있지 않은 상황
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
		}
		else if (session.getAttribute("admin") == null)	//--관리자 아님. 일반사원으로 로그인된 상황
		{
			// 관리자로 재로그인할 수 있도록 강제 로그아웃
			mav.setViewName("redirect:logout.action");
			return mav;
		}
		
		ArrayList<Region> regionList = new ArrayList<Region>();
		
		try
		{
			regionList = dao.list();
			
			mav.addObject("regionList", regionList);
			mav.setViewName("/WEB-INF/views/RegionList.jsp");
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		return mav;

	}
	
}
