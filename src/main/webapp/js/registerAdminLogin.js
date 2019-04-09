/**
 * 
 * @auther:杨向东
 * 
 * */

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
	
	
	//alert($("#pwd").val()+"和"+$("#pwd2").val());
/*	if($("#tip0").val().length>0){
		alert($("#tip0").val().length);
		layer.alert("工号已存在",{icon:5,time:1000});
		return;
	}*/
	if($("#pwd").val()!=$("#pwd2").val()){
		layer.alert("两次密码不一致，请重新输入",{icon:1,time:1000});
		return;
	}
	$.ajax({
		url : "/parkingms/registerAdminLoginAction.action",
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
			"pwd" : $("#pwd").val(),//密码的验证，第二个密码没有bean属性对应，当两次密码不一致时，可以注册成功，密码为第一次密码，这个bug要改，弄一个失焦事件
			"character" : $("#character").val()//最后一个参数，后面的逗号，可写可不写
			
		},
		success : function(message) {
			//console.info(message)//在控制台
			layer.alert("注册成功",{icon:6,time:10000});
		}

	});
}
