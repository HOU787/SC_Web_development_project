/**
 * 
 */

document.getElementById("submitBtn").addEventListener('click', validation);

function validation(e) {
	 e.preventDefault();  // 기능이 있는 태그의 기본 기능을 삭제 (a, submit, reset 등)

	 // 아이디(이메일)
	 let m1 = document.getElementById("member_id");
	  if(m1.value.length == 0) {
		 alert("이메일을 입력해주세요");
		 m1.focus();
		 m1.select();
		 return;
	 }
	 
	 // 비밀번호
	 let m2 = document.getElementById("member_pw");
	  if(m2.value.length == 0) {
		 alert("비밀번호를 입력해주세요");
		 m2.focus();
		 m2.select();
		 return;
	 }
	 
	
	 let memberForm = document.getElementById("memberForm");

	 memberForm.submit()
	  
 }