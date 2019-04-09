function deleteFlag() {


	$.ajax({
		url : "/management-companytreaty/deleteAdminLoginAction.action",
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