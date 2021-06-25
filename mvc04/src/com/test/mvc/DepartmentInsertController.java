/*======================================
   #46. DepartmentInsertController.java
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

public class DepartmentInsertController implements Controller		// get방식이든 post방식이든 Controller가 다 받음
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
        
        // 데이터 수신
        String departmentName = request.getParameter("departmentName");
        int count = 0;
        
        try
		{
        	count = dao.count(departmentName);
        	
        	if (count==0)
			{
				Department department = new Department();
				
				department.setDepartmentName(departmentName);
				
				dao.add(department);
				
				mav.setViewName("redirect:departmentlist.action");
			}
        	else 
        	{
        		mav.addObject("count", count);
        		
        		mav.setViewName("WEB-INF/views/DepartmentInsertForm.jsp");
			}
        	
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;

	}
	
}
