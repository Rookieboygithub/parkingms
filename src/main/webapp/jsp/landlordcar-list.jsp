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
<meta charset="utf-8">
<base href="<%=basePath%>">
<title>招租车位列表</title>
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
	<div style="line-height:35px">
		<span class="layui-breadcrumb"> <a href="javascript:top.location.reload()">首页</a>
			<a>包租婆管理</a> <a><cite>查看招租车位</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm"
				onclick="window.location.reload()">
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>
	
	<div style="margin: 0 auto">
		<form class="layui-form layui-form-pane">
			<label class="layui-form-label">搜索</label>
			<div class="layui-input-inline">
				<input type="text" placeholder="车位产权证编号" class="layui-input" id='certificateNo'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="车位编号" class="layui-input" id='carNo'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="车位地址" class="layui-input" id='location'>
			</div>
			<div class="layui-input-inline" style="width:165px">
				<select name="status" id='status' lay-filter='*'>
					<option value="">车位状态</option>
					<option value="0">待审核</option>
					<option value="1">已审核</option>
					<option value="2">待出租</option>
					<option value="3">已预约</option>
					<option value="4">已出租</option>
					<option value="5">审核失败</option>
				</select>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="包租婆姓名" class="layui-input" id='landlordname'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="用户名" class="layui-input" id='landlordalias'>
			</div>
			<div class="layui-input-inline">
				<button class="layui-btn" type='button'>
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
		</form>
	</div>
	
	<div style="text-align: center">
		<table class="layui-table" lay-even>
			<thead>
				<tr>
					<td>车位ID</td>
					<td>车位产权证编号</td>
					<td>车位编号</td>
					<td>车位地址</td>
					<td>包租婆姓名</td>
					<td>用户名</td>
					<td>状态</td>
					<td>申请时间</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody id="carlist">
				<c:forEach items="${list}" var="data" varStatus="status">
					<tr>
						<td class='carId'>${data.id}</td>
						<td>${data.certificateNo}</td>
						<td>${data.carNo}</td>
						<td>${data.location}</td>
						<td>${data.landlord.name}</td>
						<td>${data.landlord.alias}</td>
						<td class="td-status">${data.status}</td>
						<td><fmt:formatDate value="${data.time}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class='td-manage'><button
								class='layui-btn layui-btn-warm layui-btn-xs td-button '>
								<i class='layui-icon'>&#xe642;</i>处理
							</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

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
		//记录分页参数
		var currentPage = 1;
		var limitPage = 10;
		//标记是否在执行操作处理
		var flag = 0;
		// 加载layui模块
		layui.use(['form', 'element','layer'], function() {
			var form = layui.form,
				element = layui.element, //面包导航
				layer = layui.layer;
			
			// 监听select选择事件
			form.on('select(*)', function(data){
				search(1,limitPage);
			});
		});
		// 加载layui分页模块，并显示分页栏
		addPage();
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
							showList();
						}
					}
				});
			});
		}
		// 搜索栏文本框内容改变时发送AJAX查询
		$(".layui-form input").change(function(){
			search(1,limitPage);
		})
		// 首次加载时将表格中车位状态栏的数据由对应数字转换为文字描述
		$(".td-status").each(function(){
			var status = $(this).html();
			$(this).html(showStatus(status));
		})
		// 将车位状态数字转换为文字描述
		function showStatus(status){
			var x;
			switch(status){
			case "0":
				x="<button class='layui-btn layui-btn-xs layui-btn-danger btn-status'>待审核</button>";
				break;
			case "1":
				x="<button class='layui-btn layui-btn-xs layui-btn-warm btn-status'>已审核</button>";
				break;
			case "2":
				x="<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>待出租</button>";
				break;
			case "3":
				x="<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>已预约</button>";
				break;
			case "4":
				x="<button class='layui-btn layui-btn-xs layui-btn-normal btn-status'>已出租</button>";
				break;
			case "5":
				x="<button class='layui-btn layui-btn-xs layui-btn-danger btn-status'>审核失败</button>";
				break;
			case "6":
				x="已删除";
				break;
			}
			return x;
		}
		// 分页查询
		function showList() {
			$.ajax({
				url : "<%=path%>/showLandlordCarsByPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : currentPage,
					limit : limitPage,
					status:$("#status").val(),
					certificateNo:$("#certificateNo").val(),
					carNo:$("#carNo").val(),
					location:$("#location").val(),
					"landlord.name":$("#landlordname").val(),
					"landlord.alias":$("#landlordalias").val()
				},
				success : function(result) {
					var data = result.list;
					var str = "";
					if(data.length==0 && flag==0){
						layer.msg('没有符合的车位信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						str += "<tr><td class='carId'>" + data[i].id + "</td>" +
							"<td>" + data[i].certificateNo + "</td>" +
							"<td>" + data[i].carNo + "</td>" +
							"<td>" + data[i].location + "</td>" +
							"<td>" + data[i].landlord.name + "</td>" +
							"<td>" + data[i].landlord.alias + "</td>" +
							"<td class='td-status'>" + showStatus(data[i].status+"") +"</td>" +
							"<td>" + data[i].time +"</td>" +
							"<td class='td-manage'><button class='layui-btn layui-btn-warm layui-btn-xs td-button'><i class='layui-icon'>&#xe642;</i>处理</button></td></tr>";
					}
					$("#carlist").html(str);
					addClick();
					flag = 0;
				}
			});
		}
		// 控制表格操作栏显示
		addClick();
		function addClick(){
			$(".td-button").each(function(){
				var id = $(this).parents("tr").find(".carId").html();
				var status = $(this).parents("tr").find(".btn-status").html();
				if(status != "待审核"){
					$(this).html("<i class='layui-icon'>&#xe63c;</i>详情");
					$(this).addClass("layui-btn-normal");
					$(this).removeClass("layui-btn-warm");
					
				}
				$(this).click(function(){
					layer.open({
            		type: 2,
            		area: ['720px', '600px'],
            		maxmin: true,
           			shadeClose: true,
            		title: '审核车位',
            		content: '<%=path%>/showLandlordCarReview.action?id='+id,
            		end:function(){
            			flag = 1;
            			search(currentPage, limitPage);
            		}
       			 });
				});
			});
		}
		// 点击搜索按钮后的函数
		function search(curr,limit){
			$.ajax({
				url : "<%=path%>/showLandlordCarsByPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : curr,
					limit : limit,
					status:$("#status").val(),
					certificateNo:$("#certificateNo").val(),
					carNo:$("#carNo").val(),
					location:$("#location").val(),
					"landlord.name":$("#landlordname").val(),
					"landlord.alias":$("#landlordalias").val()
				},
				success : function(result) {
					var data = result.list;
					var str = "";
					if(data.length==0 && flag==0){
						layer.msg('没有符合的车位信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						str += "<tr><td class='carId'>" + data[i].id + "</td>" +
							"<td>" + data[i].certificateNo + "</td>" +
							"<td>" + data[i].carNo + "</td>" +
							"<td>" + data[i].location + "</td>" +
							"<td>" + data[i].landlord.name + "</td>" +
							"<td>" + data[i].landlord.alias + "</td>" +
							"<td class='td-status'>" + showStatus(data[i].status+"") +"</td>" +
							"<td>" + data[i].time +"</td>" +
							"<td class='td-manage'><button class='layui-btn layui-btn-warm layui-btn-xs td-button'><i class='layui-icon'>&#xe642;</i>处理</button></td></tr>";
					}
					$("#carlist").html(str);
					addClick();
					flag = 0;
					
					if(result.total != $("#totalPage").val()){
						$("#totalPage").val(result.total);
						currentPage = curr;
						limitPage = limit;
						addPage(); // 重要，当currentPage超过最大页数时，会取最大页数
					}
					if(curr != currentPage){
						showList();
					}
				}
			});	
		}
			
	</script>
</body>
</html>

