<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>MemberList.jsp</title>

<!-- 부트스트랩 css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 제이쿼리 script -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<!-- 부트스트랩 script -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>

<div>
	<h1>회원 정보</h1>
	<hr>
</div>

<div class="container">
	<div class="panel-group">
		<div class="panel-default">
			
			<div class="panel-heading" id="title">
				회원 정보 입력
			</div>
			
			<div class="panel-body">
				<form role="form" action="memberinsert.action" method="post">
				
					<div class="form-group">
						<label for="name">
							NAME :  
						</label>
						<input type="text" class="form-control" id="name" name="name">
					</div>
					
					<div class="form-group">
						<label for="telephone">
							TELEPHONE :
						</label>
						<input type="tel" class="form-control" id="telephone" name="telephone">
					</div>
					
					<button type="submit" class="btn btn-success btn-sm">SUBMIT</button>
					<button type="reset" class="btn btn-success btn-sm btnCancel">CANCEL</button>
				
				</form>
			</div>
		</div><!-- close .panel-default -->
		
		<div class="panel panel-default">
			<div class="panel-heading">
				회원 정보 출력
			</div>
			
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th>MID</th>
							<th>NAME</th>
							<th>TELEPHONE</th>
							<th>삭제 / 수정</th>
						</tr>
					</thead>
					<tbody>
						<!-- 
						<tr>
							<td>1</td>
							<td>전혜림</td>
							<td>010-1111-1111</td>
							<td>
								<button type="button" class="btn btn-default btn-xs btnDelete">삭제</button>
								<button type="button" class="btn btn-default btn-xs btnUpdate">수정</button>
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>선혜연</td>
							<td>010-2222-2222</td>
							<td>
								<button type="button" class="btn btn-default btn-xs btnDelete">삭제</button>
								<button type="button" class="btn btn-default btn-xs btnUpdate">수정</button>
							</td>
						</tr> 
						-->
						
						<c:forEach var="member" items="${list }">
						<tr>
							<td>${member.mid }</td>
							<td>${member.name }</td>
							<td>${member.telephone }</td>
							<td>
								<button type="button" class="btn btn-danger btn-xs btnDelete">삭제</button>
								<button type="button" class="btn btn-primary btn-xs btnUpdate">수정</button>
							</td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
				
				<button type="button" class="btn btn-default btn-sm">
					Count <span class="badge">${count }</span>
				</button>
				
			</div>
		</div>
		
	</div>
</div>

</body>
</html>