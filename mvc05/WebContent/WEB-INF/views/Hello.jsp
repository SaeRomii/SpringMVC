<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Hello.jsp</title>
</head>
<body>

<!-- 뷰(View) 페이지 -->
<!-- → HelloController 로 부터 Model 객체의 정보를 수신하여 -->
<!--    출력하는 처리 -->

<div>
	<h1>수신한 결과 출력</h1>
	<hr>
</div>

<div>
	<h2>${hello }</h2>
	<!-- 컨트롤러에서 넘긴 Hello, SpringMVCAnnotation World~!!! 부분 -->
</div>

</body>
</html>