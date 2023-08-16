<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>회원정보수정</title>
    <link rel="stylesheet" href="${contextPath }/css/reviseForm.css">
    </head>
    <body>
    <div id="container">
        <form action="${contextPath }/ReviseMember.do" method="get" autocomplete="off" >
            <!-- 현재 아이디를 기본으로 입력하고 있어서 아이디 변경은 없었으면 좋겠다...jsp인가? -->
            <table class="tb">
                <caption><span>${loginMember.uid }</span>님의 정보수정 페이지</caption>
                <tr>
                    <th class="col">아이디</th>
                    <td>${loginMember.uid}</td>
                </tr>
                <tr>
                    <th class="col"><label for="pwd">비밀번호</label></th>
                    <td> <input type="password" name="pwd" id="pwd"/>
                        <div id="pwdErrorMessage"></div></td>
                </tr>
                <tr>
                    <th class="col"><label for="pwd2">비밀번호 확인</label></th>
                    <td><input type="password" name="pwd2" id="pwd2"/></td>
                </tr>
                <tr>
                    <th class="col"> <label for="name">이름</label></th>
                    <td><input type="text" name="name" id="name"/><br/></td>
                </tr>
                <tr>
                    <th class="col"><label for="phone">전화번호</label></th>
                    <td><input id="phone" type="tel" name="phone"/></td>
                </tr>
                <tr>
                    <th class="col"><label for="address">주소</label></th>
                    <td><input type="text" name="address" id="address"/></td>
                </tr>
                <tr>
                    <th class="col"><label for="age">나이</label></th>
                    <td><input type="text" name="age" id="age"/></td>
                </tr>
                <tr>
                    <th class="col"><label>성별</label></th>
                    <td><div class="gender">
                        <div class="iner">
                            <input id="w" type="radio" name="gender" value="여" checked>
                            <label for="w">여</label>
                        </div>
                    <div class="iner">
                        <input id="m" type="radio" name="gender" value="남">
                        <label for="m">남</label>
                    </div>
                    </div>
                    </td>
                </tr>
            </table>
            <input id="insertMember" type="submit" value="회원정보수정"/>
            <input type="reset" value="초기화"/>
        </form>
    </div>
    <script src="${contextPath }/js/pwdEquals.js"></script>
    <script src="${contextPath }/js/inputCheck.js"></script>
    </body>
    </html>