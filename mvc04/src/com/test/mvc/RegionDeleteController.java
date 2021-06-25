/*======================================
   #41. RegionDeleteController.java
   - 사용자 정의 컨트롤러 클래스
======================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자의 정의 컨트롤러 클래스를 구현한다.

public class RegionDeleteController implements Controller		// get방식이든 post방식이든 Controller가 다 받음
{
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
		
		// 세션 처리과정 추가 -------------------------------------------------------------------------------
		// 주소로 employeelist나 직원수정 삭제 페이지 못들어가게 해줘야함
		HttpSession session =  request.getSession();
		
		if (session.getAttribute("name")==null)		//-- 로그인이 되어있지 않은 상황
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
		}
		else if (session.getAttribute("admin")==null)	//-- 로그인은 되었지만 관리자가 아닌 상황 즉, 일반 사원으로 로그인 
		{
			mav.setViewName("redirect:logout.action");
			return mav;
		}
		// ------------------------------------------------------------------------------- 세션 처리과정 추가
		
		// 데이터 수신
		String regionId = request.getParameter("regionId");
		
		try
		{
			dao.remove(regionId);
			
			mav.setViewName("redirect:regionlist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;

	}
	
}
