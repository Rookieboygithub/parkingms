<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>表单模块 - layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="lib/layuinew/css/layui.css">
<style>
body {
	padding: 20px;
}
#n{
	width: 800px;
	margin-top: 15px;
} 
#m{
	width: 800px;
	margin: 0 auto;
} 
</style>

</head>
<script type="text/javascript" src="js/public/jQuery-2.2.2.js"></script>
<body>
	<div style="line-height: 40px;height: 40px">
		<span class="layui-breadcrumb"> <a href="jsp/welcome.html">首页</a> <a>CBD车位管理</a> <a><cite>查看管辖车位</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm" onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>
	<div id="n"></div>
<form class="layui-form">
		<div id="m">
			<div class="layui-form-item">
				<label class="layui-form-label">车位编号</label>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" autocomplete="off" class="layui-input"
						id="car_no" >
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
						onclick="allNumber()" type="button">
						<i class="layui-icon">&#xe615;</i>
					<tton>
				</div>
			</div>
			</div>
		</form>
	<div style="text-align: center">
		<table class="layui-table">
			<thead>
				<tr>
					<td>编号</td>
					<td>车位地址</td>
					<td>车位编号</td>
					<td>车位状态</td>
					<td>删除</td>
				</tr>
			</thead>
			<thead id="company_id">

			</thead>
			</tbody>
		</table>
	</div>
	<!-- 当前页数显示条数 -->
	<input type="hidden" id="limit">
	<!--当前页码  -->
	<input type="hidden" id="pageAt">
	<!--分页  -->
	<div id="page" style='margin-bottom:20px'></div>
	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>
	<script src="lib/layuinew/layui.js"></script>
	<script src="js/private/showCbdcarByPage.js"></script>
</body>
</html>
