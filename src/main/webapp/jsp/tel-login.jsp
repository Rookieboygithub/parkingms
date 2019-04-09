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
<link rel="stylesheet" href="../css/x-admin.css" media="all">
<link rel="stylesheet" href="../css/xadmin.css">
<script type="text/javascript" src="../js/public/jQuery-2.2.2.js"></script>
<style type="text/css">
#tip1, #tip2 ,#tip3, #tip4{
	height: 15px;
	line-height: 15px;
}

#send:HOVER{
	color: red;
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
			<input name="tel" placeholder="手机号" id="tel" type="text"
				lay-verify="required" class="layui-input" required maxlength="11">
			<div id="tip1"></div>
			<div id="tip3" hidden="hidden"></div>
			<input name="code" lay-verify="required" id="code" placeholder="验证码"
				type="text" class="layui-input" style="width: 190px" required maxlength="6">
			<div id="tip2"></div>
			<div id="tip4" hidden="hidden"></div>
			<button  id="send" style="position: relative;bottom: 52px;left:220px;border:none;font-size: 16px;background-color:transparent;" onclick="getcode(this)">免费获取验证码</button>
			<input value="登录" id="con" type="button" lay-filter="" lay-submit=""
				style="width:100%;" onclick="login()">
			<hr class="hr20">
			<div style="text-align: right;">
				<a href="${pageContext.request.contextPath}/jsp/register.jsp">还没有账号？注册一个吧>></a>
			</div>
		</form>
	</div>
	<div class="layui-footer footer footer-demo">
		<div class="layui-main">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="../js/tel-login.js"></script>
</html>