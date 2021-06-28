<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberInsertForm.jsp</title>
<link rel="stylesheet" href="<%=cp%>/css/main.css">
<link rel="stylesheet" href="<%=cp%>/css/jquery-ui.css">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp%>/js/jquery-ui.js"></script>

<script type="text/javascript">

	$(function()
	{
		$("#submitBtn").click(function()
		{
			if( $("#memId").val()=="" || $("#memName").val()=="" || $("#memTel").val()=="" ||
				$("#memEmail").val()=="" )
			{
				$("#err").html("필수 입력 항목이 누락되었습니다.");
				$("#err").css("display", "inline");
				return;		//-- submit 액션 처리 중단
			}
			
			// 폼 submit 액션 처리 수행
			$("#memberForm").submit();
		});
	});
	
</script>
</head>
<body>

<!-- MemberInsertForm.jsp
     - 회원 데이터 입력 페이지 -->

<div>
    <!-- 메뉴 영역 -->
	<div>
		<c:import url="MemberInsert.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		<h1>[ 회원 추가 ]</h1>
		<hr>
		
		<form action="memberinsert.action" method="post" id="memberForm">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" id="memId" name="id" placeholder="아이디">	<!-- name은 DTO 속성값이랑 같아야함 -->
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="text" id="memPw" name="pw" placeholder="비밀번호">
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" id="memName" name="name" placeholder="이름">
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="text" id="memTel" name="tel" placeholder="전화번호">
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" id="memEmail" name="email" placeholder="이메일">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br><br>
						
						<button type="button" class="btn" id="submitBtn">회원 추가</button>
						<button type="button" class="btn" id="listBtn"
						onclick="location.href='memberlist.action'">회원 리스트</button>
						<br><br>
						<span id="err" style="color: red; font-weight: bold; display: none;"></span>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

</body>
</html>