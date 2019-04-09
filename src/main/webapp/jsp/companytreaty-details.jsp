<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>租户合约详情 - layui</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="./lib/layuinew/css/layui.css" >
<script src="./js/jQuery-2.2.2.js"></script>
<style>
body {
	padding: 20px;
}

.layui-form-label {
	width: 150px;
}

.x-nav {
	padding: 0 20px;
	position: relative;
	z-index: 99;
	border-bottom: 1px solid #e5e5e5;
	line-height: 39px;
	height: 39px;
	overflow: hidden;
	margin-bottom: 10px;
}
</style>
</head>

<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>租户合约管理</cite></a>
			<a><cite>租户合约详情</cite></a>
		</span>
	</div>
	<div>
		<form class="layui-form">
			<input type="hidden" id="cid" value="${param.id}">
			<input type="hidden" id="path" value="<%=basePath%>">
			<div class="layui-form-item">
				<label class="layui-form-label"> 企业用户名称 </label>
				<div class="layui-input-inline">
					<input type="text" id="companyname" autocomplete="off" class="layui-input" disabled>
				</div>
				<label class="layui-form-label"> 合同编号 </label>
				<div class="layui-input-inline">
					<input type="text" id="newno" name="" required
						lay-verify="required" autocomplete="off" class="layui-input" disabled>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 对方联系人 </label>
				<div class="layui-input-inline">
					<input type="text" id="contact" autocomplete="off" class="layui-input" disabled>
				</div>
				<label class="layui-form-label"> 对方联系电话 </label>
				<div class="layui-input-inline">
					<input type="text" id="tel" autocomplete="off" class="layui-input" disabled>
					<span id="tel_msg" style="color: red;font-size: 12px"></span>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 车位编号</label>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" autocomplete="off" class="layui-input" disabled
						id="car_no_min" lay-verify="required">
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" autocomplete="off" class="layui-input" disabled
						id="car_no_max">
				</div>
				<input type="hidden" id="car_no">
				<div class="layui-form-mid layui-word-aux">若只有一个车位，第二个输入框不填</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 车位详细地址 </label>
				<div class="layui-input-inline">
					<input type="text" id="car_addr" autocomplete="off" class="layui-input" disabled>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 合同生效日期 </label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" disabled id="beginTime" lay-verify="required">
				</div>
				<label class="layui-form-label"> 合同截止日期 </label>
				<div class="layui-inline">
					<input type="text" class="layui-input" disabled id="endTime" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 合同复印件 </label>
				<div class="layui-input-inline">
					<!--预览区域-->
					<div class="layui-upload-list">
						<img class="layui-upload-img" width="200px" height="180px" id="demo1" />
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> </label>
				<button class="layui-btn" type="button" onclick="admin_renew('续约','jsp/companytreaty-renew.html',${param.id} ,'900','630')">续约</button>
				<label class="layui-form-label"> </label>
				<button class="layui-btn" style="margin-left: 60px;" type="button"
					onclick="removeComTreaty()">解约</button>
			</div>
		</form>
	</div>
	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>
	<script src="./lib/layuinew/layui.js"></script>
	<script src="./js/companytreaty-details.js"></script>
</body>
</html>
