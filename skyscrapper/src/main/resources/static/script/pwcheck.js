/**
 * 
 */
document.getElementById("submitBtn").addEventListener('click', validation);
 
function validation(e) {
	 e.preventDefault();
	 
	 
	 /* 패스워드 길이확인
	 
	 let memberpw = document.getElementById("member_pw");
	 if(memberpw.value.length < 3 || memberpw.value.length > 5) {
		 alert("비밀번호는 3~5사이로 입력해주세요");
		 memberpw.focus();
		 memberpw.select();
		 return;
	 }
	 */
	
	 let memberForm = document.getElementById("memberForm");

	 memberForm.submit();
	  
 }