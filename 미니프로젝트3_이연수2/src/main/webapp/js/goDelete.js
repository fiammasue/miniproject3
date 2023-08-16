let delete_go = document.querySelector("#goDelete");

delete_go.addEventListener("click",function(e){
	const flag = confirm("삭제하시겠습니까?");
	if(flag){
		var link =delete_go.value;
		location.href="/myProject/"+link;
	}
	else{
		e.preventDefault();
	}	
});