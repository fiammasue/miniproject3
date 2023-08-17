<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" href="${contextPath }/css/insertBoard.css">
</head>
<body>
	<div id="container">
		<form id="insertBoard" action="${contextPath }/InsertBoard.do" method="post" autocomplete="off" onsubmit="return false;">
			<!-- 현재 아이디를 기본으로 입력하고 있어서 아이디 변경은 없었으면 좋겠다...jsp인가? -->
			<table class="tb">
				<caption>
					<span>게시글 작성</span>
				</caption>
				<tr>
					<th class="col">작성자</th>
					<td><input type="text" name="memberid" id="memberid"
						value="${loginMember.uid}" readonly /></td>
				</tr>
				<tr>
					<th class="col"><label for="title">제목</label></th>
					<td><input type="text" name="title" id="title" /></td>
				</tr>
				<tr>
					<th class="col"><label for="contents">내용</label></th>
					<td><textarea form="insertBoard" name="contents" id="contents" ></textarea></td>
				</tr>
				<tr>
					<th class="col"><label>글종류</label></th>
					<td>
						<div class="boardType">
							<input id="w" type="radio" name="boardtype" value="공지사항" checked>
							<label for="w">공지사항</label> <input id="m" type="radio"
								name="boardtype" value="게시판"> <label for="m">게시판</label>
						</div>
					</td>
				</tr>
				<tr>
					<th class="col"><label for="fixed_yn">상단 고정</label></th>
					<td><input type="checkbox" name="fixed_yn" id="fixed_yn"
						value='Y' /></td>
				</tr>
			</table>
			<input id="insertMember" type="submit" value="등록" /> 
			<input type="reset" value="초기화" />
		</form>
	</div>
	<script>
    	let insert = document.querySelector("#insertMember");
    	insert.addEventListener("click",function(e){
    		if(${empty loginMember}){
    			alert("로그인 후 이용해주세요");
    			location.href="/myProject/Index.do";
    			e.preventDefault();
    		}
    	});
    	
    	document.querySelector("#insertMember").addEventListener("click", e => {
    	    const param = {
    		        boardid: boardid.value,
    		        title: title.value,
    		        contents: contents.value,
    		        phone: phone.value,
    		        address : address.value,
    		        gender : radioValue,
    		        age: age.value
    		      };

    		      fetch("InsertMember.do", {
    		        method: "POST",
    		        headers: {
    		          "Content-Type": "application/json; charset=UTF-8",
    		        },
    		        body: JSON.stringify(param),
    		      })
    		      .then((response) => response.json())
    		      .then((json) => {
    		    	  alert(json.message);
    		          if (json.status) {
    		        	  location.href = "loginForm.do"; 
    		          }
    		      });
    		
    	});
    	
    </script>
</body>
</html>