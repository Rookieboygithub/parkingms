function checkbox() {
	var user = 0;
	var car = 0;
	var pact = 0;
	var complain = 0;
	if ($("#authorityUser").is(":checked")) {
		user = 1;
	}
	if ($("#authorityCar").is(":checked")) {
		car = 1;
	}
	if ($("#authorityPact").is(":checked")) {
		pact = 1;
	}
	if ($("#authorityComplain").is(":checked")) {
		complain = 1;
	}

	$.ajax({
		url : "/management-companytreaty/registerAdminLoginAction.action",
		type : "post",
		dataType : "json",
		data : {
			"authorityUser" : user,
			"authorityCar" : car,
			"authorityPact" : pact,
			"authorityComplain" : complain,
			"name" : $("#name").val(),
			"tel" : $("#tel").val(),
			"account" : $("#account").val(),
			"pwd" : $("#pwd").val(),
			"character" : $("#character").val()
			
		},
		success : function(message) {
			console.info(message)
		}

	});
}
