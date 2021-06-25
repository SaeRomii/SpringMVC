/*===========================================
   HelloController.java
   - 사용자 정의 컨트롤러 클래스
   - 『@Controller』 어노테이션 등록
   - 『@RequestMapping』 어노테이션 등록
===========================================*/

package com.test.mvc;

import org.springframework.stereotype.Controller;		//-- check~!!!
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// ※ Annotation 표기법으로 만든 컨트롤러 개게 지정
@Controller
public class HelloController
{
	// ※ 컨트롤러 요청에 대한 액션 처리를 위한 메소드는 사용자 지정.
	/*  기본틀
	@RequestMapping("URL매핑주소")
	public String 메소드이름(매개변수)
	{
		// ※ 비즈니스 로직 처리(모델 활용)
		//    ....
		
		return "뷰 이름.jsp";
	}
	*/
	
	// hello.action 요청이 들어오면 응답할거야
	@RequestMapping("/hello.action")
	public String hello(Model model)
	{
		// ※ 비즈니스 로직 처리(모델 활용)
		//    ....
		
		// 데이터 전달 명령
		model.addAttribute("hello", "Hello, SpringMVCAnnotation World~!!!");    // hello라는 이름으로 Hello, SpringMVCAnnotation World넘기겠다.
		
		return "/WEB-INF/views/Hello.jsp";
	}
}
