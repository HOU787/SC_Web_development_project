
document.getElementById("sendBtn").addEventListener('click', validation);

function validation(e) {
   e.preventDefault();  // 기능이 있는 태그의 기본 기능을 삭제 (a, submit, reset 등)
   
		let product_id = $("#product_id").val();
		let snd_id = $("#snd_id").val();
		let rcv_id = $("#rcv_id").val();
		let message = $("#message").val();
	
	let sendData = {"product_id": product_id , "snd_id":snd_id, "rcv_id":rcv_id, "message":message}
	
	//alert(JSON.stringify(sendData))
    // let inquiryForm = document.getElementById("inquiryForm");
   // inquiryForm.submit();


   //alert(JSON.stringify(sendData));
   $.ajax({
      url: 'inquiry'
      , method: 'POST'
      , data: sendData
      , success: function(resp) {
         $("#message").text(resp["text"]);
         $('#exampleModal').modal('hide');  // 첫 번째 모달 닫기
         $('#exampleModalToggle2').modal('show');  // 두 번째 모달 열기
         
         $("#message").val("");
      }
   })

}