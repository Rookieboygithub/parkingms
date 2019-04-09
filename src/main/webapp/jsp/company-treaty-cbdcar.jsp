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
<title>企业车位</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="lib/layuinew/css/layui.css">
</head>
<script type="text/javascript" src="js/public/jQuery-2.2.2.js"></script>
<body>

	<div style="line-height: 10px;height: 10px;margin-bottom: 35px">
		<span class="layui-breadcrumb"> </span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm" onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"> 车位编号 </label>
		<div class="layui-input-inline">
			<input type="text" id="cbdcar_no" name="name" autocomplete="on" class="layui-input">
		</div>
		<label class="layui-form-label"> 车位地址 </label>
		<div class="layui-input-inline">
			<input type="text" id="cbdcar_addr" name="addr" autocomplete="off" class="layui-input">
		</div>
		<div>
			<label for="L_repass" class="layui-form-label"> </label>
			<button class="layui-btn" onclick="pageingAjax(1, 5)">查询</button>
		</div>
	</div>
	<div class="x-body">
		<table class="layui-table" lay-even>
			<thead>
				<tr>
					<th>车位编号</th>
					<th>车位地址</th>
				</tr>
			</thead>
			<tbody id="x-link">
			</tbody>
		</table>
		<div id="page"></div>
	</div>
	<input type="hidden" id="treaty_id" value="${param.id}">
	<input type="hidden" id="pageAt">
	<input type="hidden" id="limit">
	<script src="lib/layuinew/layui.js"></script>

	<script type="text/javascript" src="js/private/company-treaty-cbdcar.js"></script>
</body>
</html>