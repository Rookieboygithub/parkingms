
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta charset="utf-8">
<title>X-admin v1.0</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/x-admin.css" media="all">
</head>

<body>
	<div class="x-body">
		<form class="layui-form">
			<div class="layui-form-item">
				<label for="username" class="layui-form-label"> 工号 </label>
				<div class="layui-input-inline">
					<input type="text" id="account" class="layui-input" disabled=""
						value="">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="name" class="layui-form-label"> 姓名 </label>
				<div class="layui-input-inline">
					<input type="text" id="name" class="layui-input" disabled=""
						value="S001">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="phone" class="layui-form-label"> 手机号 </label>
				<div class="layui-input-inline">
					<input type="text" id="phone" class="layui-input" disabled=""
						value="" >
				</div>
				

			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 权限管理 </label>
				<div class="layui-form-mid">用户管理权限</div>
				<input type="checkbox" id="user" name="" lay-skin="switch"
					lay-text="开启|关闭">
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<div class="layui-form-mid">车位管理权限</div>
				<input type="checkbox" name="" id="car" lay-skin="switch"
					lay-text="开启|关闭">
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<div class="layui-form-mid">合同管理权限</div>
				<input type="checkbox" name="" id="pact" lay-skin="switch"
					lay-text="开启|关闭">
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<div class="layui-form-mid">投诉管理权限</div>
				<input type="checkbox" name="" id="complain" lay-skin="switch"
					lay-text="开启|关闭">
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn"  type="button"
					onclick="sub()">提交</button>
			</div>
	</div>
	 <input type="hidden" id="myid" value="${param.id}">
	</form>
	</div>
	<script src="${pageContext.request.contextPath}/lib/layui/layui.js"
		charset="utf-8">
        </script>
	<script src="${pageContext.request.contextPath}/js/x-layui.js"
		charset="utf-8">
        </script>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"
		charset="utf-8">
        </script>
	<script>
		layui.use([ 'form', 'layer' ], function() {
			$ = layui.jquery;
			var form = layui.form(),
				layer = layui.layer;
			form.render(); //渲染页面
	
		});
		$(function() {

			$.ajax({
				url : "../findone.action",
				type : "post",
				dataType : "json",
				async: false,
				data : {
					id : $("#myid").val()
				},
				success : function(data) {
					$("#name").val(data.name);
					$("#phone").val(data.tel);
					$("#account").val(data.account);
					if (data.authorityCar != 1) {
						$("#car").prop('checked', false);
					} else {
						$("#car").prop('checked', true);
					}
					if (data.authorityComplain != 1) {
						$("#complain").prop('checked', false);
					} else {
						$("#complain").prop('checked', true);
					}
					if (data.authorityUser != 1) {
						$("#user").prop('checked', false);
					} else {
						$("#user").prop('checked', true);
					}
					if (data.authorityPact != 1) {
						$("#pact").prop('checked', false);
					} else {
						$("#pact").prop('checked', true);
					};
	
					layui.use('form', function() {
						var form = layui.form;
						form.render('checkbox');
	
					});
	
				}
			})
	
	
		})
	
		
		function sub() {
			
				var user = 0;
				var pact = 0;
				var complain = 0;
				var car = 0;
				if ($("#car").prop('checked')) {
					car = 1;
				}
				if ($("#pact").prop('checked')) {
					pact = 1;
				}
				if ($("#user").prop('checked')) {
					user = 1;
				}
				if ($("#complain").prop('checked')) {
					complain = 1;
				}
				$.ajax({
					url : "../updateAdmin.action",
					type : "post",
					dataType : "json",
					data : {
					    id : $("#myid").val(),
						authorityCar:car,
						authorityPact:pact,
						authorityUser:user,
						authorityComplain:complain,
					},
					success : function(data) {
						if (data.judge=="success") {
							window.location.href = "${pageContext.request.contextPath}/jsp/success.jsp"
						}
					}
				})
			}
	
		
	</script>
</body>