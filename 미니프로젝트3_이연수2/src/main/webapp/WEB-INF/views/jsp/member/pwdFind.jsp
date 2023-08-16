<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 결과</title>
<link rel="stylesheet" href="${contextPath }/css/pwdFind.css">
</head>
<body>
  <div class="container">
    <div class="message">${requestScope.message}</div>
    <a href="${contextPath }/member/loginForm.html">로그인하기</a>
  </div>
</body>
</html>