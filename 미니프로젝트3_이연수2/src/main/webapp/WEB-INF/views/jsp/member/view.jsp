<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>    
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <link rel="stylesheet" href="${contextPath }/css/view.css">
    </head>
    <body>
    <div id="container">
      <table class="tb">
        <caption><span>${loginMember.name }</span>님의 상세정보</caption>
        <tr>
          <th class="col">아이디</th>
          <td> ${loginMember.uid }</td>
        </tr>
        <tr>
          <th class="col">비밀번호</th>
          <td> ${loginMember.pwd }</td>
        </tr>
        <tr>
          <th class="col">이름</th>
          <td> ${loginMember.name }</td>
        </tr>
        <tr>
          <th class="col">전화번호</th>
          <td> ${loginMember.phone }</td>
        </tr>
        <tr>
          <th class="col">주소</th>
          <td> ${loginMember.address }</td>
        </tr>
        <tr>
          <th class="col">나이</th>
          <td> ${loginMember.age }</td>
        </tr>
        <tr>
          <th class="col">성별</th>
          <td> ${loginMember.gender }</td>
        </tr>
      </table>
        <ul id="link">
          <li><a href="${contextPath }/member/removeForm.html">회원탈퇴</a> </li>
          <li><a href="${contextPath }/ReviseForm.do">회원정보수정</a></li> 
          <li><a href="${contextPath }/Index.do">메인페이지로</a></li>
        </ul>
      </div>
    </body>
    </html>