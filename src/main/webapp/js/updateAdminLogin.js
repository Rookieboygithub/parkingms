/**
 * 
 * @auther:杨向东
 * 
 * */


function checkbox() {

	
	$.ajax({
		url : "/parkingms/updateAdminLoginAction.action",
		type : "post",
		async:false,
		method : "post",
		dataType : "json",
		data : {
			
			"authorityUser" : $("#authorityUser").val(),
			"authorityCar" : $("#authorityCar").val(),
			"authorityPact" : $("#authorityPact").val(),
			"authorityComplain" : $("#authorityComplain").val(),
/*			"name" : $("#name").val(),
			"tel" : $("#tel").val(),
			"account" : $("#account").val(),
			"pwd" : $("#pwd").val(),
			"character" : $("#character").val(),*/
			
			"loginid" : $("#loginid").val()
			
		},
		success : function(data) {
			//alert("1");
			//先从页面获取到数据，在隐藏input中
			var authorityUser = $("#authorityUser").val();
			var authorityCar = $("#authorityar").val();
			var authorityPact = $("#authorityPact").val();
			var authorityComplain = $("#authorityComplain").val();
			
			content = 
				"<span> "+aaa(authorityUser)+" </span> "+
				"<span> "+aaa(authorityCar)+" </span> "+
				"<span> "+ccc(authorityPact)+" </span> "+
				"<span> "+ddd(authorityComplain)+" </span> ";       
			
			//写入到html中
			$("#checkbox").append(content);
		}
	});
}

checkbox();


/**
 * 
 * 下面大量的重复代码，该怎么解决
 * @param data
 * @returns {String}
 */
function aaa(data){
	if(data == 1){		
		str = "<input type='checkbox' name='authorityUser' title='用户管理权限' id='authorityUser' checked>";
	}else{
		str = "<input type='checkbox' name='authorityUser' title='用户管理权限' id='authorityUser'>";
	}
	return str;
}

function bbb(data){
	if(data == 1){		
		str = "<input type='checkbox' name='authorityCar' title='车位管理权限' id='authorityCar' checked>";
	}else{
		str = "<input type='checkbox' name='authorityCar' title='车位管理权限' id='authorityCar'>";
	}
	return str;
}

function ccc(data){
	if(data == 1){		
		str = "<input type='checkbox' name='authorityPact' title='合同管理权限' id='authorityPact' checked>";
	}else{
		str = "<input type='checkbox' name='authorityPact' title='合同管理权限' id='authorityPact'>";
	}
	return str;
}

function ddd(data){
	if(data == 1){		
		str = "<input type='checkbox' name='authorityComplain' title='投诉管理权限' id='authorityComplain' checked>";
	}else{
		str = "<input type='checkbox' name='authorityComplain' title='投诉管理权限' id='authorityComplain'>";
	}
	return str;
}
