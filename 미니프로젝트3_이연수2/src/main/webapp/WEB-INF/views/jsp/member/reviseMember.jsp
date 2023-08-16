<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
 <c:set var="contextPath" value="${ pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정결과</title>
<link rel="stylesheet" href="${contextPath }/css/revise.css">
</head>
<body>
	<div class="container">
		<div class="message">${requestScope.message }</div>
		<a href="${contextPath }/View.do">회원정보상세보기</a>
	</div>
	
</body>
</html>