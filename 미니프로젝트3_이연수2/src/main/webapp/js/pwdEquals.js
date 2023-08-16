let pwd = document.querySelector("#pwd");
let pwd2 = document.querySelector("#pwd2");

document.querySelector("#insertMember").onclick = ()=>{
    if(pwd.value!=pwd2.value){
        alert("비밀번호를 다시 확인해주세요");
        return false;
    }
}