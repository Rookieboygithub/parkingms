<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>CBD车位管理系统</title>

<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/css/x-admin.css" media="all">
<link rel="stylesheet" href="<%=path%>/css/xadmin.css">
<script type="text/javascript" src="<%=path%>/js/public/jQuery-2.2.2.js"></script>
<style type="text/css">
#tip1, #tip2 {
	height: 15px;
	line-height: 15px;
}

a:HOVER {
	color: red;
}
</style>
</head>
<body class="login-bg">

	<div class="login layui-anim layui-anim-up">
		<div class="message">CBD车位管理系统</div>
		<div id="darkbannerwrap"></div>
		<form>
			<input name="account" placeholder="用户名" id="account" type="text"
				lay-verify="required" class="layui-input" required>
			<div id="tip1"></div>
			<input name="password" lay-verify="required" id="password"
				placeholder="密码" type="password" class="layui-input" required>
			<div id="tip2"></div>
			<input name="code" lay-verify="required" id="code" placeholder="验证码"
				type="text" class="layui-input" style="width: 150px" required>
			<div style="position: absolute; top: 267px;left: 220px">
				<img id="codeimg" onclick="getcode()" width="150px">
			</div>
			<div id="tip3"></div>
			<hr class="hr15">
			<input value="登录" id="con" type="button" lay-filter="" lay-submit=""
				style="width:100%;" onclick="login()">
			<hr class="hr20">
			<div style="text-align: right;">
				<a href="${pageContext.request.contextPath}/jsp/tel-login.jsp"
					style="position: relative;right:90px;">忘记密码了？</a> <a
					href="${pageContext.request.contextPath}/jsp/register.jsp"
					style="position: relative;left:20px;">还没有账号？注册一个吧>></a>
			</div>
			<div id="tip2"></div>
		</form>
	</div>
	<input type="hidden" id="character" value="${sessionScope.character}">
	<div class="layui-footer footer footer-demo">
		<div class="layui-main">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>
</body>
<script language="JavaScript"> 
if (window != top) 
top.location.href = location.href; 
</script>
<script src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript">

	$("body").keydown(function() {
		if (event.keyCode == "13") { //keyCode=13是回车键
			$('#con').click();
		}
	});

	function login() {
		var account = $("#account").val();
		var password = $("#password").val();
		var code = $("#code").val();
		var obj = {
			"account" : account,
			"pwd" : password,
			"code" : code
		};
		if (code != "" && account != "" && password != "") {
			$.ajax({
				url : "<%=path%>/login.action",
				method : "post",
				async : true,
				data : obj,
				success : function(result) {
					var data = result.result;
					if (data == "fail" || data == null) {
						$("#tip2").html("<span style='color:red;font-size:14px;'>用户名或密码错误</span>").show(300).delay(3000).hide(300);
						;
						getcode();
					} else if (data == "success") {
						if (result.character == 0 || result.character == 1) {
							window.location.href = "${pageContext.request.contextPath}/jsp/index.jsp";
						} else {
							window.location.href = "<%=path%>/jsp/index2.jsp";
						}


					} else if (data == "failcode") {
						$("#tip3").html("<span style='color:red;font-size:14px;'>验证码错误</span>").show(300).delay(3000).hide(300);
						;
						getcode();
					}
				}
			})
		} else {
			$("#tip1").html("<span style='color:red;font-size:14px;'>信息不能为空</span>").show(300).delay(3000).hide(300);
			;
			getcode();
		}
	}

	function getcode() {
		document.getElementById("codeimg").src = "/parkingms/verifyCode.action?a=" + Math.random();
	}
	getcode();
</script>
</html>