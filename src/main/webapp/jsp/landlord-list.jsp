<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>包租婆列表</title>
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
		<span class="layui-breadcrumb"> 
			<a href="javascript:top.location.reload()">首页</a>
			<a>包租婆管理</a> 
			<a><cite>查看包租婆</cite></a>
		</span>
		<div class="layui-input-inline" style="float:right">
			<button class="layui-btn layui-btn-sm" onclick="window.location.reload()" >
				<i class="layui-icon">&#xe669;</i>
			</button>
		</div>
		<hr class="layui-bg-gray" style="margin-top: 0">
	</div>
		
	<div style="margin: 0 auto">
		<form class="layui-form layui-form-pane">
			<label class="layui-form-label">搜索</label>
			<div class="layui-input-inline">
				<input type="text" placeholder="用户名" class="layui-input" id='u_alias'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="姓名" class="layui-input" id='u_name'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="家庭地址" class="layui-input" id='u_addr'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="电话号码" class="layui-input" id='u_tel'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="身份证" class="layui-input" id='u_cardno'>
			</div>
			<div class="layui-input-inline">
				<input type="text" placeholder="邮箱" class="layui-input" id='u_email'>
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
					<td>编号</td>
					<td>用户名</td>
					<td>姓名</td>
					<td>家庭地址</td>
					<td>电话号码</td>
					<td>身份证号</td>
					<td>邮箱</td>
					<td>职业</td>
					<td>信誉度</td>
					<td>车位信息</td>
				</tr>
			</thead>
			<tbody id="landlordlist">
				<c:forEach items="${list}" var="data" varStatus="status">
					<tr>
						<td class="uid">${data.id}</td>
						<td>${data.alias}</td>
						<td>${data.name}</td>
						<td>${data.addr}</td>
						<td>${data.tel}</td>
						<td>${data.cardNo}</td>
						<td>${data.email}</td>
						<td>${data.career}</td>
						<td class='td-click'>${data.credit}</td>
						<td>
							<button class='layui-btn layui-btn-normal layui-btn-xs td-button' onclick="showCar(this)"><i class='layui-icon'>&#xe63c;</i>详情</button>
						</td>
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
		layui.use([ 'form', 'element' ], function() {
			var form = layui.form,
				element = layui.element; //面包导航
		});
		// 搜索栏文本框失去焦点时发送AJAX查询
		$(".layui-form input").change(function(){
			search();
		})
		$(".td-click").each(function() {
			var credit = $(this).html();
			$(this).html(showCreditStar(credit) + (100 - credit * 100));
		})
		function showList(curr, limit) {
			$.ajax({
				url : "<%=path%>/showLandlordsByPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : curr,
					limit : limit,
					alias:$("#u_alias").val(),
					name:$("#u_name").val(),
					addr:$("#u_addr").val(),
					tel:$("#u_tel").val(),
					cardNo:$("#u_cardno").val(),
					email:$("#u_email").val()
				},
				success : function(result) {
					var data = result.list;
					var str = "";
					if(data.length==0){
						layer.msg('没有符合的用户信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						str += "<tr><td class='uid'>" + data[i].id + "</td>" +
							"<td>" + data[i].alias + "</td>" +
							"<td>" + data[i].name + "</td>" +
							"<td>" + data[i].addr + "</td>" +
							"<td>" + data[i].tel + "</td>" +
							"<td>" + data[i].cardNo + "</td>" +
							"<td>" + data[i].email + "</td>" +
							"<td>" + data[i].career + "</td>" +
							"<td>" + showCreditStar(data[i].credit) + (100 - data[i].credit * 100) + "</td>" +
							"<td><button class='layui-btn layui-btn-normal layui-btn-sm td-button' onclick='showCar(this)'>点击查看</button></td>";
					}
					$("#landlordlist").html(str);
				}
			});
		}
		function showCreditStar(credit) {
			var i = (1 - credit) * 5;
			var j = parseInt(i);
			var k = 0;
			var str = "";
			for (var z = 1; z <= j; z++) {
				str += "<i class='layui-icon layui-icon-rate-solid' style='color: #FFB800;'></i>";
			}
			if ((i - j) > 0.5) {
				str += "<i class='layui-icon layui-icon-rate-half' style='color: #FFB800;'></i>";
				k++;
			}
			for (var z = 1; z <= (5 - j - k); z++) {
				str += "<i class='layui-icon layui-icon-rate' style='color: #FFB800;'></i>";
			}
			return str;
		}
		// 搜索框搜索触发事件的函数
		function search() {
			var curr = 1;
			var limit = 10;
			$.ajax({
				url : "<%=path%>/showLandlordsByPage.action",
				type : "post",
				dataType : "json",
				data : {
					curr : curr,
					limit : limit,
					alias:$("#u_alias").val(),
					name:$("#u_name").val(),
					addr:$("#u_addr").val(),
					tel:$("#u_tel").val(),
					cardNo:$("#u_cardno").val(),
					email:$("#u_email").val()
				},
				success : function(result) {
					var data = result.list;
					var str = "";
					if(data.length==0){
						layer.msg('没有符合的用户信息', {
							icon : 5,
							time : 2000
						});
					}
					for (var i = 0; i < data.length; i++) {
						str += "<tr><td class='uid'>" + data[i].id + "</td>" +
							"<td>" + data[i].alias + "</td>" +
							"<td>" + data[i].name + "</td>" +
							"<td>" + data[i].addr + "</td>" +
							"<td>" + data[i].tel + "</td>" +
							"<td>" + data[i].cardNo + "</td>" +
							"<td>" + data[i].email + "</td>" +
							"<td>" + data[i].career + "</td>" +
							"<td>" + showCreditStar(data[i].credit) + (100 - data[i].credit * 100) + "</td>" +
							"<td><button class='layui-btn layui-btn-normal layui-btn-sm td-button' onclick='showCar(this)'>点击查看</button></td>";
					}
					$("#landlordlist").html(str);
					$("#totalPage").val(result.total);
					addPage();
				}
			});
		}
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
					limit : 10, //每页显示的条数
					limits : [ 5, 10, 15 ],
					layout : [ 'prev', 'page', 'next', 'count', 'limit', 'skip' ],
					jump : function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						//obj.curr得到当前页，以便向服务端请求对应页的数据。
						//obj.limit得到每页显示的条数
						//首次不执行
						if (!first) {
							showList(obj.curr, obj.limit);
						}
					}
				});
			});
		}
		function showCar(data){
			var userid = $(data).parents("tr").find(".uid").html();
			window.location.href="<%=path%>/showFirstLetByLandlordId.action?landlord.id="+userid;
		}
	</script>
</body>
</html>

