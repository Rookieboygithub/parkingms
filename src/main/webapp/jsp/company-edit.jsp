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
<link rel="stylesheet" href="../css/x-admin.css" media="all">
</head>

<body>
	<div class="x-body">
		<form class="layui-form">
			<div class="layui-form-item">
				<label for="username" class="layui-form-label"> 企业名称 </label>
				<div class="layui-input-inline">
					<input type="text" id="cname" class="layui-input" disabled=""
						value="">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="username" class="layui-form-label"> 企业账号 </label>
				<div class="layui-input-inline">
					<input type="text" id="account" class="layui-input" disabled=""
						value="${username}">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="name" class="layui-form-label"> 企业地址 </label>
				<div class="layui-input-inline">
					<input type="text" id="addr" class="layui-input" disabled=""
						value="">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="phone" class="layui-form-label"> 企业联系人 </label>
				<div class="layui-input-inline">
					<input type="text" id="contact" class="layui-input" disabled=""
						value="" onblur="check2()">
				</div>
				<div class="layui-input-inline">
					<button class="layui-btn" type="button" onclick="mod()">修改</button>
				</div>
				<div class="layui-form-mid layui-word-aux" id="tip0"></div>
			</div>
			<div class="layui-form-item">
				<label for="phone" class="layui-form-label"> 联系人电话 </label>
				<div class="layui-input-inline">
					<input type="text" id="tel" class="layui-input" disabled=""
						onblur="check1()" value="" >
				</div>
				<div class="layui-input-inline">
					<button class="layui-btn" type="button" onclick="modTel()">修改</button>
				</div>
				<div class="layui-form-mid layui-word-aux" id="tip1"></div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<button class="layui-btn" type="button" onclick="sub()">提交</button>
			</div>
		</form>
	</div>
	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
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
		$(function() {
			$.ajax({
				url : "../CompanyMessage.action",
				type : "post",
				dataType : "json",
				data : {
					account : $("#account").val()
				},
				success : function(data) {
					$("#cname").val(data.name);
					$("#addr").val(data.addr);
					$("#contact").val(data.contact);
					$("#tel").val(data.tel)
				}
			})
		})
		function mod() {
			document.getElementById("contact").disabled = false;
	
		}
		function modTel() {
			document.getElementById("tel").disabled = false;
		}
	
		/*      校验表单元素 */
		function check1() {
			var tel = $("#tel").val();
			var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
			if (!reg.test(tel)) {
				$("#tip1").html("<span style='color:red'>请重新输入一个正确的电话号码</span>");
				return false;
			} else {
				$("#tip1").html("");
				return true;
			}
		}
		function check2(){
		var contact=$('#contact').val();
		alert(contact);
		if(contact.length<2){
		$('#tip0').html("<span style='color:red'>您输入的联系人不正确</span>");
		return false;
		}else{
		$('#tip0').html("");
		return true;
		}
		}
		function sub() {
			if (check1()&&check2()){
				$.ajax({
					url : "../CompanyModify.action",
					type : "post",
					dataType : "json",
					data : {
						account : $("#account").val(),
						contact : $("#contact").val(),
						tel : $("#tel").val()
					},
					success : function(data) {
						if (data.rs == "success") {
							window.location.href = "${pageContext.request.contextPath}/jsp/success.jsp"
						}
					}
				})
			}
	
		}
	</script>
</body>