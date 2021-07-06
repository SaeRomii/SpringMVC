<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	String name = request.getParameter("name");
	String upload = request.getParameter("upload");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test2.jsp</title>
</head>
<body>

이름 : <%=name %><br>
파일 : <%=upload %><br>
<!-- 이번에는 결과값으로 null만 출력 -->
<!-- 즉, 바이너리 값을 getParameter 를 통해서 받을 수 없다는 의미이다. -->

</body>
</html>