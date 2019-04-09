<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>添加自营车位</title>
<link rel="stylesheet" href="../lib/layuinew/css/layui.css">
<script src="../js/jQuery-2.2.2.js"></script>
<style>
body {
	padding: 20px;
}

.x-nav {
	padding: 0 20px;
	position: relative;
	z-index: 99;
	border-bottom: 1px solid #e5e5e5;
	line-height: 39px;
	height: 39px;
	overflow: hidden;
}
</style>
</head>
<body>
	<div style="line-height:35px">
		<span class="layui-breadcrumb"> <a href="welcome.html">首页</a>
			<a>CBD车位管理</a> <a><cite>添加自营车位</cite></a>
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
				<label class="layui-form-label"> 车位编号</label>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" autocomplete="off" class="layui-input"
						id="car_no_min" lay-verify="required">
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" autocomplete="off" class="layui-input"
						id="car_no_max">
				</div>
				<input type="hidden" id="car_no">
				<div class="layui-form-mid layui-word-aux">若只有一个车位，第二个输入框不填</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 车位详细地址 </label>
				<div class="layui-input-inline">
					<input type="text" id="car_addr" name="" required=""
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
			</div>
			<br>
			<div class="layui-form-item">
				<label class="layui-form-label"> </label>
				<button class="layui-btn" lay-filter="" lay-submit="" type="button"
					onclick="login()">增加</button>
			</div>
			<div id="result"></div>
			<div id="b"></div>
		</form>
	</div>
	
		<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div><script src="../lib/layuinew/layui.js"></script>
	<script src="../js/companytreaty-add.js"></script>
	<script>
		function login() {
			var js = {
				"addr" : $("#car_addr").val(),
				"no" : $("#car_no").val()
			};
			$.ajax({
				url : "/parkingms/insertCbdcar.action",
				method : "post",
				dataType : 'json',
				/* contentType:"application/json", */
				/*将数据转换成json格式传到后台，如果步转换格式且在接受参数那加上requestbody将会报415的错  */
				/* 	data:JSON.stringify(js), */
				data : js,
				async : true,
				success : function(message) {
					alert(message),
					window.location.href = ""
				}
			});
		}
	</script>
</body>
</html>