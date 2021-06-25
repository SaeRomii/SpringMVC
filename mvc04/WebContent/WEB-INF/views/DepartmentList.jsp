<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DepartmentList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
<link rel="preconnect" href="https://fonts.gstatic.com">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(function()
	{
		$(".updateBtn").click(function()
		{
			$(location).attr("href", "departmentupdateform.action?departmentId=" + $(this).val());
		});
		
		$(".deleteBtn").click(function()
		{
			//테스트
			// alert("삭제 버튼 클릭");
			if(confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href", "departmentdelete.action?departmentId=" + $(this).val());
			}	
		});
	});

</script>

</head>
<body>

<!---------------------------------------------------
  #42. DepartmentList.jsp
  - 부서 리스트 출력 페이지.
  - 관리자가 접근하는 부서 데이터 출력 페이지
----------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
	
		<h1>[ 부서 관리(관리자 전용) ]</h1>
		<hr>
		
		<div>
			<form action="">
				<input type="button" value="부서 추가" class="btn"
				 onclick="location.href='departmentinsertform.action'">
			</form>
		</div>
		
		<br><br>
		
		<!-- DEPARTMENTID DEPARTMENTNAME -->
		
		<table id="departments" class="table">
			<tr>
				<!-- 항목 4EA -->
				<th>번호</th>
				<th>부서명</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<!-- <tr>
				<td>1</td>
				<td>개발부</td>
				<td>
					<button type="button" class="btn">수정</button>
				</td>
				<td>
					<button type="button" class="btn">삭제</button>
				</td>
			</tr> -->
			
			<c:forEach var="department" items="${departmentList}">
				<tr>
					<td>${department.departmentId }</td>
					<td>${department.departmentName }</td>
					<td><button type="button" class="btn updateBtn" value="${department.departmentId}">수정</button></td>
					<td>
						<button type="button" 
						${department.delCheck==0 ? "class='btn deleteBtn'" : "class='diabled'" }
						${department.delCheck==0 ? "" : "disabled='disabled'"} 
						value="${department.departmentId }">삭제</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

</body>
</html>