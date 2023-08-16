<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
<link rel="stylesheet" href="${contextPath }/css/logout.css">
</head>
<body>
  <div class="container">
    <div class="message">${requestScope.message }</div>
   <%--  <div class="message">현재 접속자수는 ${applicationScope.currentCount } 입니다.</div> --%>
    <a href="${contextPath }/Index.do">메인페이지로</a>
  </div>
</body>
</html>