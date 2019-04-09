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
	<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label for="username" class="layui-form-label"> 用户名 </label>
				<div class="layui-input-inline">
					<input type="text" id="username" name="username" required=""
						lay-verify="required" autocomplete="off" class="layui-input"
						disabled="" value="${username}">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="name" class="layui-form-label"> 真实姓名 </label>
				<div class="layui-input-inline">
					<input type="text" id="name" name="name" required=""
						lay-verify="name" autocomplete="off" class="layui-input"
						disabled="">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="phone" class="layui-form-label"> 信用值 </label>
				<div class="layui-input-inline">
					<input type="text" id="credit" name="crdit" required=""
						lay-verify="" autocomplete="off" class="layui-input" disabled="">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="phone" class="layui-form-label"> 电话 </label>
				<div class="layui-input-inline">
					<input type="text" id="phone" name="phone" required=""
						lay-verify="phone" autocomplete="off" class="layui-input"
						disabled="" onblur="check_tel()">
				</div>
				<div class="layui-form-mid layui-word-aux" id="tip1"></div>
			</div>
			<div class="layui-form-item">
				<label for="phone" class="layui-form-label"> 地址 </label>
				<div class="layui-input-inline">
					<input type="text" id="addr" name="phone" required="" lay-verify=""
						autocomplete="off" class="layui-input" disabled="">
				</div>
				<div class="layui-form-mid layui-word-aux" id="tip2"></div>
			</div>

			<div class="layui-form-item">
				<label for="phone" class="layui-form-label"> 邮箱</label>
				<div class="layui-input-inline">
					<input type="text" id="email" name="" required="" lay-verify=""
						autocomplete="off" class="layui-input" disabled=""
						onblur="check_email()">
				</div>
				<div class="layui-form-mid layui-word-aux" id="tip3"></div>
			</div>



			<div class="layui-form-item">
				<label class="layui-form-label">职业</label>
				<div class="layui-input-inline">
					<input type="text" id="career" class="layui-input" disabled="">
				</div>
			</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button type="button" class="layui-btn layui-btn-primary"
				onclick="mod()">修改</button>
			<button class="layui-btn layui-btn-normal" type="button" onclick="update()"
				lay-filter="">立即提交</button>
		</div>
	</div>
		
	</form>
		
	</div>

	<script src="<%=path%>/js/jQuery-2.2.2.js"></script>
	<script src="${pageContext.request.contextPath}/lib/layui/layui.js"
		charset="utf-8">
        </script>
	<script src="${pageContext.request.contextPath}/js/x-layui.js"
		charset="utf-8">
        </script>
	<script>
		layui.use([ 'form', 'layer' ], function() {
			$ = layui.jquery;
			var form = layui.form(),
				layer = layui.layer;
	
			//自定义验证规则
			form.verify({
				nikename : function(value) {
					if (value.length < 5) {
						return '昵称至少得5个字符啊';
					}
				},
				pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
				repass : function(value) {
					if ($('#L_pass').val() != $('#L_repass').val()) {
						return '两次密码不一致';
					}
				}
			});
	
			//监听提交
			form.on('submit(add)', function(data) {
				console.log(data);
				//发异步，把数据提交给php
				layer.alert("增加成功", {
					icon : 6
				}, function() {
					// 获得frame索引
					var index = parent.layer.getFrameIndex(window.name);
					//关闭当前frame
					parent.layer.close(index);
				});
				return false;
			});
	
	
		});
	</script>
	<script>
		$(function() {
			$.ajax({
				url : "../finduser.action",
				type : "post",
				dataType : "json",
				data : {
				},
				success : function(data) {
					$("#name").val(data.name);
					$("#addr").val(data.addr);
					$("#phone").val(data.tel);
					$("#email").val(data.email);
					$("#credit").val((100 - data.credit * 100));
					$("#career").val(data.career);
				}
			})
		})
		function mod() {
			document.getElementById("phone").disabled = false;
			document.getElementById("addr").disabled = false;
			document.getElementById("career").disabled = false;
			document.getElementById("email").disabled = false;
		}
		function update() {
			if (check_tel() && check_email()) {
				$.ajax({
					url : "../updateMes.action",
					type : "post",
					dataType : "json",
					data : {
						addr : $("#addr").val(),
						tel : $("#phone").val(),
						career : $("#career").val(),
						email : $("#email").val()
					},
					success : function(data) {
						if (data.judge == "success") {
							layer.msg('操作成功', {
								icon : 6,
								time : 2000
							});
						}
					}
				})
			}
		}
		/* 进行数据验证 */
		function check_addr() {
		}
		function check_tel() {
			var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
			var tel = $("#phone").val();
			if (!reg.test(tel)) {
				$("#tip1").html("<span style='color:red;'>请输入正确的手机号码</span>");
				return false;
			} else {
				$("#tip1").html("");
				return true;
			}
		}
		function check_email() {
			var reg = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
			var email = $("#email").val();
			if (!reg.test(email)) {
				$("#tip3").html("<span style='color:red;'>请输入正确的邮箱</span>");
				return false;
			} else {
				$("#tip3").html("");
				return true;
			}
		}
	</script>
</body>

</html>
