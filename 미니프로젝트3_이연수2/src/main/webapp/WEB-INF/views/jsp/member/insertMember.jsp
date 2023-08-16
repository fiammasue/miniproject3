<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입완료</title>
<link rel="stylesheet" href="${contextPath }/css/insert.css">

</head>
<body>

	<div class="container">
		
		<div class="name"><span>${requestScope.uid }</span> 회원님 </div>
		<div class="message">${requestScope.message }</div>
		<a href="${contextPath }/member/loginForm.html">로그인 페이지로</a>
	</div>

</body>
</html>