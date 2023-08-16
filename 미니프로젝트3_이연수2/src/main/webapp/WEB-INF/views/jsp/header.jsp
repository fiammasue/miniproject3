<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/header.css">
</head>
<body>
        <header>
            <!-- 로고만들기 -->
            <div id="logo">
                <a href="index.jsp"><h1>I love Yeonsu</h1></a>
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
                    <li><a href="board/allNotice.jsp">공지사항</a></li>
                    <li><a href="board/allBoard.jsp">게시판</a></li>
                </ul>
            </nav>
        </header>
</body>
</html>