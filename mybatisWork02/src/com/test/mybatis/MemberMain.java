/*=====================
   MemberMain.java
   - 컨트롤러
=====================*/

package com.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberMain
{
	// sqlsession 이라는 인터페이스 타입의 속성 구성
	// mybatis 객체 의존성 (자동) 주입~!!!
	@Autowired
	private SqlSession sqlSession;
	// sqlSession 지정해주지 않아도 Auto 이용해서 자동으로 주입
	// mybatis 사용하기 위해서 sqlsession만들어야 하는데 이건 스프링 컨테이너가함
	
	// /memberlist.action 요청 들어오면 ↓memberList(ModelMap model) 일하게
	@RequestMapping(value = "/memberlist.action", method = RequestMethod.GET)
	public String memberList(ModelMap model)
	{
		// IMemberDAO dao = (IMemberDAO)sqlSession.getMapper(); 이렇게 캐스팅해서 가져오면 위험성 있어서 ↓방법으로
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		//IMemberDAO dao = new MemberDAO();
		
		model.addAttribute("count", dao.count());
		model.addAttribute("list", dao.list());
		
		return "WEB-INF/views/MemberList.jsp";
	}
}
