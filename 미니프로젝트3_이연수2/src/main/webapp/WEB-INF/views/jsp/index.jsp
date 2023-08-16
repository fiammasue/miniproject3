<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 

<!DOCTYPE html> 
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>I love Yeonsu</title>
    <link rel="stylesheet" href="/myProject/css/style.css">
    <link rel="stylesheet" href="/myProject/css/header.css">
</head>
<body>
    <div id="container">
        <!-- 로그인 회원가입부분 -->
        <!-- js에 값을 받아오는게 안되면 걍 로그아웃 마이페이지를 enterMember로옮기면됌 -->
        <div id="enterMember">
        <c:choose>
        	<c:when test="${empty loginMember }">
	        	<ul class="members" id="loginOptions">
	                <li><a href="/myProject/member/loginForm.html" id="loginToggle">로그인</a></li>
	                <li><a href="/myProject/member/agreeForm.html">회원가입</a></li>
	             </ul>
        	</c:when>
        	<c:when test="${(not empty loginMember) && equalsAdmin }">
	        	<ul class="members" id="loggedInOptions">
	                <li><a href="/myProject/LogoutMember.do" id="logoutToggle">로그아웃</a></li>
	                <li><a href="/myProject/View.do">마이페이지</a></li>
        			<li><a href="/myProject/AllMemberShow.do">회원전체보기</a></li>
        		</ul>
        	</c:when>
        	<c:when test="${not empty loginMember}">
        		<ul class="members" id="loggedInOptions">
	                <li><input type="button" id="logoutToggle" value="로그아웃"/></li>
	                <li><a href="/myProject/View.do">마이페이지</a></li>
	           </ul>
        	</c:when>
        	
        	
        </c:choose>
        </div>
        <header>
            <!-- 로고만들기 -->
            <div id="logo">
                <a href="/myProject/Index.do"><h1>I love Yeonsu</h1></a>
            </div>
            <!-- 메뉴만들기 -->
            <nav>
                <ul id="topMenu">
                    <li><a href="#">놀러갈 곳<span>▼</span></a>
                        <ul>
                            <li><a href="#">별이언니네</a></li>
                            <li><a href="#">외할머니네</a></li>
                        </ul>
                    </li>
                    <li><a href="#">급식이<span>▼</span></a>
                    <ul>
                        <li><a href="#">초딩</a></li>
                        <li><a href="#">중딩</a></li>
                        <li><a href="#">고딩</a></li>
                    </ul>
                    </li>
                    <li><a href="/myProject/AllNotice.do">공지사항</a></li>
                    <li><a href="/myProject/AllBoard.do">게시판</a></li>
                </ul>
            </nav>
        </header>
        <!-- 슬라이드 그림부분 -->
        <div id="slideShow"> 
            <div id="slides">
                <img src="/myProject/images/slide01.jpg" alt="">
                <img src="/myProject/images/slide02.jpg" alt="">
                <img src="/myProject/images/slide03.jpg" alt="">
                <button id="prev">&lang;</button>
                <button id="next">&rang;</button>
            </div>
        </div>
        <!-- 탭부분과 퀵메뉴 -->
        <div id="contents">
            <!-- 탭부분 -->
            <div id="tabMenu">
                <input type="radio" id="tab1" name="tabs" checked>
                <label for="tab1">공지사항</label>
                <input type="radio" id="tab2" name="tabs">
                <label for="tab2">게시판</label>
           <!-- 공지사항 내용 -->
            <div id="notice" class="tabContent">
                <h2>공지사항 내용입니다.</h2>
                
                <table>
                <c:forEach var="notice" items="${noticeList}">
                	<tr>
		                 <td>${notice.boardid }</td>
		                 <td><a href='/myProject/ViewOneBoard.do?boardid=${notice.boardid }'>${notice.title }</a></td>
		                 <td>${notice.memberid }</td>
	                 </tr>
                </c:forEach>
                <c:if test="${ noticeListSize == 0}">
               		 <tr>
	                 	<td colspan=3>현재 자료가 존재 하지 않습니다</td>
                 	 </tr>
                </c:if>
                </table>
            </div>
            <!-- 게시판 내용 -->
            <div id="gallery" class="tabContent">
                <h2>게시판 내용입니다.</h2>
                <table>
                <c:forEach var="board" items="${boardList}">
	                <tr>
		                 <td>${board.boardid }</td>
		                 <td><a href='/myProject/ViewOneBoard.do?boardid=${board.boardid }'>${board.title }</a></td>
		                 <td>${board.memberid }</td>
	                 </tr>
                </c:forEach>
                <c:if test="${noticeListSize ==0 }">
                	<tr>
	                 	<td colspan=3>현재 자료가 존재 하지 않습니다</td>
	             	 </tr>
                </c:if>
                </table>
            </div>
        </div>
        <!-- 동그란 퀵메뉴 -->
            <div id="links">
                <ul>
                    <li>
                        <a href="#">
                            <span id="quick-icon1"></span>
                            <p>승기천</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span id="quick-icon2"></span>
                            <p>연안부두</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span id="quick-icon3"></span>
                            <p>강화루지</p>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <footer>
            <!-- 바닥부분 메뉴 4개 -->
            <div id="bottomMenu">
                <ul>
                    <li><a href="#">회사소개</a></li>
                    <li><a href="#">개인정보처리방침</a></li>
                    <li><a href="#">여행약관</a></li>
                    <li><a href="#">사이트맵</a></li>
                </ul>
            </div>
            <!-- sns 사진부분 -->
            <div id="sns">
                <ul>
                    <li><a href="#"><img src="/myProject/images/sns-1.png" alt=""></a></li>
                    <li><a href="#"><img src="/myProject/images/sns-2.png" alt=""></a></li>
                    <li><a href="#"><img src="/myProject/images/sns-3.png" alt=""></a></li>
                </ul>
            </div>
            <!-- 회사주소 -->
            <div id="company">
                <p>제주특별자치도 ***동 ***로 *** (대표전화) 123-456-7890</p>
            </div>
        </footer>
    </div>
    <!-- 슬라이드 쇼 넘기는 js -->
    <script src="/myProject/js/slidesshow.js"></script>
    <!-- 로그인 유무로 버튼을 다르게 뜨게하는 js-->
    <!-- <script src="js/loginState.js"></script> -->
    <script>
    document.querySelector("#logoutToggle").addEventListener("click", e => {
        fetch("LogoutMember.do")
        .then((response) => response.json())
        .then((json) => {
            alert(json.message);
            if (json.status) {
                location.href = 'Index.do';
            }
        });
    });

    </script>
</body>
</html>