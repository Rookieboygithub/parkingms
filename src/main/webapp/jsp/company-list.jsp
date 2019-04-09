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
</style>
</head>
<script type="text/javascript" src="js/public/jQuery-2.2.2.js"></script>
<body>
	<div style="line-height: 40px;height: 40px;margin-bottom: 35px">
		<span class="layui-breadcrumb"> <a href="jsp/welcome.html">首页</a> <a>企业用户管理</a> <a><cite>企业用户管理</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm" onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>


	<div class="layui-form-item">
		<label class="layui-form-label"> 企业名称 </label>
		<div class="layui-input-inline">
			<input type="text" id="company_name" name="name" autocomplete="on" class="layui-input">
		</div>
		<label class="layui-form-label"> 企业地址 </label>
		<div class="layui-input-inline">
			<input type="text" id="company_addr" name="addr" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"> 双方联系人 </label>
		<div class="layui-input-inline">
			<input type="text" id="company_contact" name="contact" autocomplete="off" class="layui-input">
		</div>
		<label class="layui-form-label"> 联系电话 </label>
		<div class="layui-input-inline">
			<input type="text" id="company_tel" name="tel" autocomplete="off" class="layui-input">
		</div>
		<div>
			<label for="L_repass" class="layui-form-label"> </label>
			<button class="layui-btn" onclick="company_select()">查询</button>
		</div>
	</div>

	<div style="text-align: center">
		<table class="layui-table" lay-even>
			<thead>
				<tr>
					<td>编号</td>
					<td>企业登录名</td>
					<td>企业名称</td>
					<td>企业楼层位置</td>
					<td>企业联系人</td>
					<td>企业联系电话</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody id="company_id">
			</tbody>
		</table>
	</div>
	<!-- 总页数 -->
	<input type="hidden" value="${sessionScope.number}" id="pageAll">
	<!-- 当前页数显示条数 -->
	<input type="hidden" id="limit">
	<!--当前页码  -->
	<input type="hidden" id="pageAt">
	<!--分页  -->
	<div id="page"></div>
	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>
	<script src="lib/layuinew/layui.js"></script>
	<script src="js/private/company-list.js"></script>
</body>
</html>
