<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
        <meta charset="utf-8">
        <title>
            X-admin v1.0
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="../css/x-admin.css" media="all">
    </head>
	<script type="text/javascript" src="<%=path%>/js/jQuery-2.2.2.js"></script>
	<link rel="stylesheet" href="<%=path%>/lib/layuinew/css/layui.css">
	<style>
body {
	padding: 20px;
}
</style>
<body>
	<div style="line-height:35px">
		<span class="layui-breadcrumb"> 
			<a href="welcome.html">首页</a>
			<a>投诉管理</a> 
			<a><cite>投诉信息</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm" onclick="window.location.reload()" >
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>
	<div style="text-align: center">
		<table class="layui-table" lay-even>
			<thead>
				<tr>
					<th>投诉编号</th>
					<th>出租订单编号</th>
					<th>投诉方</th>
					<th>被投诉方</th>
					<th>投诉时间</th>
					<th>状态</th>
					<th>处理结果</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="x-link">
			</tbody>
		</table>

		
	</div>
	<div id="page"></div>
	<script src="<%=path%>/lib/layuinew/layui.js"></script>
	<script>
		// 加载layui模块
		layui.use('element', function() {
			var element = layui.element; //面包导航
		});
		var cid;
		function showCompalin() {
			$.ajax({
				url : "<%=path%>/showAllComplain.action",
				type : "post",
				dataType : "json",
				data : {},
				success : function(data) {
					var content = "";
					for (var i = 0; i < data.length; i++) {
						cid = data[i].id;
						content += "<tr>"
									+"<td>"+ data[i].id+"</td>"
										+"<td>"+ data[i].deal.no+"</td>"
										+"<td>"+ data[i].complaint.alias+"</td>"
										+"<td>"+ data[i].defendant.alias+"</td>"
										+"<td>"+ data[i].date+"</td>"
										+"<td>"+ showStatus(data[i].status)+"</td>"
										+"<td>"+ data[i].dealreason+"</td>"
                            			+"<td><div onclick='window.location.href=\"/parkingms/showOneComplain.action?id="+cid+"\"'>受理</div></td>"                    				
									+"</tr>"
						$("#x-link").html(content);
					}

				}
			})

		}
		showCompalin();
		function showStatus(status){
			var x;
			switch(status){
			case 0:
			x="<button class='layui-btn layui-btn-xs layui-btn-danger btn-status'>待审核</button>";
			break;
			case 1:
			x="<button class='layui-btn layui-btn-xs layui-btn-warm btn-status'>未通过</button>";
			break;
			case 2:
			x="<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>已通过</button>";
			break;
			}
			return x;
		}
		
		function showOneCompalin(cid) {
			$.ajax({
				url : "<%=path%>/showOneComplain.action",
				type : "post",
				dataType : "json",
				data : {
					id : cid
				},
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						$("#location").html(data[i].location);
						$("#carNumber").html(data[i].carNumber);
						$("#begintime").html(data[i].begintime);
						$("#endtime").html(data[i].endtime);
						$("#alias").html(data[i].alias);
						$("#name").html(data[i].name);
						$("#cardno").html(data[i].cardno);
						$("#career").html(data[i].career);
						$("#tel").html(data[i].tel);
						$("#reason1").html(data[i].reason);
						$("#byalias").html(data[i].byalias);
						$("#byname").html(data[i].byname);
						$("#bycardno").html(data[i].bycardno);
						$("#bycareer").html(data[i].bycareer);
						$("#bytel").html(data[i].bytel);
					}
					window.location.href="complaint.jsp";

				}
			})

		}
	</script>
</body>
</html>
