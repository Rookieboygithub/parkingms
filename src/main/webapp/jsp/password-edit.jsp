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
				<label class="layui-form-label"> 管理员工号 </label>
				<div class="layui-input-inline">
					<input type="text" id="account" name="account" disabled=""
						value="${username}" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> <span class="x-red">*</span>旧密码
				</label>
				<div class="layui-input-inline">
					<input type="password" id="oldPwd" name="pwd" required="" value=""
						lay-verify="required" autocomplete="off" onblur="tip0()" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux" id="tip0"></div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> <span class="x-red">*</span>新密码
				</label>
				<div class="layui-input-inline">
					<input type="password" id="pwd" name="newpwd" required=""
						lay-verify="pass" autocomplete="off" class="layui-input"
						onblur="reg1()">
				</div>
				<div class="layui-form-mid layui-word-aux" id="tip1">6到12个字符</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> <span class="x-red">*</span>确认新密码
				</label>
				<div class="layui-input-inline">
					<input type="password" id="L_repass" name="repass" required=""
						lay-verify="required" autocomplete="off" class="layui-input"
						onblur="reg2()">
				</div>
				<div class="layui-form-mid layui-word-aux" id="tip2">6到12个字符</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<button class="layui-btn" type="button" onclick="sub()">修改</button>
			</div>
		</form>
	</div>
	<script src="${pageContext.request.contextPath}/lib/layui/layui.js"
		charset="utf-8">
        </script>
	<script src="${pageContext.request.contextPath}/js/x-layui.js"
		charset="utf-8">
        </script>
	<script src="${pageContext.request.contextPath}/js/jQuery-2.2.2.js"></script>
	<script src="${pageContext.request.contextPath}/js/mobile/layer.js"></script>
	<script type="text/javascript">
		layui.use([ 'layer' ], function() {
			$ = layui.jquery;
			var form = layui.form(),
				layer = layui.layer;
		});
		function reg1() {
			var reg = /^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)\S{6,16}$/;
			var val1 = $("#pwd").val();
			
			if (!reg.test(val1)) {
				$("#tip1").html("<span style='color:red'>请输入6-12个字符(必须包含字母和数字),不包括中文</span>");
				return false;
			} else {
				$("#tip1").html("");
				return true;
			}
		}
		;
		function reg2() {
			var val1 = $("#pwd").val();
			var val2 = $("#L_repass").val();
			if (val1 != val2) {
				$("#tip2").html("<span style='color:red'>两次输入密码不一致，请重新输入</span>");
				return false;
			} else {
				$("#tip2").html("");
				return true;
			}
		}
		function tip0() {
			$.ajax({
				url : "../verifyPwd.action",
				type : "post",
				dataType : "json",
				data : {
					pwd : $("#oldPwd").val(),
				},
				success : function(data) {
				if(data.tips=="原密码不正确"||data.tips==null){
					$("#tip0").html("<span style='color:red'>" + data.tips+ "</span>")
					}else{
					$("#tip0").html("<span style='color:red'>" + "验证通过" + "</span>")
					}
				}
			})
	
		}
	
		function sub() {
			if (reg1() && reg2()) {
				$.ajax({
					url:"../updatePwd.action",
					type:"post",
					dataType:"json",
					data:{
					    account:$("#account").val(),
						pwd:$("#oldPwd").val(),
						newPwd:$("#pwd").val()
					},
					success : function(data) {
					if(data.tips=="成功"){
						layer.msg('密码修改成功', {icon: 6, time: 2000});
						setTimeout(function(){
							window.location.reload();
						}, 2000);
					}else{
					alert("修改失败");
					}
					}
				})
			}
		}
	</script>

</body>

</html>
