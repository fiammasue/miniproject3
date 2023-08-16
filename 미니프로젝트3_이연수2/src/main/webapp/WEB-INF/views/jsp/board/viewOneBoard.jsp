<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>        
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 하나보기</title>
<link rel="stylesheet" href="${contextPath }/css/viewBoard.css">
</head>
<body>
 <c:if test="${ requestScope.existBoard}">
      <script>
		alert("게시물이 삭제되었습니다.");
		history.back();
	</script>
</c:if>
	 <div id="content">
        <table class="post">
            <tr>
                <th>제목</th>
                <td colspan="5">${board.title}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${board.memberid}</td>
                <th>작성날짜</th>
                <td>${board.regdate}</td>
                <th>조회수</th>
                <td>${board.hit}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="5"><textarea name="" id="text1" cols="60" rows="10" readonly>${board.contents}</textarea></td>
            </tr>
        </table>
        
         <div id="fixed-buttons">
            <button id="goList" value="${boardtype}">목록</button>
             <button id="goMain">메인페이지로</button>
        

		<%-- <li><a href='${boardtype}'>목록보기</a> </li>
 --%> 
	 <c:if test='${(not empty loginMember) && requestScope.equalWriter}'>
 		  <button id="goRevise" value="ReviseBoardForm.do">글수정</button>
            <button id="goDelete" value="DeleteBoard.do">글삭제</button>
 		  
 		  <%-- <li><a href="${contextPath }/DeleteBoard.do">삭제</a></li> 
          <li><a href="${contextPath }/ReviseBoardForm.do">수정</a></li> --%>
 		</c:if>
 </div>
 <form id="insertReply" action="/myProject/InsertReply.do" method="get">
            
          <label for="comment" id="comment">댓글 입력</label>
          <input id="userid" name="memberid" value="${loginMember.uid}" readonly>
            <textarea form="insertReply" class="comment" name="comment" cols="60" rows="5"></textarea>
            <div class="userButton">
            <c:if test='${not empty loginMember}'>
              <button form="insertReply" type="submit">등록</button>
             </c:if>
            </div>
        </form>

            <!-- Sample existing comment -->
      <c:forEach var="reply" items="${replyList}">
        <form id="existReply" action="/myProject/ReviseDeleteReply.do" method="get">
        <input id="boardid" name ="boardid" value="${reply.boardid}"/> <input id="replyid" name="replyid" value="${reply.replyid}"/>
            <span class="comment-id">${reply.memberid }</span>
            <span class="comment-time">${reply.regdate }</span>
            <textarea form="existReply" id="reviseReply" class="comment" name="comment" cols="60" rows="5" disabled>${reply.comments}</textarea>
            <c:if test="${loginMember.uid eq reply.memberid }">
            <div class="userButton">
            <button form="existReply" class="edit-button" name="submitValue" value="수정">수정</button>
            <button  id="tt" form="existReply" class="delete-button" type="submit" name="submitValue" value="삭제">삭제</button>
            </div>
            </c:if>
        </form>
      </c:forEach>
</div>
    <script src="${contextPath }/js/replyDelete.js"></script>
    <script src="${contextPath }/js/goList.js"></script>
    <script src="${contextPath }/js/goMain.js"></script>
    <script src="${contextPath }/js/viewR.js"></script>    
    <script src="${contextPath }/js/goRevise.js"></script>
    <script src="${contextPath }/js/goDelete.js"></script>
</body>
</html>