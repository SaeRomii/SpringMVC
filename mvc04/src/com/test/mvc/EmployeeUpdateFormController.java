/*===================================================
   #24. EmployeeUpdateFormController.java
	    (employeeupdateform.action)
   - 사용자 정의 컨트롤러 클래스
   - 직원 데이터 수정 폼 요청에 대한 액션 처리
   - DAO 객체에 대한 의존성 주입(DI)을 위한 준비
     → 인터페이스 형태의 자료형을 속성으로 구성
     → setter 메소드 구성   
===================================================*/

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자의 정의 컨트롤러 클래스를 구현한다.

public class EmployeeUpdateFormController implements Controller		// get방식이든 post방식이든 Controller가 다 받음
{
	// check~~!!!
	// EmployeeInsertFormController 구성과는 다른 방식으로 처리
	// 원래 DAO 한 군데에 때려 넣는거 아닌데 Insert할 때 우리가 편하려고 그렇게함
	// 원래 이렇게 구성해야함 ↓
	// 이렇게 하면 디스패처에서 스프링 컨테이너 안에서 4개 객체(employeeDAO, regionDAO 등등)
	// 만들어서 속성값에 각각 주입해달라는 처리 하게됨
	
	private IEmployeeDAO employeeDAO;
	private IRegionDAO regionDAO;
	private IDepartmentDAO departmentDAO;
	private IPositionDAO positionDAO;

	public void setEmployeeDAO(IEmployeeDAO employeeDAO) 
	{
		this.employeeDAO = employeeDAO;
	}

	public void setRegionDAO(IRegionDAO regionDAO) 
	{
		this.regionDAO = regionDAO;
	}

	public void setDepartmentDAO(IDepartmentDAO departmentDAO) 
	{
		this.departmentDAO = departmentDAO;
	}

	public void setPositionDAO(IPositionDAO positionDAO) 
	{
		this.positionDAO = positionDAO;
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
		
		ArrayList<Region> regionList = new ArrayList<Region>();
		ArrayList<Department> departmentList = new ArrayList<Department>();
		ArrayList<Position> positionList = new ArrayList<Position>();
		
		try 
		{
			// 데이터 수신 (→ EmployeeList.jsp 로부터 employeeId 수신)
			String employeeId = request.getParameter("employeeId");
			
			Employee employee = new Employee();
			
			employee = employeeDAO.searchId(employeeId);
			
			//System.out.println(employee.getEmployeeId());
			
			regionList = regionDAO.list();
			departmentList = departmentDAO.list();
			positionList = positionDAO.list();
			
			mav.addObject("employee", employee);
			mav.addObject("regionList", regionList);
			mav.addObject("departmentList", departmentList);
			mav.addObject("positionList", positionList);
			
			mav.setViewName("/WEB-INF/views/EmployeeUpdateForm.jsp");
			
		} catch (Exception e) 
		{
			System.out.println(e.toString());
		}
		
		
		return mav;

	}
	
}
