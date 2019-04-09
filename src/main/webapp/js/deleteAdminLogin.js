
/**
 * 
 * @auther:杨向东
 * 
 * */

function deleteFlag() {


	$.ajax({
		url : "/parkingms/deleteAdminLoginAction.action",
		type : "post",
		dataType : "json",
		data : {
			
			"loginid" : $("#account").val()
			
		},
		success : function(message) {
			console.info(message)
		}

	});
}