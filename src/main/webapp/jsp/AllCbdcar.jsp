<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>平台车位信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="../css/layui.css" media="all">

</head>

<body>

	<!-- 导航栏 -->
	<div style="line-height:35px">
		<span class="layui-breadcrumb"> <a href="jsp/welcome.html">首页</a>
			<a>平台车位</a> <a><cite>车位信息</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>

	<div>
		<form class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">车位编号</label>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" autocomplete="off" class="layui-input"
						id="car_no_min" lay-verify="required">
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" autocomplete="off" class="layui-input"
						id="car_no_max">
				</div>
				<input type="hidden" id="car_no"> <label
					class="layui-form-label">车位地址</label>
				<div class="layui-input-inline">
					<input type="text" id="car_addr" placeholder="请输入查询地址"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-input-inline" style="width: 50px;height: 20px"></div>
				<div class="layui-input-inline" style="width:80px">
					<button class="layui-btn" lay-submit="" lay-filter="sreach"
						onclick="sreach()">
						<i class="layui-icon">&#xe615;</i>
					</button>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">车位状态</label>
				<div class="layui-input-block">
					<input type="checkbox" name="yes" title="已出租" checked=""> 
					<input type="checkbox" name="no" title="未出租" checked="">
				</div>
			</div>
		</form>
		<table id="demo" lay-filter="test"></table>
	</div>

	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>

	<script src="../js/private/layui.all.js"></script>
	<script src="../js/sreachCar.js"></script>
</body>
</html>
