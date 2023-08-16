//이름 유효성 검사
var name1 = document.querySelector("#name");

name1.addEventListener("input", function() {
  var value = this.value;
  var regex = /^[a-zA-Zㄱ-ㅎ가-힣]*$/; // 영문과 한글만을 허용하는 정규식
  
  if (!regex.test(value)) {
    this.value = value.replace(/[^a-zA-Zㄱ-ㅎ가-힣]/g, ""); // 영문과 한글 이외의 문자 제거
    alert("영문과 한글만 입력할 수 있습니다.");
  }
});

//전화번호 유효성검사
var phone = document.getElementById("phone");

phone.addEventListener("input", function() {
  var value = this.value;
  var sanitizedValue = sanitizePhoneNumber(value);

  this.value = sanitizedValue;
});

function sanitizePhoneNumber(value) {
  var sanitizedValue = value.replace(/[^0-9]/g, ""); // 숫자 이외의 문자 제거

  var formattedValue = '';
  var chunkLengths = [3, 4, 4]; // 각 청크(3, 4, 4)의 길이 설정

  var index = 0;
  for (var i = 0; i < chunkLengths.length; i++) {
    var chunk = sanitizedValue.slice(index, index + chunkLengths[i]);
    if (chunk.length > 0) {
      formattedValue += chunk;
      if (i < chunkLengths.length - 1) {
        formattedValue += '-';
      }
    }
    index += chunkLengths[i];
  }

  return formattedValue;
}
//비밀번호 유효성검사
var pwd1 = document.querySelector("#pwd");
var errorMessage = document.querySelector("#pwdErrorMessage");

pwd1.addEventListener("input", function() {
  var value = this.value;
  var regex = /^[a-zA-Z0-9!@]*$/; // 숫자, 영문, 특수문자('!', '@')만 허용하는 정규식

  if (!regex.test(value)) {
    errorMessage.style.visibility = 'visible';
  } else {
    errorMessage.style.visibility = 'hidden';
  }
});
//나이 유효성검사
var input = document.getElementById("age");

input.addEventListener("input", function() {
  var value = this.value;
  var regex = /^[0-9]*$/; // 숫자만 허용하는 정규식

  if (!regex.test(value)) {
    this.value = value.replace(/[^0-9]/g, ""); // 숫자 이외의 문자 제거
    alert("숫자만을 입력하세요")
  }
});
//아이디 유효성검사
var uid = document.querySelector("#uid");

uid.addEventListener("input", function() {
  var value = this.value;
  var regex = /^[a-zA-Z0-9]*$/; // 영문과 숫자만을 허용하는 정규식
  
  if (!regex.test(value)) {
    this.value = value.replace(/[^a-zA-Z0-9]/g, ""); // 영문과 숫자 이외의 문자 제거
    alert("영문과 숫자만 입력할 수 있습니다.");
  }
});

