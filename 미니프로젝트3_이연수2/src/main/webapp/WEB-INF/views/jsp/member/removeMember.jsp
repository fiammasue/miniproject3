<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>     
 <c:set var="contextPath" value="${ pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴결과</title>
<link rel="stylesheet" href="${contextPath }/css/remove.css">
</head>
<body>
  <div class="container">
    <div class="message">${requestScope.message }</div>
    <a href="${contextPath }/Index.do">메인페이지로</a>
  </div>
</body>
</html>