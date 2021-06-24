/*======================================
   #26. EmployeeDeleteController.java
	    (employeedelete.action)
   - 사용자 정의 컨트롤러 클래스
   - 직원 데이터 삭제 액션 처리 → employeelist.action 다시 요청할 수 있도록 안내
   - DAO 객체에 대한 의존성 주입(DI)을 위한 준비
     → 인터페이스 형태의 자료형을 속성으로 구성
     → setter 메소드 구성   
======================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자의 정의 컨트롤러 클래스를 구현한다.

public class EmployeeDeleteController implements Controller		// get방식이든 post방식이든 Controller가 다 받음
{
	private IEmployeeDAO dao;
	
	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 여기서 handleRequest는 우리 그 get, post 둘다 받는거 만들때에서 둘다 받는 기능 하는애라고 생각하면됨
		// 액션 코드 
		
		ModelAndView mav = new ModelAndView();
		
		// 데이터 수신(→ EmployeeList.jsp로부터 employeeId 수신)
		String employeeId = request.getParameter("employeeId");
		
		try
		{
			dao.remove(employeeId);
			
			// DB상 내용 지울테니 employeelist.action을 다시 요청해라~
			mav.setViewName("redirect:employeelist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;

	}
	
}