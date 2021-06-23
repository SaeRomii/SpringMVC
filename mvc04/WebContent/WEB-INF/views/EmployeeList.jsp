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
<title>EmployeeList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp%>/css/main.css">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
	$(function() 
	{
		$(".updateBtn").click(function() 
		{
			// 테스트
			//alert("수정 버튼 클릭");
			
			// 수정할 때는 이전에 입력된 값 가져와서 수정해야 하는거라서 updateform만 가져와서는 안됨
			// employeeupdateform.action 페이지 요청 → 업데이트(수정)
			// employeeId 전송 → 아이디로 그 직원의 정보들을 객체로 넘겨받을 수 있음
			// 컨트롤러는 EmployeeDAO 주입 받아서 searchId라는 메소드 호출함
			// EL로 value값 전송해줘서 클라이언트 만나게함
			// 어떤 수정 버튼을 눌렀느냐에 따라 각각 다른 employeeId 넘겨줘야함
			$(location).attr("href", "employeeupdateform.action?employeeId=" + $(this).val());
			
		});
		
		$(".deleteBtn").click(function() 
		{
			// 테스트
			//alert("삭제 버튼 클릭");
			
			if (confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?")) 
			{
				$(location).attr("href", "employeedelete.action?employeeId=" + $(this).val());
			}
		});
	});
</script>

</head>
<body>

<!---------------------------------------------------
  #14. EmployeeList.jsp 
  - 직원 리스트 출력 페이지.
  - 관리자가 접근하는 직원 데이터 출력 페이지
    (일반 직원이 접근하는 직원 데이터 출력 페이지는
     EmpList.jsp 로 구성할 예정)
----------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[ 직원 관리 ]</h1>
		<hr>
		
		<div>
			<form action="">
				<input type="button" value="직원 추가" class="btn"
				onclick="location.href='employeeinsertform.action'">	<!-- 직원 추가 할 수 있는 폼 떠야함 employeeinsertform.action요청 -->
			</form>
		</div>
		
		<br><br>
		
		<!-- 
		EMPLOYEEID NAME SSN BIRTHDAY LUNAR LUNARNAME TELEPHONE
		DEPARTMENTID DEPARTMENTNAME POSITIONID POSITIONNAME
		REGIONID REGIONNAME BASICPAY EXTRAPAY PAY GRADE
		 -->
		
		
		<table id="customers" class="table">
			<tr>
				<!-- 항목 15EA -->
				<th>번호</th>
				<th>이름</th>
				<th>주민번호</th>
				<th>생년월일</th>
				<th>양/음력</th>
				<th>전화번호</th>
				<th>지역</th>
				<th>부서</th>
				<th>직위</th>
				<th>기본급</th>
				<th>수당</th>
				<th>급여</th>
				<th>등급</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<!-- 
			<tr>
				<td>1</td>
				<td>박나현</td>
				<td>631212</td>
				<td>1963-12-12</td>
				<td>양력</td>
				<td>010-1111-1111</td>
				<td>서울</td>
				<td>개발부</td>
				<td>사원</td>
				<td>1,500,000</td>
				<td>1,000,000</td>
				<td>2,500,000</td>
				<td>관리자</td>
				<td><button type="button" class="btn">수정</button></td>
				<td><button type="button" class="btn">삭제</button></td>
			</tr>
			 -->
			 
			<c:forEach var="employee" items="${employeeList }">
			<tr>
				<td>${employee.employeeId }</td>
				<td>${employee.name }</td>
				<td>${employee.ssn }-*******</td>
				<td>${employee.birthday }</td>
				<td>${employee.lunarName }</td>
				<td>${employee.telephone }</td>
				<td>${employee.regionName }</td>
				<td>${employee.departmentName }</td>
				<td>${employee.positionName }</td>
				
				<%-- <td>${employee.basicPay }</td> 1,000,000(그루핑?) 이렇게 만들기 위해 ↓--%>
				
				<td>
					<fmt:formatNumber value="${employee.basicPay }" groupingUsed="true"></fmt:formatNumber>
				</td>
				
				<!-- <td>${employee.extraPay }</td> -->
				<td>
					<fmt:formatNumber value="${employee.extraPay }" groupingUsed="true"></fmt:formatNumber>
				</td>
				
				<!-- <td>${employee.pay }</td> -->
				<td>
					<fmt:formatNumber value="${employee.pay }" groupingUsed="true"></fmt:formatNumber>
				</td>
				
				<!-- <td>관리자</td> -->
				<%-- <td>${employee.grade }</td> --%>
				<td>${employee.grade==0 ? "관리자" : "일반직원" }</td>
				
				<td><button type="button" class="btn updateBtn"
					value="${employee.employeeId }">수정</button></td>
				<td><button type="button" class="btn deleteBtn"
					value="${employee.employeeId }">삭제</button></td>
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