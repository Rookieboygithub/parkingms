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
<title>企业合同</title>
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
	<div class="x-body">
		<table class="layui-table">
			<thead>
				<tr>
					<th>合同编号</th>
					<th>企业名称</th>
					<th>双方联系人</th>
					<th>联系人电话</th>
					<th>合同生效日期</th>
					<th>合同结束日期</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody id="x-link">

			</tbody>
		</table>
		<div id="page"></div>
	</div>
	<input type="hidden" id="treaty_id" value="${param.id}">
	<input type="hidden" id="allNumber" value="${sessionScope.streatyNumber }">
	<input type="hidden" id="pageAt">
	<input type="hidden" id="limit">
	<script src="lib/layuinew/layui.js"></script>
	<script type="text/javascript" src="js/private/company-cbdcar-treaty.js"></script>
</body>
</html>