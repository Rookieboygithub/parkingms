
/*获取添加结果*/
function company_add() {
	var addstat = $("#addstat").val();
	var add = "初始态";
	if (add != "清除数据") {
		add = addstat;
	}
	layui.use('layer', function() {
		var layer = layui.layer;
		if (add == "注册成功") {
			layer.alert(add);
			add = "清除数据";
		} else if (add == "注册失败") {
			layer.alert(add);
			add = "清除数据";
		}
	});
}
/*提示弹框方法*/
function pop_up(string) {
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.msg(string, {
			icon : 5, //设置弹出框样式
			time : 2000 //自动关闭时间
		});
	});

}

company_add();
/*导入layui格式及提供layui表单验证*/
function layui_element() {
	layui.use([ 'form', 'jquery', 'element' ], function() {
		var form = layui.form,
			element = layui.element,
			$ = layui.jquery;
		form.verify({
			username : [
				/^[a-z0-9A-Z\u4e00-\u9fa5]{6,18}$/
				, '登录名为6~18位数字、字母或汉字' ],
			pass : [
				/*'密码必须6到12位，且不能出现空格'*/
				/^[\S]{6,12}$/
				, '密码必须6到12位，且不能出现空格'
			],
			repass : function(value) {
				var repassvalue = $('#password').val();
				if (value != repassvalue) {
					return '两次输入的密码不一致!';
				}
			}
		});
	});
}
layui_element();
/*检测登录名格式及是否存在*/
function usernameCheck() {
	var rule = /^[a-z0-9A-Z\u4e00-\u9fa5]{6,18}$/;
	var username = $("#username").val();
	if (!rule.test(username)) {
		pop_up("登录名为6~18位数字、字母或汉字");
	} else {
		usernameExist(username);
	}
}
/*检测密码格式*/
function passwordCheck() {
	var rule = /^[\S]{6,12}$/;
	var password = $("#password").val();
	if (!rule.test(password)) {
		pop_up("密码必须6到12位，且不能出现空格");
	}
}
function passwordSameCheck() {
	var password = $("#password").val();
	var repassword = $("#repassword").val();
	if (!(password === repassword)) {
		pop_up("两次输入密码不一致");
	}
}
/*检测是否为空*/
function nullCheck(elements) {
	var elemnt = $("#" + elements).val();
	if (elemnt == "") {
		pop_up("必填项不能为空");
	}
}
/*检测电话号码的格式是否正确*/
function telCheck() {
	var element = $("#company_tel").val();
	var rule = /^1\d{10}$/;
	if (!rule.test(element)) {
		pop_up("请填入正确的手机号");
	}
}
/*检测用户名是否已经存在*/
function usernameExist(username) {
	$.ajax({
		url : "company/loginNameCheck.action",
		type : "post",
		async : "true",
		dataType : "json",
		data : {
			loginName : username,
		},
		success : function(data) {
			pop_up(data);
		}
	})
}
function changeCheck(id) {
	var elemnt = $("#" + id).change(usernameCheck());
}