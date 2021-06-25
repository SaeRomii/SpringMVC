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
<title>PositionList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
<link rel="preconnect" href="https://fonts.gstatic.com">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(function() 
	{
		$(".updateBtn").click(function() 
		{
		   	$(location).attr("href", "positionupdateform.action?positionId=" + $(this).val());         
		});
	
			
		$(".deleteBtn").click(function()
		{
			// 테스트
			//alert("삭제 버튼 클릭");
				
			if (confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href", "positiondelete.action?positionId=" + $(this).val());     
			}
		});
	});

</script>

</head>
<body>

<!---------------------------------------------------
  #51. PositionList.jsp
  - 직위 리스트 출력 페이지.
  - 관리자가 접근하는 직위 데이터 출력 페이지
----------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
	
		<h1>[ 직위 관리(관리자 전용) ]</h1>
		<hr>
		
		<div>
			<form action="">
				<input type="button" value="직위 추가" class="btn"
				 onclick="location.href='positioninsertform.action'">
			</form>
		</div>
		
		<br><br>
		
		<!-- POSITIONID POSITIONNAME MINBASICPAY -->
		
		<table id="positions" class="table">
			<tr>
				<!-- 항목 5EA -->
				<th>번호</th>
				<th>직위명</th>
				<th>기본급</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<!-- <tr>
				<td>1</td>
				<td>사원</td>
				<th>1000000</th>
				<td>
					<button type="button" class="btn">수정</button>
				</td>
				<td>
					<button type="button" class="btn">삭제</button>
				</td>
			</tr> -->
			
			<c:forEach var="position" items="${positionList}">
				<tr>
					<td>${position.positionId }</td>
					<td>${position.positionName }</td>
					<td>
						<fmt:formatNumber value="${position.minBasicPay }" groupingUsed="true"></fmt:formatNumber>
					</td>
					<td><button type="button" class="btn updateBtn" value="${position.positionId}">수정</button></td>
					<td>
					<button type="button" class="btn deleteBtn" value="${position.positionId }" 
					${position.delCheck==0 ? "" : "disabled=\"disabled\"" } >삭제</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- 회사 소개 및 어플리케이션 소개 영역 -->
	<div id="footer">
	</div>
	
</div>

</body>
</html>