<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>添加企业</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" href="lib/layuinew/css/layui.css">
<script type="text/javascript" src="js/public/jQuery-2.2.2.js"></script>
<style>
body {
	padding: 20px;
}

.layui-form-pane .layui-form-label {
	width: 130px;
}
</style>
</head>
<body>
	<div style="line-height: 40px;height: 40px;margin-bottom: 25px">
		<span class="layui-breadcrumb"> <a href="jsp/welcome.html">首页</a> <a><cite>增加企业</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm" onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>

	<div>
		<form class="layui-form layui-form-pane" action="company/addCompany.action" method="post" >
			<div class="layui-form-item">
				<label class="layui-form-label"> 企业登录名 </label>
				<div class="layui-input-inline">
					<input type="text" name="account" id="username" lay-verify="username" autocomplete="off" class="layui-input" onchange="usernameCheck()">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 企业登录密码 </label>
				<div class="layui-input-inline">
					<input type="password" id="password" name="pwd" lay-verify="pass" autocomplete="off" class="layui-input" onchange="passwordCheck()">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 再次确认密码 </label>
				<div class="layui-input-inline">
					<input type="password" id="repassword" name="pwder" lay-verify="repass" autocomplete="off" class="layui-input" onblur="passwordSameCheck()">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 企业名称 </label>
				<div class="layui-input-inline">
					<input type="text" name="name"  id="company_name" lay-verify="required" autocomplete="off" class="layui-input" onchange="nullCheck('company_name')">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 企业楼层位置 </label>
				<div class="layui-input-inline">
					<input type="text" name="addr" id = "company_addr" lay-verify="required" autocomplete="off" class="layui-input" onchange="nullCheck('company_addr')">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 企业联系人 </label>
				<div class="layui-input-inline">
					<input type="text" name="contact" id="company_contact"  lay-verify="required" autocomplete="off" class="layui-input" onchange="nullCheck('company_contact')">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 企业联系电话 </label>
				<div class="layui-input-inline">
					<input type="text" name="tel" id = "company_tel" lay-verify="phone" autocomplete="off" class="layui-input" onchange="telCheck()">
				</div>
			</div>
			<div class="layui-form-item">
				<button class="layui-btn" lay-filter="" lay-submit id="addcompany">增加</button>
			</div>
		</form>
	</div>
	<input type="hidden" id="addstat" value="${requestScope.result}">
	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>
	<script src="lib/layuinew/layui.js"></script>
	<script type="text/javascript" src="js/private/company-add.js"></script>
</body>
</html>