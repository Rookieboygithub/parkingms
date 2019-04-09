<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>投诉页面</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" href="../lib/layuinew/css/layui.css">

<style>
body {
	padding: 20px;
}
</style>
</head>
<body>
	<!--导航栏-->
	<div style="line-height:35px">
		<span class="layui-breadcrumb"> <a href="jsp/welcome.html">首页</a>
			<a>包租婆管理</a> <a><cite>我要投诉</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label"> 交易编号 </label>
			<div class="layui-input-inline">
				<input type="text" id="dealNo" class="layui-input" disabled>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 投诉方 </label>
			<div class="layui-input-inline">
				<input type="text" id="complainant" class="layui-input" disabled>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 被投诉方 </label>
			<div class="layui-input-inline">
				<input type="text" id="defendant" class="layui-input" disabled>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 投诉理由 </label>
			<div class="layui-input-inline" style="width: 380px">
				<textarea id="reason" class="layui-input"
					style="height: 100px;resize: none"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> </label>
			<button class="layui-btn" type="button">提交</button>
		</div>
	</form>
	<input type="hidden" id="userId" value="${sessionScope.userId}">
	<input type="hidden" id="userRole" value="${sessionScope.userRole}">
	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>
	<script src="../lib/layuinew/layui.js"></script>
	<script>
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>

