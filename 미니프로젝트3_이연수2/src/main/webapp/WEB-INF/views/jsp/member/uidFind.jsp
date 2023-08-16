<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>       
   <c:set var="contextPath" value="${ pageContext.request.contextPath}"/>      
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>아이디 찾기 결과</title>
<link rel="stylesheet" href="${contextPath }/css/uidFind.css">
</head>
<body>
  <div class="container">
	<div class="message">${requestScope.message } ${requestScope.uid }</div>
	<a href="${contextPath }/member/loginForm.html">로그인 페이지로</a></div>
</body>
</html>