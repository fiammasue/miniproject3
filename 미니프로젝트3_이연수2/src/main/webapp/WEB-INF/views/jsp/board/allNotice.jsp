<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 사항 전체보기</title>
<link rel="stylesheet" href="${contextPath }/css/allBoard.css">
</head>
<body>
	<form action="/myProject/DeleteSelectPage.do" method='post'>
		<div id="contain">
			<table class="tb">
				<caption>공지사항</caption>
				<tr class="head">
				<c:if test='${ (not empty loginMember) && (loginMember.uid eq "root") }'>
					<th>전체선택 <input type='checkbox' id='chk'></th>
					</c:if>
					<th>글번호</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성자</th>
				</tr>

				<c:if test="${not empty boardList}">
					<c:forEach var="board" items="${boardList }">
						<tr>
						<c:if test='${ (not empty loginMember) && (loginMember.uid eq "root") }'>
							<th><input type='checkbox' class='boardid' name='boardid'
								value='${board.boardid}'></th>
							</c:if>
							<td class="kk">${board.boardid}</td>
							<td><a href='/myProject/ViewOneBoard.do?boardid=${board.boardid}'
								class="col">${board.title}</a></td>
							<td>${board.hit}</td>
							<td>${board.memberid}</td>
						</tr>
					</c:forEach>

				</c:if>
			</table>
			<a href="/myProject/Index.do">메인페이지로</a> 
			<a href="/myProject/InsertForm.do">등록</a>

			<c:if
				test="${(not empty sessionScope.loginMember) && requestScope.result }">
				<input class="delete-button" type='submit' value="삭제">
			</c:if>

		</div>
	</form>
	<script type="text/javascript">
		let allChk = document.querySelector("#chk");
	    let boardIds = document.querySelectorAll(".boardid");
	    allChk.addEventListener("change", function() {
/*	    	
            for (let i = 0; i < boardIds.length; i++) {
                chkBox[i].checked = allChk.checked;
            }
*/
	            boardIds.forEach(e=>e.checked = allChk.checked);
	        });
	
	</script>

<script src="${contextPath }/js/replyDelete.js"></script>

</body>
</html>