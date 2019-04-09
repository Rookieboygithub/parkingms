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

<title>My JSP 'outtreaty-renew.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet"
	href="<%=basePath%>/lib/layuinew/css/layui.css">
	<script type="text/javascript" src="<%=basePath %>/js/jQuery-2.2.2.js"></script>

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
		<span class="layui-breadcrumb"> <a href="<%=basePath %>/jsp/index.jsp"><cite>首页</cite></a> <a href="<%=basePath %>/jsp/outtreaty-list.jsp"><cite>外部合约管理</cite></a>
			<a href="<%=basePath %>/jsp/outtreaty-renew.jsp"><cite>续约外部合约</cite></a>
		</span>
	</div>
	<div>
		<form class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label"> 原合同编号 </label>
				<div class="layui-input-inline">
					<input type="text" id="" name="" required="" lay-verify="required"
						autocomplete="off" class="layui-input"
						value="<%=request.getParameter("no")%>">
				</div>
				<label class="layui-form-label"> 新续合同编号 </label>
				<div class="layui-input-inline">
					<input type="text" id="newno" name="" required="" lay-verify="required"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 合同单位 </label>
				<div class="layui-input-inline">
					<input type="text" id="company" name="" required=""
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 双方联系人 </label>
				<div class="layui-input-inline">
					<input type="text" id="contact" name="" required=""
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
				<label class="layui-form-label"> 双方联系电话 </label>
				<div class="layui-input-inline">
					<input type="text" id="tel" name="" required=""
						lay-verify="phone" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> 合同生效日期 </label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="beginTime">
				</div>
				<label class="layui-form-label"> 合同截止日期 </label>
				<div class="layui-inline">
					<input type="text" class="layui-input" id="endTime">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"> </label>
				<button class="layui-btn" lay-filter="" lay-submit="" onclick="commit()">增加</button>
			</div>
		</form>
	</div>

	<div id="id" style="display: none;"><%=request.getParameter("id")%></div>

	<script src="<%=basePath%>/lib/layuinew/layui.js"></script>
	<script>
	
		layui.use([ 'form', 'element' ], function() {
			var form = layui.form,
				element = layui.element; //面包导航
		});
	</script>
	<script>
		layui.use('laydate', function() {
			var laydate = layui.laydate;
	
			//执行一个laydate实例
			laydate.render({
				elem : '#beginTime', //指定元素
				type : 'datetime',
				min:0,
				calendar : true,
			
			});
	
			laydate.render({
				elem : '#endTime', //指定元素
				type : 'datetime',
				min:0,
				calendar : true,
				
			});
		});
	</script>
	<script type="text/javascript">
		function commit() {
			$.ajax({
				url : "<%=basePath%>/change.action",
				method : "post",
				dataType : 'json',
				async : true,
				data : {
					id : $("#id").html(),
					newno : $("#newno").val(),
					company : $("#company").val(),
					contact : $("#contact").val(),
					tel : $("#tel").val(),
					endtime : $("#endTime").val(),
					begintime : $("#beginTime").val()
				},
				success : function(data) {
				 alert(data.mes);
				 if("修改成功"==data.mes){
				 window.location.href="jsp/welcome.html";
				 }
				
				},
				error : function(message){
				alert("error:"+message);
				alert("修改失败，请重试！");
				}
			})
	
		}
	</script>
</body>
</html>
