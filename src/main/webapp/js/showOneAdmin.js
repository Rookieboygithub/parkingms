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
		url : "/parkingms/findAdminAction.action",
		type : "post",
		async:false,
		method : "post",
		dataType : "json",
		data : {
			
			//"account" : $("#loginAccount").val()
			
		},
		success : function(data) {
			
			alert("进入到ajcx");
			var character = data.character;
			
			if(character == 0){
				//$("#loginAccount").val(data.account);
				//alert("超管界面");
				content = 
					"<input type='checkbox' name='authorityUser1' title='用户管理权限' id='authorityUser1' checked disabled >" +
					"<input type='checkbox' name='authorityUser2' title='车位管理权限' id='authorityUser2' checked disabled >" +
					"<input type='checkbox' name='authorityUser3' title='合约管理权限' id='authorityUser3' checked disabled >" +
					"<input type='checkbox' name='authorityUser4' title='投诉管理权限' id='authorityUser4' checked disabled >";
					
				//写入到html中
				$("#checkbox").append(content);
				
			}else if(character == 1){
				
				//alert("普通管理员界面");
				
				$("#loginAccount").val(data.account);
				//alert(data.name)
				$("#name").val(data.name);
				$("#tel").val(data.tel);
				//因为在页面不能直接显示数据0101,要显示对勾一类的图形
				content = 
					"<span> "+aaa(data.authorityUser)+" </span> "+
					"<span> "+aaa(data.authorityCar)+" </span> "+
					"<span> "+ccc(data.authorityPact)+" </span> "+
					"<span> "+ddd(data.authorityComplain)+" </span> ";       
				
				//写入到html中
				$("#checkbox").append(content);
			}else{
				//$("#checkbox").append("我还没想好");
				
				
			}

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
		str = "<input type='checkbox' name='authorityUser' title='用户管理权限' id='authorityUser' checked disabled >";
	}else{
		str = "<input type='checkbox' name='authorityUser' title='用户管理权限' id='authorityUser' disabled >";
	}
	return str;
}

function bbb(data){
	if(data == 1){		
		str = "<input type='checkbox' name='authorityCar' title='车位管理权限' id='authorityCar' checked disabled >";
	}else{
		str = "<input type='checkbox' name='authorityCar' title='车位管理权限' id='authorityCar' disabled >";
	}
	return str;
}

function ccc(data){
	if(data == 1){		
		str = "<input type='checkbox' name='authorityPact' title='合同管理权限' id='authorityPact' checked disabled >";
	}else{
		str = "<input type='checkbox' name='authorityPact' title='合同管理权限' id='authorityPact' disabled >";
	}
	return str;
}

function ddd(data){
	if(data == 1){		
		str = "<input type='checkbox' name='authorityComplain' title='投诉管理权限' id='authorityComplain' checked disabled >";
	}else{
		str = "<input type='checkbox' name='authorityComplain' title='投诉管理权限' id='authorityComplain' disabled >";
	}
	return str;
}
