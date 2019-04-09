<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>日志记录</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
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
<body>
	
	<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-normal"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
	</div>
	
	<div style="margin: 0 auto">
		<form class="layui-form layui-form-pane">
			<label class="layui-form-label">搜索</label>
			<div class="layui-input-inline">
				<input type="text" placeholder="操作" class="layui-input" id='method'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="时间" class="layui-input" id='time'>
			</div>
			<div class="layui-input-inline">
				<select name="status" id='type' lay-filter='*'>
					<option value="">日志类型</option>
					<option value="0">登录日志</option>
					<option value="1">操作日志</option>
				</select>
			</div>
			<div class="layui-input-inline">
				<button class="layui-btn layui-btn-normal" type='button' >
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</form>
	</div>
	
	<div style="text-align: center">
		<table class="layui-table" lay-even>
			<thead>
				<tr>
					<td>序号</td>
					<td>用户名</td>
					<td>模块</td>
					<td>操作</td>
					<td>日志类型</td>
					<td>时间</td>
					<td>IP</td>
				</tr>
			</thead>
			<tbody id="loglist">
				
			</tbody>
		</table>
	</div>
	<input type="hidden" id="userId" value="${sessionScope.userId}">
	<input type="hidden" id="userRole" value="${sessionScope.userCharacter}">
	<input type="hidden" id="username" value="${sessionScope.username}">
	<input type="hidden" id="totalPage" value="${total}">
	<div id="page" style='margin-bottom:20px'></div>
	<!-- 底部固定区域 -->
	<div class="layui-layout-admin">
		<div class="layui-footer" style="left:0;text-align: center;">
			<p>Copyright ©2018 CBD车位管理系统 v1.0 All Rights Reserved.</p>
		</div>
	</div>
	<script src="lib/layuinew/layui.js"></script>
	<script src="js/jQuery-2.2.2.js"></script>
	<script>
		// 记录分页参数
		var currentPage=1;
		var limitPage = 10;
		// 搜索栏文本框失去焦点时发送AJAX查询
		$(".layui-form input").change(function(){
			currentPage=1;
			showList();
		})
		// 加载layui模块
		layui.use(['form','element', 'layer','laydate' ], function() {
			var form = layui.form,
				element = layui.element, //面包导航
				layer = layui.layer,
				laydate = layui.laydate;
				
				// 监听select选择事件
				form.on('select(*)', function(data){
					currentPage=1;
					showList();
				});   
				
				//执行一个laydate实例
				var start = laydate.render({
					elem : '#time', //指定元素
					type : 'date',
					done : function(value,date) {
						currentPage=1;
						showList();
				}
			});
		});
		// 将layui分页封装成一个函数
		function addPage() {
			layui.use('laypage', function() {
				var laypage = layui.laypage;
				//执行一个laypage实例
				laypage.render({
					elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
					count : $("#totalPage").val(), //数据总数，从服务端得到
					limit : limitPage, //每页显示的条数
					curr : currentPage,
					limits : [ 5, 10, 15 ],
					layout : [ 'prev', 'page', 'next', 'count', 'limit', 'skip' ],
					jump : function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						//obj.curr得到当前页，以便向服务端请求对应页的数据。
						//obj.limit得到每页显示的条数
						currentPage = obj.curr;
						limitPage = obj.limit;
						//首次不执行
						if (!first) {
							showList(obj.curr, obj.limit);
						}
					}
				});
			});
		}
		showList();
		// 根据请求的页数curr及每页显示数量limit发送AJAX请求显示对应页信息
		function showList() {
			$.ajax({
				url : "<%=path%>/showLogByPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : currentPage,
					limit : limitPage,
					username:$("#username").val(),
					type:$("#type").val(),
					time:$("#time").val(),
					method:$("#method").val()
				},
				success : function(result) {
					var data= result.list;
					var string = "";
					if(data.length==0 ){
						layer.msg('没有符合的日志信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						string += "<tr><td>" + ((currentPage-1)*limitPage+i+1) + "</td>" +
							"<td>" + data[i].username + "</td>" +
							"<td>" + data[i].module + "</td>" +
							"<td>" + data[i].method + "</td>" +
							"<td>" + checkLogType(data[i].type) + "</td>" +
							"<td>" + data[i].time + "</td>" +
							"<td>" + data[i].ip + "</td>";
					}
					$("#loglist").html(string);
					if($("#totalPage").val()==''||$("#totalPage").val()!=result.total){
						$("#totalPage").val(result.total);
						addPage();
					}
				}
			});
		}
		// 判断日志类型
		function checkLogType(type){
			var str;
			if(type==0){
				str="登录";
			}else{
				str="操作";
			}
			return str;
		}
	</script>
</body>
</html>