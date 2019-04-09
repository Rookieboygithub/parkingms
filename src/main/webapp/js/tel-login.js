// 使用回车登陆
$("body").keydown(function() {
	if (event.keyCode == "13") { //keyCode=13是回车键
		$('#con').click();
	}
});

var countdown = 60;
function login() {
	var tel = $("#tel").val();
	var code = $("#code").val();
	var obj = {
		"Tel" : tel,
		"code" : code
	};
	var reg = "^[1][3,4,5,7,8][0-9]{9}$";
	if (tel.match(reg) == null) {
		$("#tip3").hide(300);
		$("#tip1").html("<span style='color:red;font-size:14px;'>手机号有错</span>").show(300).delay(1200).hide(300);
		$("#tip3").delay(1200).show(300);
		return;
	}
	if (code.length < 6) {
		$("#tip4").hide(300);
		$("#tip2").html("<span style='color:red;font-size:14px;'>验证码错误</span>").show(300).delay(1200).hide(300);
		$("#tip4").delay(1200).show(300);
		return;
	}
	$.ajax({
		url : "../mlogin.action",
		method : "post",
		async : true,
		data : obj,
		success : function(data) {
			if (data != "success") {
				$("#tip4").hide(300);
				$("#tip2").html("<span style='color:red;font-size:14px;'>" + data + "</span>").show(300).delay(1200).hide(300);
				$("#tip4").delay(1200).show(300);
			} else {
				window.location.href = "./index.jsp"
			}
		}
	});
}

function getcode(obj) {
	var reg = "^[1][3,4,5,7,8][0-9]{9}$";
	var tel = $("#tel").val();
	if (tel != "" && tel.match(reg) != null) {
		settime(obj);
		$.ajax({
			url : "../getMessageCode.action",
			method : "post",
			async : true,
			data : {
				tel : tel
			},
			success : function(data) {
				if (data != "发送成功!") {
					$("#tip4").hide(300);
					$("#tip2").html("<span style='color:red;font-size:14px;'>" + data + "</span>").show(300).delay(1200).hide(300);
					$("#tip4").delay(1200).show(300);
					countdown = 0;
				} else {
					$("#tip4").hide(300);
					$("#tip2").html("<span style='color:red;font-size:14px;'>验证码发送成功!</span>").show(300).delay(1200).hide(300);
					$("#tip4").delay(1200).show(300);
				}
			}
		});
	} else {
		$("#tip3").hide(300);
		$("#tip1").html("<span style='color:red;font-size:14px;'>手机号有错</span>").show(300).delay(1200).hide(300);
		$("#tip3").delay(1200).show(300);
	}
}

function settime(obj) {
	if (countdown == 0) {
		$(obj).css("color", "black");
		$(obj).attr("disabled", false);
		$(obj).text("获取验证码");
		countdown = 60;
		$.ajax({
			url : "../setMessageCode.action",
			method : "get",
			async : false,
		});
		return;
	} else {
		$(obj).attr("disabled", true);
		$(obj).css("color", "red");
		$(obj).text("(" + countdown + ") s 重新发送");
		countdown--;
	}
	setTimeout(function() {
		settime(obj)
	}, 1000);
}