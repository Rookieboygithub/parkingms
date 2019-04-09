<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>平台车位信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="../css/layui.css" media="all">
<script type="text/javascript" src="../js/private/layui.all.js"></script>
</head>

<body>
	
	<!-- 导航栏 -->
	<div style="line-height:35px">
		<span class="layui-breadcrumb"> <a href="jsp/welcome.html">首页</a>
			<a>平台车位</a> <a><cite>车位信息</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>

	<dev>
		<table id="demo" lay-filter="test"></table>
	</dev>

	<!-- 底部固定区域 -->
	<!-- <div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div> -->

	<script>
		layui.use('table', function() {
			var table = layui.table;
	
			//第一个实例
			table.render({
				elem : '#demo',
				url : '/parkingms/showCbdcarNotLease.action', //数据接口
				height : 580,
				page : true, //开启分页
				cols : [ [ //表头
					{
						field : 'id',
						title : 'ID',
						sort : true,
						width: 300,
						fixed : 'left'
					}
					, {
						field : 'no',
						width: 300,
						title : '车位编号'
					}
					, {
						field : 'addr',
						title : '地址',
						width : 300
					}
					, {
						field : 'maxtime',
						title : '最长可租时间',
						width: 300
					}
				]]
			});
		});
	</script>
</body>
</html>
