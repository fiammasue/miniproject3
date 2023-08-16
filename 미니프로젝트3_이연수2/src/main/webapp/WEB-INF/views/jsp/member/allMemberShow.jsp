<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원전체보기</title>
<link rel="stylesheet" href="${contextPath }/css/allMemberShow.css">

</head>
<body>

	<div id="contain">
		<table class="tb">
			<caption>회원목록</caption>
			<tr class="head">
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>이름</th>
				<th>성별</th>
			</tr>

			<c:if test="${not empty memberList}">
				<c:forEach var="member" items="${memberList }">
					<tr>
						<td class="col">${member.uid }</td>
						<td>${member.name }</td>
						<td>${member.pwd }</td>
						<td>${member.age }</td>
						<td>${member.phone }</td>
						<td>${member.address }</td>
						<td>${member.gender }</td>
					</tr>
				</c:forEach>
			</c:if>


		</table>
		<a href="${contextPath }/Index.do">메인페이지로</a>
	</div>


</body>
</html>