/*=====================
   Sample.java
   - 컨트롤러 객체
=====================*/

package com.test.mybatis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Sample
{
	@RequestMapping(value="/hello.action", method=RequestMethod.GET)	// GET 방식 요청에 의해서
	public String hello(Model model)
	{
		model.addAttribute("message", "Hello Spring annotation + MVC + mybatis Framework");
		
		return "/WEB-INF/views/Hello.jsp";
	}
}
