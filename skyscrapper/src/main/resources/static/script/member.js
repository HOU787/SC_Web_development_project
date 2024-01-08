/**
 * 
 */

document.getElementById("submitBtn").addEventListener('click', validation);

function validation(e) {
	 e.preventDefault();  // 기능이 있는 태그의 기본 기능을 삭제 (a, submit, reset 등)

	 // 아이디(이메일)
	 let m1 = document.getElementById("m1");
	  if(m1.value.length == 0) {
		 alert("이메일을 입력해주세요");
		 m1.focus();
		 m1.select();
		 return;
	 }
	 
	 let m2 = document.getElementById("m2");
	  if(m2.value.length == 0) {
		 alert("이메일을 입력해주세요");
		 m2.focus();
		 m2.select();
		 return;
	 }
	 
	 let member_id = document.getElementById("member_id");
	 member_id.value = `${m1.value}@${m2.value}`;
	 
	 // 비밀번호
	 
	 let member_pw = document.getElementById("member_pw");
	 if(member_pw.value.length < 3 || member_pw.value.length > 5) {
		 alert("비밀번호는 3~5사이로 입력해주세요");
		 member_pw.focus();
		 member_pw.select();
		 return;
	 }	
	 
	 // 이름
	 
	 let member_nm = document.getElementById("member_nm");
	 if(member_nm.value.length == 0) {
		 alert("이름을 입력해주세요");
		 member_nm.focus();
		 member_nm.select();
		 return;
	 }	
	 
	 // 멤버타입
	 
	 let member_type = document.getElementById("member_type");
	 
	 let seller = document.getElementById("seller").checked;
	 let buyer = document.getElementById("buyer").checked;
	 
	
	 if(seller) 
		member_type.value  = 0;
	 if (buyer) 
	 	member_type.value = 1;
	 if (seller && buyer)
	 	member_type.value = 2;
	 	
	 let t1 = document.getElementById("t1");
	 let t2 = document.getElementById("t2");
	 let t3 = document.getElementById("t3");
	 let t4 = document.getElementById("t4");

	 /*
	 
	 if(isNaN(t1.value) || t1.value.length != 3 ) {
		 alert("국가는 문자가 포함되지 않는 3자리 숫자로 입력");
		 t1.focus();
		 t1.select();
		 return;
	 }
	 
	 if(isNaN(t2.value) || t2.value.length != 4 ) {
		 alert("전화번호는 문자가 포함되지 않는 4자리 숫자로 입력");
		 t2.focus();
		 t2.select();
		 return;
	 }
	 
	 if(isNaN(t3.value) || t3.value.length != 4 ) {
		 alert("전화번호는 문자가 포함되지 않는 4자리 숫자로 입력");
		 t3.focus();
		 t3.select();
		 return;
	 }
	 
	 if(isNaN(t4.value) || t4.value.length != 4 ) {
		 alert("전화번호는 문자가 포함되지 않는 4자리 숫자로 입력");
		 t4.focus();
		 t4.select();
		 return;
	 }
	 
	 */
	 
	 let phone = document.getElementById("company_tell");
	 phone.value = `+${t1.value}-${t2.value}-${t3.value}-${t4.value}`;
	
	 let memberForm = document.getElementById("memberForm");
	alert("Sign Up Successfully")
	 memberForm.submit()
	  
 }