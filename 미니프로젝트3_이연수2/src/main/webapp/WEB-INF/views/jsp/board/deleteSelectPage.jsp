<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>     
 <c:set var="contextPath" value="${ pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선택삭제완료</title>
<link rel="stylesheet" href="${contextPath }/css/deleteBoard.css">
</head>
<body>
<div class="container">
    <div class="message">${requestScope.message}</div>
    <a href="${contextPath }/Index.do">메인페이지로</a>
    <a href="${sessionScope.boardtype}">목록으로</a>
  </div>
</body>
</html>