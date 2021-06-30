/*==============================
   StudentController.java
   - 컨트롤러
==============================*/

package com.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController
{
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/studentlist.action", method = RequestMethod.GET)
	public String studentList(ModelMap model)
	{
		String result = "";
		
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		
		model.addAttribute("count", dao.count());
		model.addAttribute("list", dao.list());
		
		result = "WEB-INF/views/StudentList.jsp";
		
		return result;
	}
	
	@RequestMapping(value = "/studentinsertform.action", method=RequestMethod.GET)
	public String studentInsertForm()
	{
		String result = null;
		
		result = "WEB-INF/view/St  udentInsertForm.jsp";
		
		return result;
	}
	
	@RequestMapping(value = "/studentinsert.action", method=RequestMethod.POST)
	public String studentInsert(StudentDTO student) 
	{
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		
		dao.add(student);
		
		//-- 별도의 뷰 페이지 만들지 않고 주소 재지정함
		return "redirect:studentlist.action";

	}
	
	
	@RequestMapping(value = "/gradelist.action", method=RequestMethod.GET)
	public String gradeList(ModelMap model) 
	{
		String result = null;
		
		IGradeDAO dao = sqlSession.getMapper(IGradeDAO.class);
		
		model.addAttribute("count", dao.count());
		model.addAttribute("list", dao.list());
		
		result = "WEB-INF/view/GradeList.jsp";
		
		return result;

	}
	
	@RequestMapping(value = "/gradeinsertform.action", method=RequestMethod.GET)
	public String gradeInsertForm()
	{
		String result = null;
		
		result = "WEB-INF/view/GradeInsertForm.jsp";
		
		return result;
	}
	
	@RequestMapping(value = "/gradeinsert.action", method=RequestMethod.POST)
	public String gradeInsert(GradeDTO grade) 
	{
		IGradeDAO dao = sqlSession.getMapper(IGradeDAO.class);
		
		dao.add(grade);
		
		//-- 별도의 뷰 페이지 만들지 않고 주소 재지정함
		return "redirect:gradelist.action";
	}
}
